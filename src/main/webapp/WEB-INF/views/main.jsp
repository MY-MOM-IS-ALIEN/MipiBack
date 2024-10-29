<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERP시스템</title>
<link rel="stylesheet" href="resources/css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div class="wrap">
		<div class="content">
			<div class="name-area">
				<c:choose>
					<c:when test="${empty member.firstRank}">
						<span>${member.memberName}(${member.memberRank eq '001' ? '사원' : 
             					 member.memberRank eq '002' ? '대리' :
             					 member.memberRank eq '003' ? '과장' :
            				     member.memberRank eq '004' ? '부장' : '알 수 없음'})님
							환영합니다.</span>
							
					</c:when>
					<c:otherwise>
						<span>${member.memberName}(${member.firstRank eq '001' ? '사원' : 
        				        member.firstRank eq '002' ? '대리' :
       					        member.firstRank eq '003' ? '과장' :
       					        member.firstRank eq '004' ? '부장' : '알 수 없음'})
							-${member.apperName}(${member.memberRank eq '001' ? '사원' : 
        				        member.memberRank eq '002' ? '대리' :
       					        member.memberRank eq '003' ? '과장' :
      					        member.memberRank eq '004' ? '부장' : '알 수 없음'})
							님 환영합니다.</span>
							<br>
							
					</c:otherwise>
				</c:choose>

				<input type="button" value="로그아웃" onclick="location.href='/logout';" class="logout-btn">
					<input type="button" value="글쓰기" onclick="location.href='/write';" class="logout-btn">
					<input type="button" value="대리결재" onclick="location.href='/proxy';" class="logout-btn" id="confirm-btn">
					<form action="/loginChange" method="get" style="display: inline;">
					<select name="selectNo">
						<option value="1">사원</option>
						<option value="2">대리</option>
						<option value="3">과장</option>
						<option value="4">부장</option>
					</select>
					<input type="button" value="전환" class="logout-btn" id="changeFrm" onclick="changeFrmSubmit()">
					</form>
			</div>
			<div id="search-area">
				<form action="/search" method="get">
					<select name="category" style="display: inline;">
						<option value="">-선택-</option>
						<option value="boardTitle">제목</option>
						<option value="memberName">작성자</option>
						<option value="boardContent">내용</option>
					</select>
					<input type="text" name="keyword" placeholder="검색어를 입력하세요"> 
					<select name="apprStat" style="display: inline;" onchange="searchApprStat()">
						<option value="">--결재상태--</option>
						<option value="01">임시저장</option>
						<option value="02">결재대기</option>
						<option value="03">결재중</option>
						<option value="04">결재완료</option>
						<option value="05">반려</option>
					</select>
					<br>
					<input type="date" name="startDate"> ~
					<input type="date" name="endDate">
					<input type="button" value="검색" id="searchFrm" onclick="searchFrmSubmit()">
				</form>
			</div>
			<div class="board-area">
    			<table id="boardTable" style="border-collapse: collapse; width: 100%;">
    				<thead>
        				<tr style="border-bottom: 1px solid #ccc;">
            				<th style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">번호</th>
            				<th style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">작성자</th>
            				<th style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">제목</th>
            				<th style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">작성일</th>
            				<th style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">결재일</th>
            				<th style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">결재자</th>
            				<th style="padding: 8px; text-align: center;">결재상태</th>
        				</tr>
    				</thead>
    				<tbody id="boardList">
    					<c:if test="${not empty boardList}">
        					<c:forEach var="board" items="${boardList}">
            					<tr style="border-bottom: 1px solid #ccc;">
                					<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">${board.seq}</td>
                					<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">${board.memberName}</td>
                					<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;"><span class="board-title">${board.boardTitle}</span></td>
                					<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">${board.formattedRegDate}</td>
                					<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">${board.formattedApprDate}</td>
                					<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc; width:150px;">${board.approver}</td>
                					<td style="padding: 8px; text-align: center; "> 
                    					<c:choose>
                        					<c:when test="${board.apprStat eq '02'}">결재대기</c:when>
                        					<c:when test="${board.apprStat eq '01'}">임시저장</c:when>
                        					<c:when test="${board.apprStat eq '03'}">결재중</c:when>
                        					<c:when test="${board.apprStat eq '04'}">결재완료</c:when>
                        					<c:when test="${board.apprStat eq '05'}">반려</c:when>
                        					<c:otherwise>알 수 없음</c:otherwise>
                    					</c:choose>
                					</td>
            					</tr>
        					</c:forEach>
    					</c:if>
    					<c:if test="${empty boardList}">
            				<tr>
                				<td colspan="6" style="padding: 8px; text-align: center;">등록된 글이 없습니다.</td>
            				</tr>
        				</c:if>
    				</tbody>
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
     
	 function ButtonVisibility() {
         console.log(role); 
         
         var confirmBtn = $('#confirm-btn');  
         
         if (role === '001' || role === '002') {
             confirmBtn.hide();  
         } else {
             confirmBtn.show();  
         }
     }
     
     $(document).ready(function() {
    	 ButtonVisibility();
    	    $('#boardTable').on('click', 'tr', function() {
    	        var seq = $(this).find('td:first').text();
    	        if (seq !== '') {
    	            window.location.href = '/write?seq=' + seq;
    	        } 
    	    });
    	});
     
     function changeFrmSubmit() {
         document.querySelector('form[action="/loginChange"]').submit();
     }
     
     function searchFrmSubmit() {
    	 const form = document.querySelector("form[action='/search']");
    	 form.submit();
     }

     function searchApprStat() {
    	 const form = document.querySelector("form[action='/search']");
    	 const apprStat = form.querySelector("select[name='apprStat']").value;
    	 
    	        $.ajax({
    	            url: '/searchStat',
    	            type: 'GET',
    	            data: { apprStat: apprStat },
    	            success: function(response) {
    	                console.log(response.data);
    	                if (response.stat === 'success') {
    	                    if (response.data.length > 0) {
    	                    	
    	                    	var html = '';
    	                    	for(var i = 0; i < response.data.length; i++){
    	                    		html += '<tr style="border-bottom: 1px solid #ccc;">';
    	                    		html += '<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">'+response.data[i].seq+'</td>';
    	                    		html += '<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">'+response.data[i].memberName+'</td>';
    	                    		html += '<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">'+response.data[i].boardTitle+'</td>';
    	                    		html += '<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">'+response.data[i].formattedRegDate+'</td>';
    	                    		html += '<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">'+response.data[i].formattedApprDate+'</td>';
    	                    		html += '<td style="padding: 8px; text-align: center; border-right: 1px solid #ccc;">'+response.data[i].approver+'</td>';
    	                    		html += '<td style="padding: 8px; text-align: center;">'+getApprStatText(response.data[i].apprStat)+'</td>';
    	                    		html += '</tr>';
    	                    	}
    	                    	
								$("#boardList").html(html);
    	                    } else {
    	                        boardList.append(`<tr><td colspan="7" style="padding: 8px; text-align: center;">등록된 글이 없습니다.</td></tr>`);
    	                    }
    	                } else {
    	                	alert("검색 실패");
    	                    console.log(response.message);
    	                }
    	            }
    	        });
    	}
    	function getApprStatText(apprStat) {
    	    switch(apprStat) {
    	        case '01': return '임시저장';
    	        case '02': return '결재대기';
    	        case '03': return '결재중';
    	        case '04': return '결재완료';
    	        case '05': return '반려';
    	        default: return '알 수 없음';
    	    }
    	}
    	</script>
</html>