package com.zawadzki.dbcon.ex3;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {
  @Override
  public Client map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new Client(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("lastname"),
            rs.getString("address"),
            rs.getString("company")
    );
  }
}
