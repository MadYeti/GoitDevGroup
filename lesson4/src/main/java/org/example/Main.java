package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws Exception {

    DBConnectionUtil.createDBConnection();
    Connection connection = DBConnectionUtil.getConnection();
    Statement statement = connection.createStatement();
    //get string from file
//    String sql = "SELECT * FROM CLIENTS";
    String sql = "select * from persons left join telephone on persons.id = telephone.person_id";
    ResultSet resultSet = statement.executeQuery(sql);
    ArrayList<Client> clients = new ArrayList<>();
    while(resultSet.next()){
      Client client = new Client();
      client.setId(resultSet.getInt("id"));
      client.setFirstName(resultSet.getString("first_name"));
      client.setLastName(resultSet.getString("second_name"));
      client.setAge(resultSet.getInt("age"));
      client.setMarried(resultSet.getBoolean("married"));
      client.setPersonId(resultSet.getInt("person_id"));
      client.setNumber(resultSet.getString("number"));
      clients.add(client);
    }
    System.out.println(clients);
  }
}