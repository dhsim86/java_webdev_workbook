<%--
  Created by IntelliJ IDEA.
  User: Dongho
  Date: 2017. 3. 11.
  Time: PM 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="member"
             scope="session"
             class="Lesson05.Member"
             type="Lesson05.Member" />
<%
    //Member member = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<div style="background-color:#00008B; color:#FFFFFF; height:20px; padding:5px;">
    SPMS(Simple Project Management System)
    <span style="float:right;">
        <a style="color:white;"
           href="<%=request.getContextPath()%>/project/list.do">Project</a>
        <a style="color:white;"
           href="<%=request.getContextPath()%>/member/list.do">Member</a>
        <%
            if (member.getEmail() != null) {
        %>
        <%=member.getName()%>
        <a style="color:white;"
           href="<%=request.getContextPath()%>/auth/logout.do">Logout</a>
        <%
            }
            else {
        %>
        <a style="color:white;"
           href="<%=request.getContextPath()%>/auth/login.do">Login</a>
        <%
            }
        %>
    </span>
</div>
