package Lesson04;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Dongho on 2017. 3. 1..
 */
@WebServlet("/member/Lesson04/list")
public class MemberListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            ServletContext sc = this.getServletContext();

            conn = (Connection) sc.getAttribute("conn");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "select mno, mname, email, cre_date" +
                    " from members" +
                    " order by mno asc"
            );

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>member list</title></head>");
            out.println("<body><h1>member list</h1>");
            out.println("<p><a href='add'>new member</a></p>");

            while(rs.next()) {
                out.println(
                        rs.getInt("mno") + ", " +
                        "<a href='update?no=" + rs.getInt("mno") + "'>" +
                        rs.getString("mname") + "</a>, " +
                        rs.getString("email") + ", " +
                        rs.getDate("cre_date") +
                        "<a href='delete?no=" + rs.getInt("mno") + "'>" +
                        "[delete]" + "</a>" + "<br>"
                );
            }
            out.println("</body></html>");
        }
        catch (Exception e) {

            throw new ServletException(e);
        }
        finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
    }
}
