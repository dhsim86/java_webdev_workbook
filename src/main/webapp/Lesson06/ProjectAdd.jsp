<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Register project</title>
</head>

<body>
    <jsp:include page="../Lesson05/Header.jsp" />
    <h1> New Project </h1>

    <form action="add.do" method="post">
    
        name: <input type="text" name="title"> <br>
        description: <input type="text" name="content"> <br>
        start_date: <input type="date" name="startdate"> <br>
        end_date: <input type="date" name="enddate"> <br>
        tag: <input type="text" name="tags"> <br>
        <input type="submit" value="register">
        <input type="button" value="cancel" onclick="location.href='list.do'">
    
    </form>
    
    <jsp:include page="../Lesson05/Tail.jsp" />
</body>

</html>
