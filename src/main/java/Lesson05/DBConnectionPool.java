package Lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dongho on 2017. 3. 25..
 */
public class DBConnectionPool {

    private String url;
    private String username;
    private String password;

    List<Connection> connectionList = new ArrayList<>();

    public DBConnectionPool(String driver, String url, String username, String password)
        throws Exception {

        this.url = url;
        this.username = username;
        this.password = password;

        Class.forName(driver);
    }

    public Connection getConnection() throws Exception {

        if (connectionList.size() > 0) {

            Connection conn = connectionList.get(0);

            if (conn.isValid(10)) {
                return conn;
            }
        }

        return DriverManager.getConnection(url, username, password);
    }

    public void returnConnection(Connection conn) throws Exception {

        connectionList.add(conn);
    }

    public void closeAll() {

        for (Connection conn : connectionList) {

            try { conn.close(); } catch (Exception e) {}
        }
    }
}
