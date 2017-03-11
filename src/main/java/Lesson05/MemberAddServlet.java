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
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        RequestDispatcher rd = request.getRequestDispatcher(
            "/Lesson05/MemberAdd.jsp"
        );

        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection((Connection) sc.getAttribute("conn"));

            Member member = new Member()
                .setEmail(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setName(request.getParameter("name"));

            int result = memberDao.insert(member);
            response.sendRedirect("list");
        }
        catch (Exception e) {

            request.setAttribute("error", e);

            RequestDispatcher rd = request.getRequestDispatcher(
                "/Lesson05/Error.jsp"
            );
            rd.forward(request, response);
        }
    }
}
