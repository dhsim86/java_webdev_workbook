package Lesson05;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dongho on 2017. 3. 7..
 */
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
            request.setAttribute("viewUrl", "/Lesson05/MemberUpdate.jsp");
        }
        catch (Exception e) {

            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            ServletContext sc = this.getServletContext();

            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
            Member member = (Member)request.getAttribute("member");

            int result = memberDao.update(member);

            request.setAttribute("viewUrl", "redirect:list.do");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
