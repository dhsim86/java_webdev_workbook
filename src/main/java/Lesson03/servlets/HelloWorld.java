package Lesson03.servlets;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Dongho on 2017. 3. 1..
 */
public class HelloWorld implements Servlet {

    ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init() called.");
        this.servletConfig = servletConfig;
    }

    @Override
    public void destroy() {
        System.out.println("destroy() called.");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException {

        System.out.println("service() called.");
    }

    @Override
    public ServletConfig getServletConfig() {

        System.out.println("getServletConfig() called.");
        return this.servletConfig;
    }

    @Override
    public String getServletInfo() {

        System.out.println("getServletInfo() called.");
        return "version=1.0;autho=donghosim;copyright=donghosim 2013";
    }
}
