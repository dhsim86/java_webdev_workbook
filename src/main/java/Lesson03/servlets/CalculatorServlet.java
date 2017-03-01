package Lesson03.servlets;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dongho on 2017. 3. 1..
 */
@WebServlet("/calc")
public class CalculatorServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println("a=" + a + "," + "b=" + b + " calculation results.");
        writer.println("a + b = " + (a + b));
        writer.println("a - b = " + (a - b));
        writer.println("a * b = " + (a * b));
        writer.println("a / b = " + ((float)a / b));
        writer.println("a % b = " + (a % b));
    }
}
