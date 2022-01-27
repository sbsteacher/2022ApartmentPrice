<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아파트 거래 내역</title>
</head>
<body>
    <h1>아파트 거래 내역</h1>
    <div>
        <table>
            <tr>
                <th>지역명</th>
                <th>법정동</th>
                <th>지번</th>
                <th>아파트명</th>
                <th>거래금액</th>
                <th>건축년도</th>
                <th>계약년도</th>
                <th>계약월</th>
                <th>계약일</th>
                <th>전용면적</th>
                <th>층</th>
            </tr>
            <c:forEach items="${requestScope.list}" var="item">
                <tr>
                    <td>${item.locationname}</td>
                    <td>${item.dong}</td>
                    <td>${item.jibun}</td>
                    <td>${item.apartmentname}</td>
                    <td>${item.dealamount}</td>
                    <td>${item.buildyear}</td>
                    <td>${item.dealyear}</td>
                    <td>${item.dealmonth}</td>
                    <td>${item.dealday}</td>
                    <td>${item.areaforexclusiveuse}</td>
                    <td>${item.floor}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>