package com.com.com.erp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.com.com.erp.dto.BoardDto;
import com.com.com.erp.dto.FileDto;
import com.com.com.erp.dto.MemberDto;
import com.com.com.erp.dto.NBoard;
import com.com.com.erp.dto.SearchDto;
import com.com.com.erp.service.BoardService;
import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataTypes;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.tx.HttpPlatformRequest;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@RequestMapping("/api/board")
@Slf4j
public class BoardController {
	@Autowired 
	private BoardService bServ;
	
//	public static void main() {
//        for (int i = 1; i <= 10; i++) {
//            // i가 홀수면 continue로 다음 반복으로 넘어감
//            if (i % 2 != 0) {
//                continue;
//            }
//
//            // i가 7이면 반복문 종료
//            if (i == 7) {
//                break;
//            }
//
//            // 짝수 출력
//            System.out.println("짝수: " + i);
//        }
//    }
//
//	
//	//넥사크로
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	   public void getBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//	      HttpPlatformRequest req = new HttpPlatformRequest(request);
//
//	      HttpPlatformResponse res = new HttpPlatformResponse(response, req);
//
//	      // 1. 데이터 가져오기
//	      List<NBoard> list = bServ.list(new NBoard());
//
//	      // 2. Nexacro DataSet 생성
//	      PlatformData platformData = new PlatformData();
//	      DataSet dataSet = new DataSet("output");
//
//	      // 3. DataSet 컬럼 정의
//	      dataSet.addColumn("seq", DataTypes.INT);
//	      dataSet.addColumn("mem_id", DataTypes.STRING);
//	      dataSet.addColumn("mem_name", DataTypes.STRING);
//	      dataSet.addColumn("board_subject", DataTypes.STRING);
//	      dataSet.addColumn("board_content", DataTypes.STRING);
//	      dataSet.addColumn("reg_date", DataTypes.DATE);
//	      dataSet.addColumn("upt_date", DataTypes.DATE);
//	      dataSet.addColumn("view_cnt", DataTypes.INT);
//	      dataSet.addColumn("useyn", DataTypes.STRING);
//
//	      // 4. DataSet에 데이터 삽입
//	      for (NBoard board : list) {
//	         int row = dataSet.newRow();
//	         dataSet.set(row, "seq", board.getSeq());
//	         dataSet.set(row, "mem_id", board.getMem_id());
//	         dataSet.set(row, "mem_name", board.getMem_name());
//	         dataSet.set(row, "board_subject", board.getBoard_subject());
//	         dataSet.set(row, "board_content", board.getBoard_content());
//	         dataSet.set(row, "reg_date", board.getReg_date());
//	         dataSet.set(row, "upt_date", board.getUpt_date());
//	         dataSet.set(row, "view_cnt", board.getView_cnt());
//	         dataSet.set(row, "useyn", board.getUseyn());
//	      }
//
//	      // 5. PlatformData에 DataSet 추가
//	      platformData.addDataSet(dataSet);
//
//	      // 6. HttpPlatformResponse로 응답 전송
//	      res.setData(platformData);
//	      res.sendData();
//	   }
//	   
//	    @RequestMapping(value = "/search", method = RequestMethod.POST)
//	       public void searchBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//	           // Nexacro에서 전송된 요청을 파싱
//	           HttpPlatformRequest platformRequest = new HttpPlatformRequest(request);
//	           platformRequest.receiveData();
//	           PlatformData inputData = platformRequest.getData();
//	           DataSet ds = inputData.getDataSet("input");  // 클라이언트에서 전송된 DataSet 이름이 input이라고 가정
//
//	           // 파라미터 수집
//	           if(ds == null) {
//	              System.out.println("널이다");
//	           }
//	           String searchType = ds.getString(0, "searchType");
//	           String searchKeyword = ds.getString(0, "searchKeyword");
//	           String startDate = ds.getString(0, "startDate");
//	           String endDate = ds.getString(0, "endDate");
//
//	           // 파라미터 확인
//	           System.out.println("searchType: " + searchType);
//	           System.out.println("searchKeyword: " + searchKeyword);
//	           System.out.println("startDate: " + startDate);
//	           System.out.println("endDate: " + endDate);
//
//	           // 파라미터를 Map에 저장
//	           Map<String, Object> searchParams = new HashMap<String, Object>();
//	           searchParams.put("searchType", searchType);
//	           searchParams.put("searchKeyword", searchKeyword);
//	           searchParams.put("startDate", startDate);
//	           searchParams.put("endDate", endDate);
//
//	           // 서비스 호출
//	           List<NBoard> list = bServ.searchBoard(searchParams);
//
//	           System.out.println("LIst" + list);
//	           System.out.println(searchParams);
//
//	           // DataSet 생성 및 응답 처리
//	           PlatformData platformData = new PlatformData();
//	           DataSet dataSet = new DataSet("output");
//
//	           dataSet.addColumn("seq", DataTypes.INT);
//	           dataSet.addColumn("mem_id", DataTypes.STRING);
//	           dataSet.addColumn("mem_name", DataTypes.STRING);
//	           dataSet.addColumn("board_subject", DataTypes.STRING);
//	           dataSet.addColumn("board_content", DataTypes.STRING);
//	           dataSet.addColumn("reg_date", DataTypes.DATE);
//	           dataSet.addColumn("upt_date", DataTypes.DATE);
//	           dataSet.addColumn("view_cnt", DataTypes.INT);
//	           dataSet.addColumn("useyn", DataTypes.STRING);
//
//	           for (NBoard board : list) {
//	               int row = dataSet.newRow();
//	               dataSet.set(row, "seq", board.getSeq());
//	               dataSet.set(row, "mem_id", board.getMem_id());
//	               dataSet.set(row, "mem_name", board.getMem_name());
//	               dataSet.set(row, "board_subject", board.getBoard_subject());
//	               dataSet.set(row, "board_content", board.getBoard_content());
//	               dataSet.set(row, "reg_date", board.getReg_date());
//	               dataSet.set(row, "upt_date", board.getUpt_date());
//	               dataSet.set(row, "view_cnt", board.getView_cnt());
//	               dataSet.set(row, "useyn", board.getUseyn());
//	           }
//
//	           platformData.addDataSet(dataSet);
//	System.out.println("@@@@@@"+dataSet);
//	           HttpPlatformResponse res = new HttpPlatformResponse(response, new HttpPlatformRequest(request));
//	           res.setData(platformData);
//	           res.sendData();
//	       }
//	   
//
//	//넥사크로 끝
	
