package Lesson05;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ServletContext sc = this.getServletContext();

            conn = (Connection) sc.getAttribute("conn");

            stmt = conn.prepareStatement(
                "insert into members(email, pwd, mname, cre_date, mod_date)" +
                " values(?, ?, ?, now(), now())"
            );
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("password"));
            stmt.setString(3, request.getParameter("name"));
            stmt.executeUpdate();

            response.sendRedirect("list");
        }
        catch (Exception e) {

            request.setAttribute("error", e);

            RequestDispatcher rd = request.getRequestDispatcher(
                "/Lesson05/Error.jsp"
            );
            rd.forward(request, response);
        }
        finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }
}
