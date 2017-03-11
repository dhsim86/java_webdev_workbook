<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Lesson05.Member" %>
<%@ page import="java.util.*" %>
<%
    request.setAttribute("scores", new int[]{90,80,70,100});

    List<String> nameList = new LinkedList<String>();
    nameList.add("Hong KilDong");
    nameList.add("Lim Gukkjeong");
    nameList.add("Il jimae");
    request.setAttribute("nameList", nameList);

    Map<String, String> map = new HashMap<String, String>();
    map.put("s01", "Hong KilDong");
    map.put("s02", "Lim Gukkjeong");
    map.put("s03", "Il jimae");
    request.setAttribute("map", map);

    Member member = new Member()
        .setNo(100)
        .setName("Hong KilDong")
        .setEmail("hong@test.com");
    request.setAttribute("member", member);

//    request.setAttribute("myRB", ResourceBundle.getBundle("MyResourceBundle"));
%>
<!DOCTYPE html>
<html>
<head>
    <title>EL Test</title>
</head>
<body>

    score[1]: ${scores[1]} <br>
    nameList[1]: ${nameList[1]} <br>
    map[s02]: ${map.s02} <br>
    member.getEmail: ${member.email} <br>
//    myRB.OK: ${myRB.OK} <br>

</body>
</html>
