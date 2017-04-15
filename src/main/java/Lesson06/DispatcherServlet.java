package Lesson06;

import Lesson05.Member;
import Lesson06.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

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
            ServletContext servletContext = this.getServletContext();
            
            HashMap<String, Object> model = new HashMap<>();

            String pageControllerPath = null;
            Controller pageController = (Controller)servletContext.getAttribute(servletPath);
            
            if ("/member/list.do".equals(servletPath)) {
            }
            else if ("/member/add.do".equals(servletPath)) {
                if (request.getParameter("email") != null) {
                    model.put("member",
                        new Member().setEmail(request.getParameter("email"))
                                    .setPassword(request.getParameter("password"))
                                    .setName(request.getParameter("name")));
                }
            }
            else if ("/member/update.do".equals(servletPath)) {
                if (request.getParameter("email") != null) {
                    model.put("member",
                        new Member().setEmail(request.getParameter("email"))
                                    .setNo(Integer.parseInt(request.getParameter("no")))
                                    .setName(request.getParameter("name")));
                }
                else {
                    model.put("no", request.getParameter("no"));
                }
            }
            else if ("/member/delete.do".equals(servletPath)) {
                model.put("no", request.getParameter("no"));
            }
            else if ("/auth/login.do".equals(servletPath)) {
                model.put("session", request.getSession());
                if (request.getMethod().equals("POST")) {
                    
                    model.put("email", request.getParameter("email"));
                    model.put("password", request.getParameter("password"));
                }
            }
            else if ("/auth/logout.do".equals(servletPath)) {
                model.put("session", request.getSession());
            }
            
            String viewUrl = pageController.execute(model);
            
            for (String key : model.keySet()) {
                request.setAttribute(key, model.get(key));
            }
            
            if (viewUrl.startsWith("redirect:")) {
                response.sendRedirect(viewUrl.substring(9));
                return;
            }
            else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewUrl);
                requestDispatcher.include(request, response);
            }
/*
            if ("/member/list.do".equals(servletPath)) {
                pageControllerPath = "/member/list";
            }
            else if ("/member/add.do".equals(servletPath)) {
                pageControllerPath = "/member/add";

                if (request.getParameter("email") != null) {
                    request.setAttribute("member",
                        new Member().setEmail(request.getParameter("email"))
                                    .setPassword(request.getParameter("password"))
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
            }*/
        }
        catch (Exception e) {

            e.printStackTrace();
            request.setAttribute("error", e);

            RequestDispatcher requestDispatcher =
                request.getRequestDispatcher("/Lesson05/Error.jsp");

            requestDispatcher.forward(request, response);
        }
    }
}
