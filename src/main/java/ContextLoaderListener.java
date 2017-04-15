import Lesson05.DBConnectionPool;
import Lesson05.MemberDao;
import Lesson05.MySqlMemberDao;
import Lesson06.MemberAddController;
import Lesson06.MemberDeleteController;
import Lesson06.MemberListController;
import Lesson06.MemberLoginController;
import Lesson06.MemberLogoutController;
import Lesson06.MemberUpdateController;

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

            MemberDao memberDao = new MySqlMemberDao();
            memberDao.setDataSource(dataSource);

            sc.setAttribute("/auth/login.do", new MemberLoginController().setMemberDao(memberDao));
            sc.setAttribute("/auth/logout.do", new MemberLogoutController());
            sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
            sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));
            sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
            sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
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
