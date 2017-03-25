import Lesson05.MemberDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Dongho on 2017. 3. 25..
 */

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    private Connection conn;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            ServletContext sc = event.getServletContext();

            Class.forName(sc.getInitParameter("driver"));
            conn = DriverManager.getConnection(
                sc.getInitParameter("url"),
                sc.getInitParameter("username"),
                sc.getInitParameter("password")
            );

            MemberDao memberDao = new MemberDao();
            memberDao.setConnection(conn);

            sc.setAttribute("memberDao", memberDao);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            conn.close();
        } catch (Exception e) {

        }
    }
}
