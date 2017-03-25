package Lesson06;

import Lesson05.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dongho on 2017. 3. 25..
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        String servletPath = request.getServletPath();

        try {

            String pageControllerPath = null;

            if ("/member/list.do".equals(servletPath)) {
                pageControllerPath = "/member/list";
            }
            else if ("/member/add.do".equals(servletPath)) {
                pageControllerPath = "/member/add";

                if (request.getParameter("email") != null) {
                    request.setAttribute("member",
                        new Member().setEmail(request.getParameter("email"))
                                    .setNo(Integer.parseInt(request.getParameter("no")))
                                    .setName(request.getParameter("name"))
                    );
                }
            }
            else if ("/member/update.do".equals(servletPath)) {
                pageControllerPath = "/member/update";

                if (request.getParameter("email") != null) {
                    request.setAttribute("member",
                            new Member().setEmail(request.getParameter("email"))
                                    .setNo(Integer.parseInt(request.getParameter("no")))
                                    .setName(request.getParameter("name"))
                    );
                }
            }
            else if ("/member/delete.do".equals(servletPath)) {
                pageControllerPath = "/member/delete";
            }
            else if ("/auth/login.do".equals(servletPath)) {
                pageControllerPath = "/auth/login";
            }
            else if ("/auth/logout.do".equals(servletPath)) {
                pageControllerPath = "/auth/logout";
            }

            RequestDispatcher requestDispatcher =
                request.getRequestDispatcher(pageControllerPath);

            requestDispatcher.include(request, response);

            String viewUrl = (String)request.getAttribute("viewUrl");

            if (viewUrl.startsWith("redirect:")) {
                response.sendRedirect(viewUrl.substring(9));
                return;
            }
            else {
                requestDispatcher = request.getRequestDispatcher(viewUrl);
                requestDispatcher.include(request, response);
            }
        }
        catch (Exception e) {

            e.printStackTrace();
            request.setAttribute("error", e);

            RequestDispatcher requestDispatcher =
                request.getRequestDispatcher("/Error.jsp");

            requestDispatcher.forward(request, response);
        }
    }
}
