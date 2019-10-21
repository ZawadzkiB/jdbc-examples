package com.zawadzki.dbcon.ex4;

import com.zawadzki.dbcon.ex3.Product;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.stream.Stream;

@RegisterConstructorMapper(Product.class)
public interface ProductsDao {

  @SqlQuery("select * from products")
  Stream<Product> getAll();

  @SqlQuery("select * from products where id=:id")
  Optional<Product> getById(@Bind("id") Integer id);

  @SqlQuery("select * from products where name=:name")
  Stream<Product> getAllByName(@Bind("name") String name);

  @SqlQuery("select * from products where category=:category")
  Stream<Product> getAllByCategory(@Bind("category") String category);

  @SqlUpdate("INSERT INTO products(name, category, price) VALUES (:name, :category, :price)")
  Boolean insertProduct(@BindBean Product product);

  @SqlUpdate("update products set name=:name, category=:category, price=:price where id=:id")
  Boolean updateProduct(@BindBean Product product);

  @SqlUpdate("delete from products where id = :id")
  Boolean deleteProduct(@Bind("id") Integer id);
}
