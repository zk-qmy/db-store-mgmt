package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;


public class DatabaseConn {
    private static DatabaseConn instance;
    private Connection connection;

    private DatabaseConn(){
        try{
            Properties config = new Properties();
            config.load(new FileInputStream("resources/config.properties"));

            String url = config.getProperty("db.url");
            String username = config.getProperty("db.username");
            String password = config.getProperty("db.password");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection(url, username, password);
                // add DAO here;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                //throw new SQLException("Database Driver not found", e);
            } catch (SQLException e) {
                e.printStackTrace();
                //throw new SQLException("Database is not ready", e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //throw new SQLException("Cannot load config",e);
        }
    }

    public static DatabaseConn getInstance(){
        if (instance == null) {
            instance = new DatabaseConn();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DatabaseConn();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
    public void closeConn(Connection connection) {
        if (connection!= null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}