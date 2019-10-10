package com.zawadzki.dbcon.ex3;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueryWithMapperTests {

    private Jdbi jdbi;

    @BeforeEach
    void setupJDBI() {
      jdbi = Jdbi.create(
              "jdbc:postgresql://localhost:5432/example1",
              "example1",
              "example1");

      /**
       * to use JDBI constructor mappers we need to register it
       */
      jdbi.registerRowMapper(ConstructorMapper.factory(Client.class));
    }


  /**
   * here we get list of Client objects from db all mapping is done by JDBI constructor annotations
   */
  @Test
    void selectAllClientsUsingConstructorMapper() {
      List<Client> clients = jdbi.withHandle(handle ->
              handle.createQuery("select * from client")
                      .mapTo(Client.class)
                      .list());

      assertThat(clients).hasSize(3);
    }

  /**
   * here we get list of Client objects from db, mappings are done using ClientMapper class
   */
  @Test
  void selectAllClientsUsingRowMapper() {
    List<Client> clients = jdbi.withHandle(handle ->
            handle.createQuery("select * from client")
                    .map(new ClientMapper())
                    .list());

    assertThat(clients).hasSize(3);
  }

  /**
   * we can used bindBean method to pass parameters to query,
   * :name should have same name as filed in Client.class same for id
   */
  @Test
  void updateClientName() {
    int updatedRows = jdbi.withHandle(handle ->
            handle.createUpdate("update client set name=:name where id=:id")
            .bindBean(new Client().setId(1).setName("updated"))
            .execute());
    assertThat(updatedRows).isEqualTo(1);
  }

  /**
   * creating new client and inserting it
   */
  @Test
  void insertClient() {
    int updatedRows = jdbi.withHandle(handle ->
            handle.createUpdate("INSERT INTO client(name, lastname, address, company) VALUES (:name, :lastname, :address, :company)")
                    .bindBean(new Client().setName("inserted").setLastname("last").setAddress("address").setCompany("company"))
                    .execute());
    assertThat(updatedRows).isEqualTo(1);
  }


}
