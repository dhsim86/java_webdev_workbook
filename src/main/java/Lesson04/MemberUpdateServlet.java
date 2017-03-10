package Lesson04;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Dongho on 2017. 3. 7..
 */
@SuppressWarnings("serial")
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

            rs.next();

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<html><head><title>member info</title></head>");
            out.println("<body><h1>member info</h1>");
            out.println("<form action='update' method='post'>");
            out.println("number: <input type='text' name='no' value='" +
                        request.getParameter("no") + "' readonly><br>");
            out.println("name: <input type='text' name='name' value='" +
                        rs.getString("mname") + "'><br>");
            out.println("email: <input type='text' name='email' value='" +
                        rs.getString("email") + "'><br>");
            out.println("registered date: " + rs.getDate("cre_date") + "<br>");
            out.println("<input type='submit' value='save'>");
            out.println("<input type='button' value='delete'" +
                        " onclick='location.href=\"delete?no=" + request.getParameter("no") + "\"'>");
            out.println("<input type='button' value='cancel'" +
                        " onclick='location.href=\"list\"'>");
            out.println("</form>");
            out.println("</body></html>");
        }
        catch (Exception e) {
            throw new ServletException(e);
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

            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                sc.getInitParameter("url"),
                sc.getInitParameter("username"),
                sc.getInitParameter("password")
            );
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
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
