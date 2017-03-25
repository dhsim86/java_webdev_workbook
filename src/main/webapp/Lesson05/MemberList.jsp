<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Lesson05.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>Member List</title>
</head>
<body>
    <jsp:include page="Header.jsp"/>
    <h1>Member List</h1>
    <p><a href='add.do'> New Member </a></p>
    <jsp:useBean id="memberList"
                scope="request"
                class="java.util.ArrayList"
                type="java.util.List<Lesson05.Member>"/>
    <%
        //List<Member> memberList = (List<Member>)request.getAttribute("memberList");

        List<Member> dummyList = new ArrayList<Member>();
        for (Member member : dummyList) {
    %>
    <%=member.getNo()%>
    <a href='update.do?no=<%=member.getNo()%>'><%=member.getName()%></a>
    <%=member.getEmail()%>
    <%=member.getCreatedDate()%>
    <a href='delete.do?no=<%=member.getNo()%>'>[Delete]</a><br>
    <%
        }
    %>

    <c:forEach var="member" items="${memberList}">
        ${member.no}
        <a href="update.do?no=${member.no}">${member.name}</a>
        ${member.email}
        ${member.createdDate}
        <a href="delete.do?no=${member.no}">[Delete]</a><br>
    </c:forEach>

    <jsp:include page="Tail.jsp"/>
</body>
</html>
