package com.zawadzki.dbcon.ex4;

import com.zawadzki.dbcon.ex3.Product;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CrudToDoTests {

  /**
   * write CRUD for products using DAO class and JDBI sql objects
   *
   * write:
   * 1. Select all
   * 2. Select by id
   * 3. Select by name
   * 4. Select by category
   * 5. Update product
   * 6. Insert Product
   * 7. Delete product
   */

  private Jdbi jdbi;

  @BeforeEach
  void setupJDBI() {
    jdbi = Jdbi.create(
            "jdbc:postgresql://localhost:5432/example1",
            "example1",
            "example1");
    jdbi.installPlugin(new SqlObjectPlugin());

  }

  @Test
  void getAll(){
    Stream<Product> productStream = jdbi.withExtension(ProductsDao.class, ProductsDao::getAll);
    assertThat(productStream.count()).isEqualTo(9);
  }

  @Test
  void getAllById() {
    Product product = jdbi.withExtension(ProductsDao.class, it -> it.getById(1))
            .orElse(new Product());

    assertThat(product.getId()).isNotNull();
  }

  @Test
  void getAllByName() {
    Stream<Product> productStream = jdbi.withExtension(ProductsDao.class,
            it -> it.getAllByName("bike"));

    assertThat(productStream.allMatch(
            it -> it.getName().equalsIgnoreCase("bike"))).isTrue();
  }

  @Test
  void getAllByCategory() {
    Stream<Product> productStream = jdbi.withExtension(ProductsDao.class,
            it -> it.getAllByCategory("electronics"));

    assertThat(productStream.allMatch(
            it -> it.getCategory().equalsIgnoreCase("electronics"))).isTrue();
  }

  @Test
  void updateProduct(){
    Boolean updated = jdbi.withExtension(ProductsDao.class, it ->
            it.updateproduct(
                    new Product()
                    .setName("update1")
                    .setCategory("u1")
                    .setPrice(BigDecimal.valueOf(111L))
                    .setId(1)
            ));

    assertThat(updated).isTrue();
  }

  @Test
  void insertProduct() {
    Boolean inserted = jdbi.withExtension(ProductsDao.class, it -> it.insertProduct(
            new Product()
                    .setName("update1")
                    .setCategory("u1")
                    .setPrice(BigDecimal.valueOf(111L))
                    .setId(1)
    ));

    assertThat(inserted).isTrue();
  }
}
