import Lesson05.DBConnectionPool;
import Lesson05.MemberDao;
import org.apache.commons.dbcp.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

/**
 * Created by Dongho on 2017. 3. 25..
 */

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    private BasicDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            ServletContext sc = event.getServletContext();

            dataSource = new BasicDataSource();

            dataSource.setDriverClassName(sc.getInitParameter("driver"));
            dataSource.setUrl(sc.getInitParameter("url"));
            dataSource.setUsername(sc.getInitParameter("username"));
            dataSource.setPassword(sc.getInitParameter("password"));

            MemberDao memberDao = new MemberDao();
            memberDao.setDataSource(dataSource);

            sc.setAttribute("memberDao", memberDao);
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        try { if (dataSource != null) dataSource.close(); } catch (SQLException e) {}
    }
}
