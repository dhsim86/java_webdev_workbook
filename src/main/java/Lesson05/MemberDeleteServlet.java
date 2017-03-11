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
import java.sql.PreparedStatement;

/**
 * Created by Dongho on 2017. 3. 7..
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            ServletContext sc = this.getServletContext();

            conn = (Connection) sc.getAttribute("conn");
            stmt = conn.prepareStatement(
                "delete from members" +
                " where mno = ?"
            );
            stmt.setInt(1, Integer.parseInt(request.getParameter("no")));

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
