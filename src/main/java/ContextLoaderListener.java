import Lesson05.DBConnectionPool;
import Lesson05.MemberDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Dongho on 2017. 3. 25..
 */

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    private DBConnectionPool dbConnectionPool;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            ServletContext sc = event.getServletContext();

            dbConnectionPool = new DBConnectionPool(
                sc.getInitParameter("driver"),
                sc.getInitParameter("url"),
                sc.getInitParameter("username"),
                sc.getInitParameter("password")
            );

            MemberDao memberDao = new MemberDao();
            memberDao.setDbConnectionPool(dbConnectionPool);

            sc.setAttribute("memberDao", memberDao);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        dbConnectionPool.closeAll();
    }
}
