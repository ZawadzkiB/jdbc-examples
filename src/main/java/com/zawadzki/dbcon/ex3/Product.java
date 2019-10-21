package com.zawadzki.dbcon.ex3;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.math.BigDecimal;

public class Product {

  private Integer id;
  private String name;
  private String category;
  private BigDecimal price;

  @JdbiConstructor
  public Product(@ColumnName("id") Integer id,
                 @ColumnName("name") String name,
                 @ColumnName("category") String category,
                 @ColumnName("price") BigDecimal price) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.price = price;
  }

  public Product() {
  }

  public Integer getId() {
    return id;
  }

  public Product setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public Product setCategory(String category) {
    this.category = category;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Product setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

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
