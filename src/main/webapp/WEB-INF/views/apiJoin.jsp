<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white shadow-md rounded-lg p-8 w-140">
        <h2 class="text-2xl font-bold mb-6 text-center">회원가입</h2>
        <form id="apiJoinForm" action="/apiJoinProc" method="POST">
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">아이디:</label>
                <div class="flex">
                    <input type="text" id="apiId" name="apiId" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="아이디를 입력하세요">
                    <input type="button" id="apiIdBtn" value="중복확인" onclick="idCheck()" class="ml-2 bg-blue-500 text-white rounded-md px-4 py-2">
                </div>
                <span id="apiIdResult" class="text-red-500 text-sm"></span>
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">비밀번호:</label>
                <input type="password" id="apiPass" name="apiPass" onkeyup="passCheck()" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="비밀번호를 입력하세요">
                <span id="apiPassResult" class="text-red-500 text-sm"></span>
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">비밀번호 확인:</label>
                <input type="password" id="apiPassCheck" onkeyup="passCheck()" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="비밀번호를 다시 입력하세요">
                <span id="apiPassResult2" class="text-red-500 text-sm"></span>
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">이름:</label>
                <input type="text" id="apiName" name="apiName" onkeyup="nameCheck()" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="이름을 입력하세요">
                <span id="apiNameResult" class="text-red-500 text-sm"></span>
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">우편번호:</label>
                <div class="flex">
                    <input type="text" id="sample6_postcode" name="apiAddress1" placeholder="우편번호" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300">
                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="ml-2 bg-blue-500 text-white rounded-md px-4 py-2">
                </div>
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">주소:</label>
                <input type="text" id="sample6_address" name="apiAddress2" placeholder="주소" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300">
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">상세주소:</label>
                <input type="text" id="sample6_detailAddress" name="apiAddress3" placeholder="상세주소" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300">
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">이메일:</label>
                <div class="flex">
                    <input type="text" id="apiEmail1" name="apiEmail1" class="mt-1 block w-1/2 border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="이메일 사용자명">
                    <span class="mx-2">@</span>
                    <input type="text" id="apiEmail2" name="apiEmail2" class="mt-1 block w-1/4 border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="도메인">
                    <select id="apiEmail3" onchange="apiEmail3Select()" class="mt-1 block w-1/4 border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300">
                        <option value="">-------</option>
                        <option value="naver.com">네이버</option>
                        <option value="gmail.com">지메일</option>
                        <option value="nate.com">네이트</option>
                        <option value="daum.net">다음</option>
                        <option value="">직접입력</option>
                    </select>
                    <input type="button" id="apiEmailBtn" value="인증번호" class="ml-2 bg-blue-500 text-white rounded-md px-4 py-2">
                </div>
                
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">인증번호:</label>
                <div class="flex">
                    <input type="text" id="apiEmailCheck" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="인증번호 입력">
                    <input type="button" id="apiEmailBtnCheck" value="확인" class="ml-2 bg-blue-500 text-white rounded-md px-4 py-2">
                </div>
                <span id="mail-check-warn" class="text-red-500 text-sm"></span>
            </div>

            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">전화번호:</label>
                <div class="flex">
                    <input type="text" id="apiPhone" name="apiPhone" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="전화번호를 입력하세요">
                    <input type="button" id="apiPhoneBtn" value="인증번호" class="ml-2 bg-blue-500 text-white rounded-md px-4 py-2">
                </div>
            </div>
			
            <div class="mb-4">
                <label class="block text-sm font-medium text-gray-700">인증번호:</label>
                
                <div class="flex">
                    <input type="text" id="apiPhoneCheck" class="mt-1 block w-full border-2 border-gray-300 rounded-md shadow-sm focus:ring focus:ring-blue-300" placeholder="인증번호 입력">
                    <input type="button" id="apiPhoneBtnCheck" value="확인" class="ml-2 bg-blue-500 text-white rounded-md px-4 py-2">
                </div>
                <span id="phone-check-warn" class="text-red-500 text-sm"></span>
            </div>
			<div class="flex items-center justify-center">
            <div class="g-recaptcha" data-sitekey="6LdxUlcqAAAAADsaPukszdvOT5R-udfQQg3lJNIK"></div>
