package com.zawadzki.dbcon.ex1;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessSimpleJDBC {

  /**
   * write select for products which price is higher that minimal price variable in main method
   * and create list of products from result set, use toString method from product to print it to console
   */

  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;

    BigDecimal minimalPrice = BigDecimal.valueOf(200L);

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
      ResultSet rs = stmt.executeQuery("select * from products where price > " + minimalPrice.toString());

      /**
       * print result set to console
       * result set have also method getType and you can specify which column you want to get
       */

      List<Product> products = new ArrayList<>();

      while (rs.next()) {

        Product product = new Product();

        product.id = rs.getInt("id");
        product.name = rs.getString("name");
        product.price = rs.getBigDecimal("price");
        product.category = rs.getString("category");

        products.add(product);
      }

      products.forEach(System.out::println);

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


  private static class Product {
    Integer id;
    String name;
    String category;
    BigDecimal price;

    @Override
    public String toString() {
      return "Product{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", category='" + category + '\'' +
              ", price=" + price +
              '}';
    }
  }
}
