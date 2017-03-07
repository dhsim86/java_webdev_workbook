package Lesson04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Dongho on 2017. 3. 1..
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html><head><title>register member</title></head>");
        out.println("<body><h1>register member</h1>");
        out.println("<form action='add' method='post'>");
        out.println("<input type='text' name='name'> <br>");
        out.println("<input type='text' name='email'> <br>");
        out.println("<input type='password' name='password'> <br>");
        out.println("<input type='submit' value='register'>");
        out.println("<input type='reset' value='clear'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            request.setCharacterEncoding("UTF-8");
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/studydb?useUnicode=true&characterEncoding=UTF-8",
                    "study",
                    "study"
            );

            stmt = conn.prepareStatement(
                "insert into members(email, pwd, mname, cre_date, mod_date)" +
                " values(?, ?, ?, now(), now())"
            );
            stmt.setString(1, request.getParameter("email"));
            stmt.setString(2, request.getParameter("password"));
            stmt.setString(3, request.getParameter("name"));
            stmt.executeUpdate();

            response.sendRedirect("list");

            /*response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Result registering member</title></head>");
            out.println("<body>");
            out.println("<p>register success!</p>");
            out.println("</body></html>");

            response.addHeader("Refresh", "1;url=list");*/
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
