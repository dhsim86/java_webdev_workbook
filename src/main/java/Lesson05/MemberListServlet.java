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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dongho on 2017. 3. 1..
 */
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
            List<Member> memberList = new ArrayList<>();

            while(rs.next()) {
                memberList.add(new Member()
                    .setNo(rs.getInt("mno"))
                    .setName(rs.getString("mname"))
                    .setEmail(rs.getString("email"))
                    .setCreatedDate(rs.getDate("cre_date"))
                );
            }

            request.setAttribute("memberList", memberList);

            RequestDispatcher rd = request.getRequestDispatcher(
                    "/Lesson05/MemberList.jsp"
            );
            rd.include(request, response);
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
