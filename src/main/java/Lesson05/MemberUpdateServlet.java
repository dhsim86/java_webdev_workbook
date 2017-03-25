package Lesson05;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Dongho on 2017. 3. 7..
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            response.setContentType("text/html; charset=UTF-8");
            request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));

            RequestDispatcher rd = request.getRequestDispatcher(
                "/Lesson05/MemberUpdate.jsp"
            );
            rd.include(request, response);
        }
        catch (Exception e) {

            request.setAttribute("error", e);

            RequestDispatcher rd = request.getRequestDispatcher(
                "/Lesson05/Error.jsp"
            );
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            Member member = new Member()
                .setEmail(request.getParameter("email"))
                .setName(request.getParameter("name"))
                .setNo(Integer.parseInt(request.getParameter("no")));

            int result = memberDao.update(member);
            response.sendRedirect("list");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
