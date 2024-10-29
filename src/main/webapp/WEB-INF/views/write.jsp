<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERP시스템</title>
<link rel="stylesheet" href="resources/css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="wrap">
		<div class="content">
		<div class="check-area">
			<table>
				<tr>
				
					<td class="check1td">결재요청</td>
					<td class="check2td">과장</td>
					<td>부장</td>
				</tr>
				<tr>
					<td class="check1td"><input type="checkbox" id="check1" disabled></td>
					<td class="check2td"><input type="checkbox" id="check2" disabled></td>
					<td> <input type="checkbox" id="check3" disabled></td>
				</tr>
			</table>
		</div>
			<div class="write-form">
				<c:choose>
    <c:when test="${empty boardList}">
    <form id="writeFormEmpty" action="/writeProc" method="post" enctype="multipart/form-data">
        <label>번호 : 
            <input type="text" name="seq" value="${seq}" readonly="readonly">
        </label> <br>
        <br> 
        <label>작성자 : 
            <input type="text" value="${member.memberName}" readonly="readonly"> 
            <input type="hidden" name="memberId" value="${member.memberId}">
        </label> <br>
        <br> 
        <label>제목 : 
            <input type="text" name="boardTitle" placeholder="제목" value="">
        </label> <br>
        <br> 
        <label>내용 : <br> 
            <textarea rows="5" cols="25" placeholder="내용" name="boardContent"></textarea>
        </label> <br> 
        <input type="hidden" name="apprStat" value="" id="statusEmpty">
        <br>
        <div id="fileInputsContainer">
        <div class="file-input-wrapper">   
        <input type="file" name="files" accept="image/*" onchange="addFileInputIfNeeded(this); validateImageSize(this);">
  		  </div>
  			  </div>

		<br>
        <input type="button" value="결재" onclick="submitForm('approve', 'writeFormEmpty', 'statusEmpty')" > 
        <input type="button" value="임시저장" onclick="submitForm('save', 'writeFormEmpty', 'statusEmpty')" >
        <input type="button" value="뒤로가기" class="back-btn">
    </form>
</c:when>
<c:otherwise>
    <form id="writeFormFilled" action="/writeProc" method="post" enctype="multipart/form-data">
        <c:forEach var="board" items="${boardList}">
            <label>번호 : 
                <input type="text" name="seq" value="${board.seq != null ? board.seq : ''}" readonly="readonly">
            </label> <br>
            <br> 
            <label>작성자 : 
                <input type="text" value="${board.memberName != null ? board.memberName : ''}" id="memberName"readonly="readonly"> 
                <input type="hidden" name="memberId" value="${member != null ? member.memberId : ''}">
            </label> <br>
            <br> 
            <label>제목 : 
                <input type="text" name="boardTitle" placeholder="제목" value="${board.boardTitle != null ? board.boardTitle : ''}">
            </label> <br>
            <br> 
            <label>내용 : <br> 
                <textarea rows="5" cols="25" placeholder="내용" name="boardContent">${board.boardContent != null ? board.boardContent : ''}</textarea>
            </label> <br> 
            <input type="hidden" value="${board.apprStat}" name="apprStat" id="statusFilled"> 
            <input type="hidden" value="${member.memberRank}" id="memberRank"> 
             <div id="fileInputsContainer">
            <c:forEach var="file" items="${fileList}">
                <div class="file-input-wrapper">
                    <input type="file" name="files" accept="image/*" data-file-id="${file.fileSeq}" data-file-name="${file.saveName}" onchange="addFileInputIfNeeded(this); validateImageSize(this);">
                    <button type="button" onclick="removeExistingFile(${file.fileSeq}, this)">삭제</button>
                    <a href="<c:url value='/download?fileSeq=${file.fileSeq}&saveName=${file.saveName}' />"><button type="button">다운로드</button></a>
                </div>
                <div class="img-area">
              	  <img src="/Desktop/upload/${file.saveName}" alt="이미지">
                </div>
            </c:forEach>
            <div class="file-input-wrapper">   
                <input type="file" name="files" accept="image/*" onchange="addFileInputIfNeeded(this); validateImageSize(this);">
            </div>
        </div>
            
            <input type="button" value="결재" onclick="submitForm('approve', 'writeFormFilled', 'statusFilled')" class="approve-btn"> 
            <input type="button" value="임시저장" onclick="submitForm('save', 'writeFormFilled', 'statusFilled')" class="save-btn"> 
            <input type="button" value="반려" onclick="submitForm('reject', 'writeFormFilled', 'statusFilled')" class="reject-btn">
            <input type="button" value="뒤로가기" class="back-btn">
        </c:forEach>
    </form>
