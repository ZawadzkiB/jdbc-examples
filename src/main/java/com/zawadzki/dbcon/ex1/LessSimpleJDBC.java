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
    PreparedStatement stmt = null;

    BigDecimal minimalPrice = BigDecimal.valueOf(200L);


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
