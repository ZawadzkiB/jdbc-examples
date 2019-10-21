package com.zawadzki.dbcon.ex3;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
  @Override
  public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Product()
            .setId(rs.getInt("id"))
            .setName(rs.getString("name"))
            .setCategory(rs.getString("category"))
            .setPrice(rs.getBigDecimal("price"));
  }
}
