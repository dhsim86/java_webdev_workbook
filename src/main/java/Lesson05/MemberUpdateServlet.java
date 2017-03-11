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

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            ServletContext sc = this.getServletContext();

            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                sc.getInitParameter("url"),
                sc.getInitParameter("username"),
                sc.getInitParameter("password")
            );
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                "select mno, email, mname, cre_date from members" +
                " where mno = " + request.getParameter("no")
            );

            response.setContentType("text/html; charset=UTF-8");
            rs.next();

            Member member = new Member()
                    .setNo(Integer.parseInt(request.getParameter("no")))
                    .setEmail(rs.getString("email"))
                    .setName(rs.getString("mname"))
                    .setCreatedDate(rs.getDate("cre_date"));

            request.setAttribute("member", member);

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
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ServletContext sc = this.getServletContext();

            conn = (Connection) sc.getAttribute("conn");
            stmt = conn.prepareStatement(
                "update members set email = ?, mname = ?, mod_date = now()" +
                " where mno = ?"
            );
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("name"));
            stmt.setInt(3, Integer.parseInt(request.getParameter("no")));

            stmt.executeUpdate();
            response.sendRedirect("list");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
        finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }
}
