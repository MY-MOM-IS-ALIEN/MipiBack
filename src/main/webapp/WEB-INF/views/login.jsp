<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="resources/css/style.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="wrap">
		<div class="content">
			<form action="loginProc" method="post">
				<h2 class="form-header">로그인</h2>
				<!-- 개인정보 입력 영역 -->
				<div class="loginBox">
					<h5>아이디</h5>
					<input type="text" class="write-input" name="memberId"
						autofocus placeholder="아이디를 입력해주세요." required="required">
					<h5>비밀번호</h5>
					<input type="password" class="write-input" name="password"
						autofocus placeholder="비밀번호를 입력해주세요." required="required">
						<br>
						<span style="color: #fff; font-size: 13px;"><input type="checkbox" onclick="autoLogin()" id="autoLoginCheckbox">자동로그인</span>
				</div>
				<div class="btn-area">
					<input type="submit" class="btn-write" value="로그인" id="loginbtn"><br>
				</div>
			</form>
		</div>
	</div>

</body>

<script type="text/javascript">
	let m = "${msg}";
	if (m != "") {
		alert(m);
	}
	
	 function autoLogin() {
         var checkbox = document.getElementById("autoLoginCheckbox");
         var memberId = document.getElementsByName("memberId")[0];
         var password = document.getElementsByName("password")[0];

         if (checkbox.checked) {
             memberId.value = "user001"; // 여기에서 실제 ID를 설정
             password.value = "password1"; // 여기에서 실제 비밀번호를 설정
         } else {
             memberId.value = "";
             password.value = "";
         }
     }
</script>
</html>