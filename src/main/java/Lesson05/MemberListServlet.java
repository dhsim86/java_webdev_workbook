package Lesson05;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Dongho on 2017. 3. 1..
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {

            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            request.setAttribute("memberList", memberDao.selectList());
            request.setAttribute("viewUrl", "/Lesson05/MemberList.jsp");
        }
        catch (Exception e) {

            throw new ServletException(e);
        }
    }
}
