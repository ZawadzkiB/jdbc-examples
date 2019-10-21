package com.zawadzki.dbcon.ex3;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryWithMapperToDoTests {

  /**
   * write some tests using constructor and row mappers you can find examples in QueryWithMapperTests.class
   *
   * write some tests:
   * 1. Select all products using row mapper
   * 2. Select all products using constructor mapper
   * 3. Select one product with id = (choose one) and use constructor mapper for that
   * 4. Update one product with id = (choose one) use bindBean to pass parameters
   * 5. Insert one product, use bindBean to pass parameters
   */

  private Jdbi jdbi;

  @BeforeEach
  void setupJDBI() {
    jdbi = Jdbi.create(
            "jdbc:postgresql://localhost:5432/example1",
            "example1",
            "example1");

    jdbi.registerRowMapper(ConstructorMapper.factory(Product.class));
  }

  @Test
  void selectAllProductsUsingConstructorMapper() {
    List<Product> clients = jdbi.withHandle(handle ->
            handle.createQuery("select * from products")
                    .mapTo(Product.class)
                    .list());

    assertThat(clients).hasSize(7);
  }

  @Test
  void selectProductWithIdUsingRowMapper() {
    List<Product> clients = jdbi.withHandle(handle ->
            handle.createQuery("select * from products where id=:id")
                    .bind("id", 2)
                    .map(new ProductMapper())
                    .list());

    assertThat(clients).hasSize(1);
  }

  @Test
  void updateProductName() {
    int updatedRows = jdbi.withHandle(handle ->
            handle.createUpdate("update products set name=:name where id=:id")
                    .bindBean(new Product().setId(1).setName("something_new"))
                    .execute());
    assertThat(updatedRows).isEqualTo(1);
  }

  @Test
  void insertClient() {
    int updatedRows = jdbi.withHandle(handle ->
            handle.createUpdate("INSERT INTO products(name, category, price) VALUES (:name, :category, :price)")
                    .bindBean(new Product().setName("newProduct1").setCategory("cat1").setPrice(BigDecimal.valueOf(200L)))
                    .execute());
    assertThat(updatedRows).isEqualTo(1);
  }
}
