import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Dongho on 2017. 3. 11..
 */
public class AppInitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("AppInitServlet.init called");
        super.init(servletConfig);

        try {
            ServletContext sc = this.getServletContext();
            Class.forName(sc.getInitParameter("driver"));

            Connection conn = DriverManager.getConnection(
                sc.getInitParameter("url"),
                sc.getInitParameter("username"),
                sc.getInitParameter("password")
            );

            sc.setAttribute("conn", conn);
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        System.out.println("AppInitServlet.destroy called");
        super.destroy();

        Connection conn =
            (Connection)this.getServletContext().getAttribute("conn");

        try {
            if (conn != null && conn.isClosed() == false) {
                conn.close();
            }
        }
        catch (Exception e) {

        }
    }
}