</div>
            <div class="mt-6">
                <input type="submit" id="apiJoinBtn" value="회원가입" onclick="apiJoinCheck()" class="w-full bg-blue-600 text-white rounded-md px-4 py-2 hover:bg-blue-700 transition duration-200">
            </div>
        </form>
    </div>
</body>
<script>

let m = "${msg}";
if (m != "") {
    alert(m);
}

	function idCheck() {
		var aaa = $("#apiId").val();
		var regex = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{1,10}$/; 
		var result = $("#apiIdResult");
		
		if(regex.test(aaa)){
			result.text("사용 가능한 ID입니다.");
			result.css("color","blue");
		} else {
			result.text("사용 불가능한 ID입니다.");
			result.css("color","red");
		}
	}
	
	function passCheck() {
	    var pass1 = $("#apiPass").val(); // 첫 번째 비밀번호 입력
	    var pass2 = $("#apiPassCheck").val(); // 두 번째 비밀번호 입력
	    var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#])[A-Za-z0-9!@#]{8,}$/;
	    var pass1Re = $("#apiPassResult");
	    var pass2Re = $("#apiPassResult2");

	    if(pass1 == ""){
	    	pass1Re.text("");
	    	
	    } else {
	    if (regex.test(pass1)) {
	        pass1Re.text("O");
	        pass1Re.css("color", "blue");
	        
	        if(pass2 == ""){
	        	pass2Re.text("");
	        } else {
	        if (pass1 === pass2) {
	            pass2Re.text(""); 
	            pass2Re.css("color", "blue");
	        } else {
	            pass2Re.text("비밀번호가 다릅니다.");  
	            pass2Re.css("color", "red");
	        }
	        }
	    } else {
	        pass1Re.text("비밀번호는 대문자,소문자, 숫자, 특수 문자 포함 최소 8글자 이상 조합 입니다."); 
	        pass1Re.css("color", "red");
	    }
	}
	}
	
	function nameCheck() {
		var name1 = $("#apiName").val();
		var nameRe = $("#apiNameResult");
		var regex = /^[가-힣A-Za-z]{1,10}$/;
		
		if(name1 == ""){
			nameRe.text("");
		} else {
			if(regex.test(name1)){
				nameRe.text("");
			} else {
				nameRe.text("한글 또는 영어 10자 미만 입력 가능합니다.");
				nameRe.css("color","red");
			}
		}
	}
	
	function apiEmail3Select(){
		var select1 = $("#apiEmail3").val();
		var emailInput = $("#apiEmail2");
		
		if(select1) {
			emailInput.val(select1);
		} else {
			emailInput.val(""); 
		}
	}
	
	function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
	
	let code; 
	
	$('#apiEmailBtn').click(function() {
		const email = $('#apiEmail1').val() + "@" + $('#apiEmail2').val(); // 이메일 주소
		console.log('완성된 이메일 : ' + email);
		const checkInput = $('#apiEmailCheck') // 인증번호 입력하는곳 
		
		$.ajax({
			type : 'get',
			url : '/mailCheck?email=' + encodeURIComponent(email),
			success : function (data) {
				console.log("data : " +  data);
				code = data;
				alert('인증번호가 전송되었습니다.')
			}			
		}); // end ajax
	}); // end send eamil
	
	// 인증번호 비교 
	$('#apiEmailBtnCheck').click(function () {
		const inputCode = $("#apiEmailCheck").val();
		const resultMsg = $('#mail-check-warn');
		console.log(code);
		console.log(inputCode);
		
		if(inputCode === code){
			resultMsg.text('인증번호가 일치합니다.');
			resultMsg.css('color','red');
			$('#apiEmailBtn').attr('disabled', true);
			$('#apiEmailBtnCheck').attr('disabled', true);
			$('#apiEmail1').attr('readonly', true);
			$('#apiEmail2').attr('readonly', true);
			$('#apiEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
			$('#apiEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
		}else{
			resultMsg.text('인증번호가 불일치 합니다. 다시 확인해주세요!.');
			resultMsg.css('color','red')
		}
	});
	
	//휴대폰 번호 인증
	var code2 = "";
	$("#apiPhoneBtn").click(function(){
	    var phone = $("#apiPhone").val();
	    $.ajax({
	        type:"POST", // post 형식으로 발송
	        url:"/phoneCheck", // controller 위치
	        data: {phoneNumber:phone}, // 전송할 데이터값
	        cache : false,
	        success:function(data){
	            if(data == "error"){ //실패시 
	                alert("휴대폰 번호가 올바르지 않습니다.");
	            }else{               
	            	alert("인증번호 발송이 완료되었습니다.");
	                code2 = data; // 성공하면 데이터저장
	            }
	        }
	    });
	});
	
	//휴대폰 인증번호 검증
	  
	$("#apiPhoneBtnCheck").click(
			function() {
				var phoneCheckSpan = $("#phone-check-warn");

				if ($("#apiPhoneCheck").val() != "" && $("#apiPhoneCheck").val() != null) {
					if ($("#apiPhoneCheck").val() == code2) {
						phoneCheckSpan.text('인증번호가 일치합니다.');
						phoneCheckSpan.css('color', 'red');
						$('#apiPhoneBtn').attr('disabled', true);
						$('#apiPhoneBtnCheck').attr('disabled', true);
						$('#apiPhone').attr('readonly', true);
						$('#apiPhoneCheck').attr('readonly', true);
					} else {
						alert('인증실패')
					}
				} else {
					alert('인증번호를 입력해주세요');
				}
			});

	$(document).ready(function() {
		$('#apiJoinForm').submit(function(event) {
			event.preventDefault(); // 폼 기본 제출 기능 막기
			var captcha = $("#g-recaptcha-response").val(); // reCAPTCHA 응답 값 가져오기

			if (!captcha) {
				alert("자동 가입 방지 봇을 확인해주세요.");
				return false;
			}

			// reCAPTCHA 검증 요청
			$.ajax({
				url : '/VerifyRecaptcha', // reCAPTCHA 검증 처리하는 서버 엔드포인트
				type : 'post',
				data : {
					recaptcha : captcha
				},
				success : function(data) {
					if (data == 0) {

					} else {
						alert("자동 가입 방지 봇을 확인 한뒤 진행 해 주세요.");
					}
				},
				error : function() {
					alert("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다.");
				}
			});
		});
	});

	function apiJoinCheck() {
		if ($("#apiId").val() === "") {
			alert("아이디를 입력하세요.");
			return false;
		}

		if ($("#apiPass").val() === "" || $("#apiPassCheck").val() === "") {
			alert("비밀번호를 입력하세요.");
			return false;
		}

		if ($("#apiName").val() === "") {
			alert("이름을 입력하세요.");
			return false;
		}

		if ($("#sample6_postcode").val() === ""
				|| $("#sample6_address").val() === ""
				|| $("#sample6_detailAddress").val() === "") {
			alert("주소를 모두 입력하세요.");
			return false;
		}

		if ($("#apiEmail1").val() === "" || $("#apiEmail2").val() === "") {
			alert("이메일을 입력하세요.");
			return false;
		}

		if ($("#apiPhone").val() === "") {
			alert("전화번호를 입력하세요.");
			return false;
		}

		// 이메일 인증 확인
		if ($('#apiEmailBtn').is(':enabled')) {
			alert("이메일 인증을 완료하세요.");
			return false;
		}

		// 전화번호 인증 확인
		if ($('#apiPhoneBtn').is(':enabled')) {
			alert("전화번호 인증을 완료하세요.");
			return false;
		}

		// reCAPTCHA 확인
		var captcha = $("#g-recaptcha-response").val();
		if (!captcha) {
			alert("자동 가입 방지 봇을 확인해주세요.");
			return false;
		}

		// 위의 모든 확인이 완료되면 폼 제출
		$('#apiJoinForm')[0].submit(); // 폼 강제 제출
	}
</script>
</html>