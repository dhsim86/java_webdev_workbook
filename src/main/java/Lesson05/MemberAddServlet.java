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
 * Created by Dongho on 2017. 3. 1..
 */
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setAttribute("viewUrl", "/Lesson05/MemberAdd.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            Member member = (Member)request.getAttribute("member");
            int result = memberDao.insert(member);

            request.setAttribute("viewUrl", "redirect:list.do");
        }
        catch (Exception e) {

            throw new ServletException(e);
        }
    }
}
