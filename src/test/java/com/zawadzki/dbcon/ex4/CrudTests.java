package com.zawadzki.dbcon.ex4;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrudTests {

  private Jdbi jdbi;

  @BeforeEach
  void setupJDBI() {
    jdbi = Jdbi.create(
            "jdbc:postgresql://localhost:5432/example1",
            "example1",
            "example1");
    jdbi.installPlugin(new SqlObjectPlugin());

  }


  /**
   * here we get stream of Client objects from db all mapping is done by JDBI constructor annotations
   */
  @Test
  void selectAllClientsUsingConstructorMapper() throws Exception {
    assertThat(jdbi.withExtension(ClientDao.class, ClientDao::getClients)).hasSize(4);
  }

  @Test
  void selectClientById() {
    assertThat(jdbi.withExtension(ClientDao.class, dao -> dao.getClientById(1))
            .orElse(new Client()).getId()).isEqualTo(1);
  }

  @Test
  void addClientAndRemoveIt() {
    Client client = new Client().setName("insertedInCrudTest").setLastname("last").setAddress("address").setCompany("company");

    Boolean inserted = jdbi.withExtension(ClientDao.class, dao -> dao.insertClient(client));
    assertThat(inserted).isTrue();

    Integer id = jdbi.withExtension(ClientDao.class, ClientDao::getClients)
            .filter(it -> it.getName().equalsIgnoreCase("insertedInCrudTest"))
            .findFirst().orElse(new Client()).getId();

    Boolean deleted = jdbi.withExtension(ClientDao.class, dao -> dao.deleteById(id));
    assertThat(deleted).isTrue();
  }
}
