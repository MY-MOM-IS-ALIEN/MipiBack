<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
</head>
<body>
<form>
 <table width="1400" height="650">
  <tr>
   <td width="100%" height="10%">회원가입
   </td>
  </tr>
  <tr>
   <td height="60%" align="center" valign="top">
   <hr><br>
   <p align="left" style="padding-left:160px">
   <br><br>
   ID : <input type="text" size="10" maxlength="15" name="id" id = "id" onblur="validateId()">
         <input type = "button" name = "idChk" value = "중복체크" onclick="idCheckFunc()">
         <span id="id-confirm-message"></span>
         <span id="id-error-confirm-message"></span>
   					<br><br>
   비밀번호 : <input type="password" size="15" maxlength="20" name="pass" id = "pass">
   					<br><br>
   비밀번호 확인 : <input type="password" size="15" maxlength="20" name="pass2" id = "pass2">
   					<br><br>
   이름 : <input type="text" size="13" name="name" id = "name" onkeyup="fncNameKeyUp(this)">
   <span id="name-error-message"></span>
   					<br><br>
   		
   이메일 : <input type="text" size="15" name="email1" id = "email1">@<input type="text" size="15" name="email2" id = "email2">
   		<span id="email-error-message"></span>
   					<br><br>
   휴대폰 : <select name="ph1">
       <option value="010">010</option>
       <option value="011">011</option>
       <option value="016">016</option>
       <option value="017">017</option>
       <option value="019">019</option>
     </select>
     - <input type="text" name="ph2" size="5" maxlength="4" id = "ph2" onkeyup="Change(event)"> - <input type="text" name="ph3" size="5" maxlength="4" id = "ph3"><br><br>
   성별 : <input type="radio" name="gender" value="남자"> 남자&nbsp;&nbsp;
   <input type="radio" name="gender" value="여자"> 여자<br><br>
   주민번호 : <input type = "text" name = "jumin1" id = "jumin1"> -  <input type = "password" name = "jumin2" id = "jumin2"><br><br>
   주소 : <input type="text" name="address" size="15" maxlength="15"><br>
   *주소는 (시/도)만 입력해주세요 (예: 경기도, 서울특별시, 경상남도 등)
   </p>
  </td>
  </tr>
  <tr>
   <td align="center">
   <hr><br>
    <input type="button"  id = "regi_btn" value="가입신청" onclick="passCheck()">&nbsp;
    <input type="reset" value="다시입력">&nbsp;
    <input type="button" value="취소">
   </td>
  </tr>
 </table>
</form>
</body>

<script type="text/javascript">
function validateId() {
    const id = document.getElementById('id');
    const regExp = /^[a-zA-Z0-9]+$/;
    const idError = document.getElementById('id-confirm-message');

    if(id == ""){
    	alert("아이디를 입력해주세요.");
    	document.getElementById('id').focus();
    } else {
    if (!regExp.test(id.value)) {
        idError.textContent = "아이디 형식이 올바르지 않습니다.";
        idError.style.color = "red";
    } else {
        idError.textContent = "O";
    }
}
}

function idCheckFunc() {
    const idd = 'test';
    const id2 = document.getElementById('id').value;
    const idError2 = document.getElementById('id-error-confirm-message');

    if (idd === id2) {
        idError2.textContent = "이미 존재하는 ID입니다.";
        idError2.style.color = "red";
    } else {
        idError2.textContent = "사용가능한 ID입니다.";
        idError2.style.color = "green";
    }
}

function resetForm() {
    const form = document.getElementById('idForm');
    form.reset(); // 폼 전체를 초기화
    document.getElementById('id-confirm-message').textContent = ""; // 에러 메시지 초기화
    document.getElementById('id-error-confirm-message').textContent = ""; // 에러 메시지 초기화
}

function passCheck() {
    const pass = document.getElementById('pass').value;
    const pass2 = document.getElementById('pass2').value;
    const emailFr = document.getElementById('email1').value;
    const emailBk = document.getElementById('email2').value;
    const emailError = document.getElementById('email-error-message');
    const emailFull = emailFr + '@' + emailBk;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const jumin = document.getElementById('jumin1').value + '-' + document.getElementById('jumin2').value;
    const juminRegex = /^[0-9]{6}-1$/;

    if (pass !== pass2) {
        alert("입력하신 비밀번호가 다릅니다.");
        document.getElementById('pass2').focus();
        return false; // 폼 제출을 막습니다.
    }

    if (emailFr.length === 0 || emailBk.length === 0) {
        alert("이메일을 입력해주세요.");
        return false;
    }

    if (!emailRegex.test(emailFull)) {
        emailError.textContent = "이메일 형식이 아닙니다.";
        emailError.style.color = "red";
        return false;
    }

    if (!juminRegex.test(jumin)) {
        alert("유효하지 않은 주민번호 형식입니다. 형식: 'XXXXXX-1'");
        return false;
    }

    return true; // 폼 제출을 허용합니다.
}

function fncNameKeyUp(input) {
    const name = input.value;
    const regExp = /^[a-zA-Z0-9]*$/;
    const nameError = document.getElementById('name-error-message');

    if (!regExp.test(name)) {
        nameError.textContent = "영어와 숫자만 입력 가능합니다.";
        nameError.style.color = "red";
    } else if (name.length > 5) {
        nameError.textContent = "5글자를 초과할 수 없습니다.";
        nameError.style.color = "red";
    } else {
        nameError.textContent = "O";
    }
}

function Change(event) {
	const phone = event.target.value;
	
	if(phone.length === 4){
		document.getElementById('ph3').focus();
	}
}

</script>
</html>
