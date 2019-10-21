package com.zawadzki.dbcon.ex2;

import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Map;

public class SimpleJDBI {

  public static void main(String[] args) {
    /**
     * create jdbi object with connection data
     */
    Jdbi jdbi = Jdbi.create(
            "jdbc:postgresql://localhost:5432/example1",
            "example1",
            "example1");

    /**
     * use jdbi with handler to create query and map results from that query
     */
    List<Map<String,Object>> products = jdbi.withHandle(handle ->
            handle.createQuery("select * from products where price > :price")
                    .bind("price", 200)
                    .mapToMap()
                    .list());

    products.forEach(System.out::println);
  }
}
