package com.zawadzki.dbcon.ex2;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class QueryTests {

  private Jdbi jdbi;

  @BeforeEach
  void setupJDBI() {
    jdbi = Jdbi.create(
            "jdbc:postgresql://localhost:5432/example1",
            "example1",
            "example1");
  }


  /**
   * Select all products and use .mapToMap() method after create query so it will create list with maps
   * and each map will column(key) : object(value)
   * assert size of list and check each map in list contains 4 columns
   */
  @Test
  void selectAllProducts() {

  }


  /**
   * Select product with highest price, you can use here .findFirst() method which returns optional
   * also after query you can use mapTo(T) to get only one column values
   * assert name of that product
   */
  @Test
  void selectNameOfProductWithHighestPrice() {

  }

  /**
   * Select products with price higher than and assert that list contains "computer", "bike", "laptop"
   * bind your price as position param to sql query
   */
  @Test
  void selectNamesWherePriceIsHigherThan200() {

  }

  /**
   * Select products with price higher than and from electronics category
   * assert that list contains "computer", "laptop"
   * bind your price and category as named as param to sql query
   */
  @Test
  void selectNamesWherePriceIsHigherThan200AndCategoryIsElectronics() {

  }
}