	@GetMapping("/main")
	public String main(HttpSession session,Model model) {
		MemberDto member = bServ.getSession(session);
		if(member == null) {
			return "login";
		} else {
		List<BoardDto> boardList = bServ.selectBoardList(member);
		model.addAttribute("boardList",boardList);
		
		return "main";
	}
	}
	
	@GetMapping("/write")
	public String write(@RequestParam(value = "seq", required = false, defaultValue = "0") Integer seq,
	                    Model model) {
		String view = null;
		if(seq != 0) {
		view = bServ.getBoardList(seq,model); // 이미 있는 seq면 가져오기	
		} else {
	    view = bServ.checkSeq(model); // seq가 없으면 다음 생성될 seq체크
		}
	    return view;
	}
	
	@PostMapping("/writeProc")
	public String writeProc(@RequestParam("files") List<MultipartFile> files,
	                        BoardDto board, RedirectAttributes rttr, HttpSession session) {
	    String status = board.getApprStat();
	    MemberDto member = bServ.getSession(session);
	    String view = null;

	    try {
	        if (board != null) {
	            if (status.equals("01")) {
	                int saveCheck = bServ.saveCheck(board.getSeq());
	                if (saveCheck != 0) {
	                    log.info("컨트롤러writeProc+임시저장로직");
	                    view = bServ.updateBoard(board, rttr, session);
	                } else {
	                    view = bServ.insert(files, board, rttr, session);
	                }
	            } else if (status.equals("02")) {
	                view = bServ.insert(files, board, rttr, session);
	            } else if (status.equals("03") || status.equals("04") || status.equals("05")) {
	                int saveCheck = bServ.saveCheck(board.getSeq());
	                if (saveCheck != 0) {
	                    log.info("컨트롤러writeProc+결재로직");
	                    board.setMemberName(member.getMemberName());
	                    board.setApprStat(status);
	                    view = bServ.updateStat(board, rttr, session);
	                } else {
	                    view = bServ.insert(files, board, rttr, session);
	                }
	            } else if (status.equals("재상신")) {
	                if (member.getMemberRank().equals("003")) {
	                    status = "03";
	                } else if (member.getMemberRank().equals("004")) {
	                    status = "04";
	                } else {
	                    status = "02";
	                }
	                log.info("컨트롤러writeProc+재상신로직");
	                board.setMemberName(member.getMemberName());
	                board.setApprStat(status);
	                bServ.updateStat(board, rttr, session);
	                view = bServ.updateBoard(board, rttr, session);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        String msg = "작성 실패: " + e.getMessage();
	        rttr.addFlashAttribute("msg", msg);
	        return "redirect:/write";
	    }

	    return view;
	}

	@PostMapping("/updateStat")
	public String updateStat(BoardDto board,RedirectAttributes rttr,HttpSession session) {
		log.info("컨트롤러updateStat");
		String view = null;
		if(board != null) {
			view = bServ.updateStat(board,rttr,session);
			log.info("결재하러 가는보드 = " + board);
		} 
		return view;
	}
	
	@GetMapping("/search")
	public String search(SearchDto search,Model model,RedirectAttributes rttr,HttpSession session) {
		String view = null;
		log.info("search"+search);
		if(search != null) {
			view = bServ.search(search,model,rttr,session);
		}
		return view;
	}
	
	@GetMapping("/searchStat")
	@ResponseBody
	public ResponseEntity<?> search(@RequestParam("apprStat") String apprStatus, HttpSession session) {
	    String memberRank = bServ.getSession(session).getMemberRank();
	    String memberId = bServ.getSession(session).getMemberId();
	    String memberName = bServ.getSession(session).getMemberName();
	    Map<String, Object> response = new HashMap<String, Object>();

	    log.info(apprStatus);
	    if (apprStatus != null) {
	        SearchDto search = new SearchDto();
	        search.setApprStat(apprStatus);
	        search.setMemberRank(memberRank);
	        search.setMemberId(memberId);
	        search.setMemberName(memberName);

	        List<SearchDto> searchList = bServ.searchStat(search);
	        if (searchList != null) {
	            response.put("stat", "success");
	            response.put("data", searchList);
	        } else {
	            response.put("stat", "fail");
	            response.put("message", "검색된 내용이 없습니다.");
	        }
	    } else {
	        response.put("stat", "fail");
	        response.put("message", "결재 상태를 입력해주세요.");
	    }

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/download")
    public ResponseEntity<Resource> fileDownload(@RequestParam("fileSeq") int fileSeq,
                                                 @RequestParam("saveName") String saveName,
                                                 HttpSession session) throws IOException {
		if (fileSeq == 0 || saveName == null || saveName.isEmpty()) {
	        log.info("컨트롤러에서 null");
	    }
        FileDto file = new FileDto();
        file.setFileSeq(fileSeq);
        file.setSaveName(saveName);
        log.info("fileDownload()" + file);
        return bServ.fileDownload(file, session);
    }
	
	@GetMapping("/selectReactList")
	@ResponseBody 
	public List<Map<String, Object>> selectReactList() {
	    List<Map<String, Object>> list = bServ.getReactList();
	    return list;
	}
	
}
