package Lesson04;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Dongho on 2017. 3. 1..
 */
@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException {

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
                        rs.getDate("cre_date") + "<br>"
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
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
