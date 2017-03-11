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
 * Created by Dongho on 2017. 3. 7..
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {

            ServletContext sc = this.getServletContext();

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection((Connection) sc.getAttribute("conn"));

            int result = memberDao.delete(Integer.parseInt(request.getParameter("no")));

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
