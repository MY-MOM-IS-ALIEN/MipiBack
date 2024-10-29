package com.com.com.erp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.com.com.erp.dao.BoardDao;
import com.com.com.erp.dao.HistoryDao;
import com.com.com.erp.dto.BoardDto;
import com.com.com.erp.dto.FileDto;
import com.com.com.erp.dto.HistoryDto;
import com.com.com.erp.dto.MemberDto;
import com.com.com.erp.dto.NBoard;
import com.com.com.erp.dto.SearchDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	@Autowired
	private BoardDao bDao;
	@Autowired
	private HistoryDao hDao;

	public String checkSeq(Model model) {
		String view = null;

		int seq = bDao.checkSeq();
		model.addAttribute("seq", (seq + 1));
		view = "write";
		return view;
	}

	public String getBoardList(Integer seq, Model model) {
		String view = null;
		List<BoardDto> boardList = bDao.getBoardList(seq);
		List<HistoryDto> historyList = hDao.getHistoryList(seq);
		List<FileDto> fileList = bDao.getImageList(seq);
		model.addAttribute("boardList", boardList);
		model.addAttribute("historyList", historyList);
		model.addAttribute("fileList", fileList);

		view = "write";

		return view;
	}

	@Transactional
	public String insert(List<MultipartFile> files, BoardDto board, RedirectAttributes rttr, HttpSession session)
			throws Exception {
		log.info("insert()");

		try {
			int result = bDao.insert(board);

			// ���� ���ε� ó��
			if (!files.isEmpty()) {
				fileUpload(files, board, session);
				log.info("�μ�Ʈ ���Ͼ��ε� ȣ��");
			}

			// ���ε� �� ó��
			int hiNum = bDao.checkHiNum(board.getSeq());
			HistoryDto history = new HistoryDto();
			history.setHiNum(hiNum + 1);
			history.setApproval(board.getMemberId());
			history.setSignStatus(board.getApprStat());
			history.setHiNo(board.getSeq());

			MemberDto member = getSession(session);
			if (member.getMemberRank().equals("001") || member.getMemberRank().equals("002")) {
				int resultHis = hDao.insertHistory(history);
			} else {
				updateStat(board, rttr, session);
			}

			String msg = (board.getApprStat().equals("01")) ? "�ӽ����� �Ǿ����ϴ�" : "�ۼ� ����";
			String view = "redirect:/write?seq=" + board.getSeq();
			rttr.addFlashAttribute("msg", msg);
			return view;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("�ۼ� ����: " + e.getMessage());
		}
	}

	@Transactional
	public void fileUpload(List<MultipartFile> files, BoardDto board, HttpSession session) throws Exception {
		// ������� Ȩ ���丮 ��θ� ������
		String userHome = System.getProperty("user.home");
		String desktopPath = userHome + "/Desktop/upload/";

		log.info("Desktop upload path: {}", desktopPath);

		// ���� ���ε� ����
		File folder = new File(desktopPath);
		// ���� ������ upload ������ ������ ����
		if (!folder.exists()) {
			folder.mkdirs();
		}

		for (MultipartFile mf : files) {
			// ���ϸ� ����
			String realName = mf.getOriginalFilename();

			// ���� ���ϸ��� ���ٸ� ���� ���Ϸ� �Ѿ
			if (realName == null || realName.isEmpty()) {
				continue;
			}
			int fileSeq = bDao.fileSeqCheck();
			FileDto dfd = new FileDto();
			dfd.setFileSeq(fileSeq + 1);
			dfd.setRealName(realName);
			dfd.setListSeq(board.getSeq());
			dfd.setSavePath(desktopPath);

			// ������ ���ϸ� ����
			String saveName = System.currentTimeMillis() + realName.substring(realName.lastIndexOf("."));
			dfd.setSaveName(saveName);

			// ���� ����
			File file = new File(desktopPath + saveName);
			try {
				mf.transferTo(file);
				// ���� ���� ����
				int fileResult = bDao.insertFile(dfd);
				log.info("���� ���� ����: {}", saveName);
			} catch (Exception e) {
				// ���� ���� ���� �� �ѹ�
				// ����� ���� ����
				if (file.exists()) {
					file.delete();
				}
				log.error("���� ���ε� �� ������ �߻��߽��ϴ�: {}", e.getMessage(), e);
				log.error("���Ͼ��ε� ĳġ");
				// ���� �߻�
				throw new Exception("���� ���ε� �� ������ �߻��߽��ϴ�: " + e.getMessage(), e);
			}
		}
	}

	public List<BoardDto> selectBoardList(MemberDto member) {
		return bDao.selectBoardList(member);
	}

	public int saveCheck(int seq) {

		return bDao.saveCheck(seq);
	}

	public String updateStat(BoardDto board, RedirectAttributes rttr, HttpSession session) {
		String view = null;
		String msg = null;
		MemberDto member = getSession(session);
		board.setMemberRank(member.getMemberRank());
		if (member.getMemberRank().equals("001") || member.getMemberRank().equals("002")) {
			board.setApprover("");
		} else {
			board.setApprover(member.getMemberId());
		}
		log.info("����updateStat+������Ʈ����");
		int updateStat = bDao.updateStat(board);
		int hiNum = bDao.checkHiNum(board.getSeq());
		HistoryDto history = new HistoryDto();
		history.setHiNum(hiNum + 1);
		history.setApproval(member.getMemberId());
		history.setSignStatus(board.getApprStat());
		history.setHiNo(board.getSeq());
		int ResultHis = hDao.insertHistory(history);
		log.info("�븮������Ʈ �� " + history);
		String status = board.getApprStat();
		if ("05".equals(status)) {
			msg = "�ݷ� �Ǿ����ϴ�.";
		} else if ("01".equals(status)) {
			msg = "�ӽ����� �Ǿ����ϴ�";
		} else {
			msg = "���� �Ϸ�";
		}
		view = "redirect:/main";
		rttr.addFlashAttribute("msg", msg);
		return view;
	}

	public MemberDto getSession(HttpSession session) {
		MemberDto member = (MemberDto) session.getAttribute("member");
		return member;
	}

	public String updateBoard(BoardDto board, RedirectAttributes rttr, HttpSession session) {
		String view = null;
		String msg = null;

		log.info("����updateBoard");
		int updateBoard = bDao.updateBoard(board);
		if (updateBoard > 0) {
			view = "redirect:/main";
			String status = board.getApprStat();
			if ("05".equals(status)) {
				msg = "�ݷ� �Ǿ����ϴ�.";
			} else if ("01".equals(status)) {
				msg = "�ӽ����� �Ǿ����ϴ�";
			} else {
				msg = "���� �Ϸ�";
			}
		} else {
			view = "redirect:/write?seq=" + board.getSeq();
			msg = "���� ����";
		}
		rttr.addFlashAttribute("msg", msg);
		return view;
	}

	public String search(SearchDto search, Model model, RedirectAttributes rttr, HttpSession session) {
		String view = null;
		String msg = null;

		String memberRank = getSession(session).getMemberRank();
		String memberId = getSession(session).getMemberId();
		String memberName = getSession(session).getMemberName();
		if (search != null) {
			search.setMemberRank(memberRank);
			search.setMemberId(memberId);
			search.setMemberName(memberName);
			List<SearchDto> searchList = bDao.search(search);
			if (searchList != null) {
				model.addAttribute("boardList", searchList);
				view = "/main";
			} else {
				msg = "�˻��� ������ �����ϴ�.";
				view = "redirect:/main";
			}
		} else {
			msg = "�˻�� �Է����ּ���";
			view = "redirect:/main";
		}

		rttr.addFlashAttribute("msg", msg);
		return view;
	}

	public List<SearchDto> searchStat(SearchDto searchDto) {
		return bDao.search(searchDto);
	}

	public ResponseEntity<Resource> fileDownload(FileDto file, HttpSession session) throws IOException {
		if (file == null) {
			log.info("���񽺿��� null");
		}

		log.info("fileDownload()" + file);
		String userHome = System.getProperty("user.home");
		String desktopPath = userHome + "/Desktop/upload/" + file.getSaveName();

		// ���� �ϵ��ũ�� ����� ���ϰ� �����ϴ� ��ü�� ����.
		InputStreamResource fResource = new InputStreamResource(new FileInputStream(desktopPath));

		// ���ϸ��� �ѱ��� ��� ���ڵ� ó���� �ʿ�.(UTF-8)
		String fileName = URLEncoder.encode(file.getSaveName(), "UTF-8");

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).cacheControl(CacheControl.noCache())
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body((Resource) fResource);
	}

	public List<Map<String, Object>> getReactList() {

		return bDao.getReactList();
	}

    public List<NBoard> list(NBoard nboard) {
        
        return bDao.list(nboard);
    }
    
    public List<NBoard> searchBoard(Map<String, Object> searchParams) {
        
        return bDao.searchBoard(searchParams);  
    }

}
