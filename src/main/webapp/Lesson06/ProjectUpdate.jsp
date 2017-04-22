<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
    <title>project info</title>
</head>
<body>
    <jsp:include page="../Lesson05/Header.jsp" />
    <h1>project info</h1>
    
    <form action="update.do" method="post">
    
        number: <input type="text" name="no" value="${project.no}" readonly> <br>
        name: <input type="text" name="title" value="${project.title}"> <br>
        description: <input type="text" name="content" value="${project.content}"> <br>
        start_date: <input type="date" name="startdate" value="${project.startDate}"> <br>
        end_date: <input type="date" name="enddate" value="${project.endDate}"> <br>
        tag: <input type="text" name="tags" value="${project.tags}"> <br>
        status: 
            <select name="state">
                <option value="0" ${project.state == 0 ? "selected" : ""}> ready </option>
                <option value="1" ${project.state == 1 ? "selected" : ""}> running </option>
                <option value="2" ${project.state == 2 ? "selected" : ""}> finished </option>
            </select> <br>
        registered date: ${project.createdDate} <br>
        
        <input type="submit" value="save">
        <input type="button" value="delete" 
            onclick="location.href='delete.do?no=${project.no}'">
        <input type="button" value="cancel" onclick="location.href='list.do'">
    
    </form>
    <jsp:include page="../Lesson05/Tail.jsp" />
</body>

</html>