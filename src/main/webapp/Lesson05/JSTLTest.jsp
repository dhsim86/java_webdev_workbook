<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 7:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Lesson05.MyMember" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Test</title>
</head>
<body>

    <c:out value="Hi"></c:out> <br>
    <c:out value="Hello">Hi</c:out> <br>

    <c:set var="username1" value="Hong KilDong"></c:set>
    <c:set var="username2">Lim GukkJeong</c:set>

    <h3>pageContext</h3> <br>
    username1: ${username1} <br>
    username2: ${username2} <br>

    <h3>otherScope</h3> <br>
    pageScope.username1: ${pageScope.username1} <br>
    requestScope.username1: ${requestScope.username1} <br>

    <h3>Set scope</h3> <br>
    <c:set var="username3" scope="request">Il Jimae</c:set>
    pageScope.username3: ${pageScope.username3} <br>
    requestScope.username3: ${requestScope.username3} <br>

    <%
        MyMember myMember = new MyMember();
        myMember.setNo(100);
        myMember.setName("Hong KilDong");
        request.setAttribute("myMember", myMember);
    %>

    <h3>Set member field</h3>
    set prev: ${myMember.name} <br>
    <c:set target="${myMember}" property="name" value="Lim GukkJeong"></c:set>
    set after: ${myMember.name} <br>

    <h3>Remove</h3>
    <c:set var="username4" value="Hong KilDong"></c:set>
    remove prev: ${username4} <br>
    <c:remove var="username4"></c:remove>
    remove after: ${username4} <br>

    <h3>Condition</h3>
    <c:if test="${10 > 20}" var="falseValue">10 is bigger than 20.</c:if> <br>
    <c:if test="${10 < 20}" var="trueValue">10 is smaller than 20.</c:if> <br>
    falseValue: ${falseValue} <br>
    trueValue: ${trueValue} <br>

    <h3>Switch</h3>
    <c:set var="userid" value="admin"></c:set>
    <c:choose>
        <c:when test="${userid == 'hong'}">
            Welcome Hong! <br>
        </c:when>
        <c:when test="${userid == 'lim'}">
            Welcome Lim! <br>
        </c:when>
        <c:when test="${userid == 'admin'}">
            Welcome admin! <br>
        </c:when>
        <c:otherwise>
            Not registered user. <br>
        </c:otherwise>
    </c:choose>

    <h3>foreach</h3>
    <%
        request.setAttribute("nameList",
            new String[]{"Hong KilDong", "Lim GukkJeong", "Li Jimae"});
    %>
    <ul>
        <c:forEach var="name" items="${nameList}">
            <li>${name}</li>
        </c:forEach>
    </ul>

    <h3>forTokens</h3>
    <%
        request.setAttribute("tokens", "v1=20&v2=30&op=+");
    %>
    <c:forTokens var="item" delims="&" items="${tokens}">
        <li>${item}</li>
    </c:forTokens>

    <h3>url</h3>
    <c:url var="calcUrl" value="http://localhost:8080/calc">
        <c:param name="a" value="20"></c:param>
        <c:param name="b" value="30"></c:param>
    </c:url>
    <a href="${calcUrl}">Calculate!</a>


</body>
</html>
