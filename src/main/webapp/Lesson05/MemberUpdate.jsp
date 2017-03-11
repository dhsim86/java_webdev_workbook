<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 6:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="member"
             scope="request"
             class="Lesson05.Member"
             type="Lesson05.Member" />
<!DOCTYPE html>
<html>
<head>
    <title>member info</title>
</head>
<body>
    <h1>member info</h1>

    <form action="update" method="post">

        number: <input type="text" name="no" value="<%=member.getNo()%>" readonly><br>
        name: <input type="text" name="name" value="<%=member.getName()%>"> <br>
        email: <input type="text" name="email" value="<%=member.getEmail()%>"> <br>
        registered date: <%=member.getCreatedDate()%> <br>

        <input type="submit" value="save">
        <input type="button" value="delete"
           onclick="location.href='delete?no=<%=member.getNo()%>'">
        <input type="button" value="cancel"
           onclick="location.href='list'">

    </form>
</body>
</html>
