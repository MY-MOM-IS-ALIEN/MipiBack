<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<span>api Main</span>
	</div>
	<div>
		<span>${member.memberId}</span> <br>
		<span>${member.memberName}</span> <br>
		<span>${member.addrZipcode}</span> <br>
		<span>${member.address1} ${member.address2}</span>
	</div>
	<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89f833dcfaa9b766dfea38b685368114&libraries=services"></script>
  <script>
    window.onload = function() {
        var geocoder = new kakao.maps.services.Geocoder();

        // JSP에서 사용자의 주소를 받아옵니다
        var address = '${member.address1}'; 

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch(address, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                // 주소로부터 검색된 좌표
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 지도를 표시할 div와 지도 옵션을 설정합니다
                var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                    mapOption = { 
                        center: coords, // 검색된 좌표를 지도의 중심으로 설정
                        level: 3 // 지도의 확대 레벨
                    };

                // 지도를 생성합니다
                var map = new kakao.maps.Map(mapContainer, mapOption); 

                // 마커를 생성하고 지도에 표시합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });
            } else {
                alert('주소를 찾을 수 없습니다.');
            }
        });
    };
   </script>
</body>
</html>