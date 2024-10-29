<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대리결재 권한부여</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div id="proxy-area">
		<form id="proxy-form" action="/proxyProc" method="post">
			<span>대리결재자 : </span>
	<select id="proxyId" name="proxyId" onchange="selectRank()">
    <option value="">-----</option>
    <c:choose>
        <c:when test="${member.memberRank == '003'}">
            <option value="user001">곽두팔</option>
            <option value="user002">김말숙</option>
            <option value="user003">박철수</option>
            <option value="user004">이영자</option>
            <option value="user005">정순자</option>
            <option value="user006">최용필</option>
        </c:when>
        <c:when test="${member.memberRank == '004'}">
            <option value="user004">이영자</option>
            <option value="user005">정순자</option>
            <option value="user006">최용필</option>
            <option value="user007">송복남</option>
            <option value="user008">허덕수</option>
            <option value="user009">신판례</option>
        </c:when>
    </c:choose>
</select>
			<br>
			<br>
			<span>직급 : <input type="text" id="rank" value=""></span>
			<br>
			<br>
			<span>대리자 : <input type="text" value="${member.memberName}(${member.memberRank eq '001' ? '사원' : 
    member.memberRank eq '002' ? '대리' :
    member.memberRank eq '003' ? '과장' :
    member.memberRank eq '004' ? '부장' : '알 수 없음'})"></span> 
    		<br>
    		<br>
    		<input type="button" value="취소" class="back-btn">
    		<input type="button" value="승인" onclick="proxyFrmSubmit()">
		</form>
	</div>
	<script type="text/javascript">
$(document).ready(function() {
    $('.back-btn').on('click', function() {
        window.location.href = '/main';
    });
});

function proxyFrmSubmit() {
    const form = document.querySelector("form[action='/proxyProc']");
    console.log("서브밋 클릭");
    form.submit();
}

function selectRank(){
    var name = document.getElementById("proxyId").value;
    var rank = document.getElementById("rank");
    console.log(name);
    console.log(rank);
    if(name === "user001" || name === "user002" || name === "user003"){
        rank.value = "사원";
    } else {
        rank.value = "대리";
    }
}

</script>
</body>
</html>