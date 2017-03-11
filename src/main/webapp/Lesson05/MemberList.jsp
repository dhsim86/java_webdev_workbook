<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Lesson05.Member" %>
<!DOCTYPE html>
<html>
<head>
    <title>Member List</title>
</head>
<body>
    <jsp:include page="Header.jsp"/>
    <h1>Member List</h1>
    <p><a href='add'> New Member </a></p>
    <jsp:useBean id="memberList"
                scope="request"
                class="java.util.ArrayList"
                type="java.util.List<Lesson05.Member>"/>
    <%
        //List<Member> memberList = (List<Member>)request.getAttribute("memberList");

        for (Member member : memberList) {
    %>
    <%=member.getNo()%>
    <a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>
    <%=member.getEmail()%>
    <%=member.getCreatedDate()%>
    <a href='delete?no=<%=member.getNo()%>'>[Delete]</a><br>
    <%
        }
    %>
    <jsp:include page="Tail.jsp"/>
</body>
</html>