</c:otherwise>
</c:choose>
			</div>
			<div class="approve-area">
				<table>
					<tr>
						<td>번호</td>
						<td>결재자</td>
						<td>결재일</td>
						<td>결재상태</td>
					</tr>
					<c:forEach var="history" items="${historyList}" varStatus="status">
						<tr>
							<td>${history.hiNum}</td>
							<td id="historyName" data-index="${status.index}">${history.memberName}</td>
							<td>${history.signDate}</td>
							<td>
							<c:choose>
									<c:when test="${history.signStatus eq '02'}">결재대기</c:when>
									<c:when test="${history.signStatus eq '01'}">임시저장</c:when>
									<c:when test="${history.signStatus eq '03'}">결재중</c:when>
									<c:when test="${history.signStatus eq '04'}">결재완료</c:when>
									<c:when test="${history.signStatus eq '05'}">반려</c:when>
									<c:otherwise>알 수 없음</c:otherwise>
								</c:choose>
								</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	let m = "${msg}";
	if (m != "") {
		alert(m);
	}
	
	var role = '${member.memberRank}';
	
	$(document).ready(function() {
	    // 페이지 로드 시 실행될 함수
	    $('.back-btn').on('click', function() {
	        window.location.href = '/main';
	    });

	    ButtonVisibility(); // 버튼 가시성 설정 함수 호출
		
	    var apprStat = $("#statusFilled").val();
	    var memberRank = $("#memberRank").val();
	    var memberName = $("#memberName").val();
	    var firstMemberName = $('#historyName[data-index="0"]').text();
	    
	    var saveBtn = $('.save-btn');
	    var apprBtn = $('.approve-btn');
	    var rejectBtn = $('.reject-btn');
	    console.log(apprStat);
	    if (apprStat === '02') {
	        $('#check1').prop('checked', true);
	        saveBtn.hide();
	        apprBtn.hide();
	        if(memberRank === '003'){
	       	 apprBtn.show();
	        } 
	    } else if(apprStat === '03') {
	    	$('#check1').prop('checked', true);
	    	$('#check2').prop('checked', true);
	    	saveBtn.hide();
	    	if(memberRank === '004'){
	    		apprBtn.show();
	    	} else {
	    		apprBtn.hide();
	    		rejectBtn.hide();
	    	}
	    } else if(apprStat === '04'){
	    	if(memberName === firstMemberName){
	    		$('#check1').hide();
	    		$('.check1td').hide();
	    		$('#check2').hide();
	    		$('.check2td').hide();
	    	}
	    	$('#check1').prop('checked', true);
	    	$('#check2').prop('checked', true);
	    	$('#check3').prop('checked', true);
	    	saveBtn.hide();
	        apprBtn.hide();
	        rejectBtn.hide();
	    } else {
	    	$('#check1').prop('checked', false);
	    	$('#check2').prop('checked', false);
	    	$('#check3').prop('checked', false);
	    	if(memberRank === '003' || memberRank === '004'){
		        rejectBtn.hide();
	    	}
	    }
	    if (apprStat === '02' || apprStat === '03' || apprStat === '04') {
	        $('input[type="text"], textarea').attr('readonly', true);
	    }

	    // 파일 입력 필드 초기화
	    $('input[type="file"]').each(function() {
	        var fileName = $(this).data('file-name');
	        if (fileName) {
	            var fileInput = $(this)[0];
	            var dataTransfer = new DataTransfer();
	            var file = new File([""], fileName, { type: "image/*" });
	            dataTransfer.items.add(file);
	            fileInput.files = dataTransfer.files;
	        }
	    });
	});

	function ButtonVisibility() {
	    var rejectBtn = $('.reject-btn');
	    var saveBtn = $('#save-btn');
	    if (role === '001' || role === '002') {
	        rejectBtn.hide();
	    } else {
	        rejectBtn.show();
	        saveBtn.hide();
	    }
	}

	function submitForm(action, formId, statusId) {
	    var status = document.getElementById(statusId);
	    switch (action) {
	        case 'approve':
	        	if(status.value === '05' || status.value === '01' && (role === '001' || role === '002')){
		        status.value = '재상신';	
		        break; 
		        } else if(status.value !== '05' && (role === '001' || role === '002')){
	            status.value = '02';
	            break;
	        	} else if(role === '003'){
	        	status.value = '03';
	        	break;
	        	} else if(role === '004'){
	        	status.value = '04';	
	        	break;
	        	} 
	            break;
	        case 'save':
	            status.value = '01';
	            break;
	        case 'reject':
	            status.value = '05';
	            break;
	        default:
	            status.value = '';
	    }
	    console.log("Status before submit: " + status.value);

	     document.getElementById(formId).submit();
	}

	function addFileInputIfNeeded(input) {
	    if (input.files.length > 0) {
	        var reader = new FileReader();

	        reader.onload = function(e) {
	            var img = new Image();
	            img.onload = function() {
	                if (this.width > 500 || this.height > 500) {
	                    alert("이미지의 크기는 가로 및 세로 500픽셀을 초과할 수 없습니다.");
	                    input.value = "";  // 파일 선택 취소
	                } else {
	                    addNewInputField(input);
	                }
	            };
	            img.src = e.target.result;
	        };
	        reader.readAsDataURL(input.files[0]);
	    }
	}

	function addNewInputField(input) {
	    if (!input.nextElementSibling) {  // 취소 버튼 추가
	        addCancelButton(input);
	    }

	    var container = document.getElementById("fileInputsContainer");
	    var existingInputs = container.querySelectorAll("input[type='file']");
	    
	    if (existingInputs.length < 5) {
	        var newInputWrapper = document.createElement("div");
	        newInputWrapper.className = "file-input-wrapper";
	        var newInput = document.createElement("input");
	        newInput.type = "file";
	        newInput.name = "files";
	        newInput.accept = "image/*";
	        newInput.setAttribute("onchange", "addFileInputIfNeeded(this)");
	        newInputWrapper.appendChild(newInput);
	        container.appendChild(newInputWrapper);
	    }
	}

	function addCancelButton(input) {
	    var cancelButton = document.createElement("button");
	    cancelButton.textContent = "취소";
	    cancelButton.type = "button";
	    cancelButton.onclick = function() {
	        var wrapper = input.parentElement;
	        wrapper.parentElement.removeChild(wrapper);
	    };
	    input.parentElement.appendChild(cancelButton);
	}

	function removeExistingFile(fileSeq, button) {
	    // 서버에 파일 삭제 요청을 보내는 코드를 여기에 추가하세요.
	    var wrapper = button.parentElement;
	    wrapper.parentElement.removeChild(wrapper);
	}
</script>

</html>