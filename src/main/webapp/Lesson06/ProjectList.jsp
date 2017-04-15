<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Lesson06.Project" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>

<head>
    <title>Project List</title>
</head>

<body>
    <jsp:include page="../Lesson05/Header.jsp"/>
    <h1> Project List </h1>
    
    <p><a href='add.do'> New Project </a>
    
    <table border="1">
        <tr>
            <th> Number </th>
            <th> Title </th>
            <th> Start Date </th>
            <th> End Date </th>
            <th> Status </th>
            <th> </th>
        </tr>
            
        <c:forEach var="project" items="${projectList}">
            <tr>
                <td> ${project.no} </td>
                <td> <a href="update.do?no=${project.no}">${project.title}</a> </td>
                <td> ${project.startDate} </td>
                <td> ${project.endDate} </td>
                <td> ${project.state} </td>
                <td> <a href="delete.do?no=${project.no}">[Delete]</a>
            </tr>
        </c:forEach>
        
    </table>
    
    <jsp:include page="../Lesson05/Tail.jsp"/>
    
</body>

</html>