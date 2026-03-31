package utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataConnection {
    private static final String URL ="jdbc:mysql://localhost:3306/JDBC_JAVA_ADVANCED_PROJECT_FINAL?createDatabaseIfNotExist=true";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="Hoangduc2006";
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";

    public static Connection openConnection(){
        Connection condition;
        try {
            Class.forName(DRIVER);
            condition= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return condition;
    }
}
