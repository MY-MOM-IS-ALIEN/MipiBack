<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>

    <title>실습문제 1</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
    <form>
        <label><input type="checkbox" id="selectAll" name="selectAll" value="전체" />전체</label>
        <label><input type="button" id="checkButton" value="버튼" /></label><br />
        <label><input type="checkbox" name="options" value="서울" />서울</label>
        <label><input type="checkbox" name="options" value="인천" />인천</label>
        <label><input type="checkbox" name="options" value="경기" />경기</label>
        <label><input type="checkbox" name="options" value="강원" />강원</label>
        <label><input type="checkbox" name="options" value="부산" />부산</label>
        <label><input type="checkbox" name="options" value="대전" />대전</label>
        <label><input type="checkbox" name="options" value="전남" />전남</label>
        <label><input type="checkbox" name="options" value="제주" />제주</label>
        <label><input type="checkbox" name="options" value="평택" />평택</label>
    </form>
    <div id="output"></div>

    <script>
        var checkedOrder = []; 

        $(function () {
            $("#selectAll").change(function () {
                var isChecked = this.checked;
                $('input[type="checkbox"][name="options"]').prop("checked", isChecked).trigger("change");
                console.log("호출");
            });

            $('input[type="checkbox"][name="options"]').change(function () {
                if (this.checked) {
                    if (!checkedOrder.includes(this.value)) {
                        checkedOrder.push(this.value); // 없으면 추가
                    }
                } else {
                    checkedOrder = checkedOrder.filter(item => item !== this.value); // 있으면 삭제
                    $("#selectAll").prop("checked", false);
                }
                updateOutput();
            });

            $('#checkButton').click(function() {
                var allChecked = $("#selectAll").prop("checked");
                var numChecked = $('input[type="checkbox"][name="options"]:checked').length;

                if (allChecked || numChecked < 4) {
                    alert("OK");
                }
            });
        });

        function updateOutput() {
            var outputText = checkedOrder.join(", ");
            $("#output").text(outputText);
        }
    </script>
</body>
</html>
