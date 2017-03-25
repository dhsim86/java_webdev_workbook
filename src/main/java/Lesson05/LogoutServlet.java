package Lesson05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dongho on 2017. 3. 11..
 */
@WebServlet("/auth/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();

        request.setAttribute("viewUrl", "redirect:login.do");
    }
}
