package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Heap {

  public void dbSelect() throws SQLException {
    Connection connection = null;
    Statement statement = null;

    try {
      DBConnectionUtil.createDBConnection();
      connection = DBConnectionUtil.getConnection();
      statement = DBConnectionUtil.createStatement(connection);
      String sqlFromFile = String.join("\n", Files.readAllLines(Paths.get("lesson4/src/main/resources/data.sql")));
//      String sql = "SELECT * FROM CLIENTS WHERE ID = 1";
      ResultSet resultSet = statement.executeQuery(sqlFromFile);
      Client client = new Client();
      while (resultSet.next()){
        client.setId(resultSet.getInt("id"));
        client.setFirstName(resultSet.getString("first_name"));
        client.setLastName(resultSet.getString("second_name"));
        client.setAge(resultSet.getInt("age"));
        client.setMarried(resultSet.getBoolean("married"));
      }
      System.out.println(client);
    } catch (SQLException | IOException e) {
      throw new RuntimeException(e);
    } finally {
      if(statement != null){
        try {
          statement.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
      if(connection != null){
        try {
          connection.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

}
