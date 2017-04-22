package Lesson06;
import Lesson05.MemberDao;
import Lesson05.MySqlMemberDao;

import org.apache.commons.dbcp.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import java.sql.SQLException;

/**
 * Created by Dongho on 2017. 3. 25..
 */

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

    @Override
    public void contextInitialized(ServletContextEvent event) {

        try {
            ServletContext sc = event.getServletContext();
            
            String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
            applicationContext = new ApplicationContext(propertiesPath);
            
            BasicDataSource dataSource = (BasicDataSource)applicationContext.getBean("dataSource");
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("study");
            dataSource.setPassword("study");
            dataSource.setUrl("jdbc:mysql://localhost/studydb?useUnicode=true&characterEncoding=UTF-8&useSSL=true&verifyServerCertificate=false");
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        try { 
        	BasicDataSource dataSource = (BasicDataSource)applicationContext.getBean("dataSource");
        	if (dataSource != null) dataSource.close(); 
    	} 
        catch (SQLException e) {}
    }
}
