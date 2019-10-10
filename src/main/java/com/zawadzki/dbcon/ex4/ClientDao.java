package com.zawadzki.dbcon.ex4;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;
import java.util.stream.Stream;

@RegisterConstructorMapper(Client.class)
public interface ClientDao {

  @SqlQuery("select * from client")
  Stream<Client> getClients();

  @SqlQuery("select * from client where id = :id")
  Optional<Client> getClientById(@Bind("id") Integer id);

  @SqlUpdate("insert into client(name, lastname, address, company) values (:name, :lastname, :address, :company)")
  Boolean insertClient(@BindBean Client client);

  @SqlUpdate("delete from client where id = :id")
  Boolean deleteById(@Bind("id") Integer id);
}
