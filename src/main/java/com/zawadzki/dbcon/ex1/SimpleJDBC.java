package com.zawadzki.dbcon.ex1;

import java.sql.*;

public class SimpleJDBC {

  /**
   * If you already have postgres db running make some simple sql calls to it.
   * You have to setup postgres driver to communicate with db server.
   * <p>
   * https://mvnrepository.com/artifact/org.postgresql/postgresql
   * download it and add as maven dependency
   */


  public static void main(String[] args) {

    Connection conn = null;
    Statement stmt = null;

    try {
      /**
       *register driver
       */
      Class.forName("org.postgresql.Driver");

      /**
       * create connection
       */
      conn = DriverManager.getConnection(
              "jdbc:postgresql://localhost:5432/example1",
              "example1",
              "example1");

      /**
       * create statement and run SQL query
       */
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from products");

      /**
       * print result set to console
       * result set have also method getType and you can specify which column you want to get
       */
      while (rs.next()) {

//        int numColumns = rs.getMetaData().getColumnCount();
//        for (int i = 1; i <= numColumns; i++) {
//          System.out.println("COLUMN " + i + " = " + rs.getObject(i));
//        }

        System.out.println("Name: " + rs.getString("name"));

      }

      /**
       * clean connections and statements
       * connection create statements and statements results sets if connection won't be closed then
       * next call using this connection can have some garbage in result set or statement that why we need to close everything
       */
      rs.close();
      stmt.close();
      conn.close();

    } catch (SQLException se) {
      se.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmt != null)
          stmt.close();
      } catch (SQLException se2) {
        se2.printStackTrace();
      }
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
  }
}

