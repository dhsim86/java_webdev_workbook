<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 6:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>member info</title>
</head>
<body>
    <h1>member info</h1>

    <form action="update" method="post">

        number: <input type="text" name="no" value="${member.no}" readonly><br>
        name: <input type="text" name="name" value="${member.name}"> <br>
        email: <input type="text" name="email" value="${member.email}"> <br>
        registered date: ${member.createdDate} <br>

        <input type="submit" value="save">
        <input type="button" value="delete"
           onclick="location.href='delete?no=${member.no}'">
        <input type="button" value="cancel"
           onclick="location.href='list'">

    </form>
</body>
</html>
