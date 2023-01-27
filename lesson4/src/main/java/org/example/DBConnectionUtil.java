package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionUtil {
  private static String dbUrl = "jdbc:h2:~/test";
  private static String user = "sa";
  private static String password = "";

  private static Connection connection;

  public static void createDBConnection() throws SQLException {
    connection = DriverManager.getConnection(dbUrl, user, password);
  }

  public static Statement createStatement(Connection connection) throws SQLException {
    return connection.createStatement();
  }

  public static Connection getConnection() {
    return connection;
  }
}
