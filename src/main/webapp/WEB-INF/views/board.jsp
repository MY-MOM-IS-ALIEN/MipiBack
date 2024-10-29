<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.2.1/flowbite.min.css" rel="stylesheet" />
<title>게시판</title>
</head>
<body>
<form id="searchForm" class="mx-40" action="/search" method="post">
    <div class="search-area">
        <label for="searchType">검색 유형:</label>
        <select id="searchType" name="searchType">
            <option value="title">제목</option>
            <option value="author">작성자</option>
            <option value="content">내용</option>
        </select>
        
        <input type="text" id="search" name="searchValue" placeholder="검색어를 입력하세요">
        <br>
        <input type="date" name="startDate"> ~ <input type="date" name="endDate">
        <button type="submit">검색</button>
    </div>
</form>
<form id="deleteForm" class="mx-40" action="/delete" method="post">
    <input type="hidden" name="selectedSeq" id="selectedSeq" />
    <button type="submit" class="text-white bg-[#24292F] hover:bg-[#24292F]/90 focus:ring-4 focus:outline-none focus:ring-[#24292F]/50 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:focus:ring-gray-500 dark:hover:bg-[#050708]/30 me-2 mb-2 btn">
        🙄 삭제
    </button>
    <a href="/write">글쓰기</a>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" class="p-4">
                        <div class="flex items-center">
                            <input id="checkbox-all-search" type="checkbox" onclick="toggleCheckboxes()" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" />
                            <label for="checkbox-all-search" class="sr-only">checkbox</label>
                        </div>
                    </th>
                    <th scope="col" class="px-6 py-3">글번호</th>
                    <th scope="col" class="px-6 py-3">작성자</th>
                    <th scope="col" class="px-6 py-3">제목</th>
                    <th scope="col" class="px-6 py-3">작성일</th>
                    <th scope="col" class="px-6 py-3">수정일</th>
                    <th scope="col" class="px-6 py-3">조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="board" items="${boardList}">
                    <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                        <td class="w-4 p-4">
                            <div class="flex items-center">
                                <input id="checkbox-${board.seq}" type="checkbox" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600" onclick="selectSeq(${board.seq})" />
                                <label for="checkbox-${board.seq}" class="sr-only">checkbox</label>
                            </div>
                        </td>
                        <td class="px-6 py-4">${board.seq}</td>
                        <td class="px-6 py-4 text-black">${board.memName}</td>
                        <td class="px-6 py-4 text-black"><a href="/write?seq=${board.seq}">${board.boardSubject}</a></td>
                        <td class="px-6 py-4">${board.formattedRegDate}</td>
                        <td class="px-6 py-4">${board.formattedUptDate}</td>
                        <td class="px-6 py-4">${board.viewCnt}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.2.1/flowbite.min.js"></script>
<script type="text/javascript">
let m = "${msg}";
if (m != "") {
    alert(m);
}
        function toggleCheckboxes() {
            const checkboxes = document.querySelectorAll('input[type="checkbox"]');
            const selectedIds = Array.from(checkboxes)
                                     .filter(checkbox => checkbox.checked)
                                     .map(checkbox => checkbox.id.split('-')[1]);

            if (selectedIds.length > 5) {
                alert("최대 5개의 항목만 선택할 수 있습니다.");
                checkboxes.forEach(checkbox => checkbox.checked = false);
                return;
            }

            document.getElementById('selectedSeq').value = selectedIds.join(',');
        }

        function selectSeq(seq) {
            toggleCheckboxes(); // 선택 상태를 검사하고 업데이트합니다.
        }
</script>
</body>
</html>
