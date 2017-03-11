<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>register member</title>
</head>
<body>
    <jsp:include page="Header.jsp" />
    <h1>register member</h1>

    <form action="add" method="post">

        name: <input type="text" name="name"> <br>
        email: <input type="text" name="email"> <br>
        password: <input type="password" name="password"> <br>
        <input type="submit" value="register">
        <input type="reset" value="clear">

    </form>
    <jsp:include page="Tail.jsp" />
</body>
</html>
