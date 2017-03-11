package Lesson05;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Dongho on 2017. 3. 11..
 */
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(
            "/Lesson05/LogInForm.jsp"
        );
        rd.forward(request, response);
    }

    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        try {

            ServletContext sc = this.getServletContext();

            MemberDao memberdao = new MemberDao();
            memberdao.setConnection((Connection)sc.getAttribute("conn"));

            Member member = memberdao.exist(
                request.getParameter("email"),
                request.getParameter("password")
            );

            if (member != null) {
                HttpSession session = request.getSession();
                session.setAttribute("member", member);

                response.sendRedirect("../member/list");
            }
            else {
                RequestDispatcher rd = request.getRequestDispatcher(
                        "/Lesson05/LogInFail.jsp"
                );
                rd.forward(request, response);
            }
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
