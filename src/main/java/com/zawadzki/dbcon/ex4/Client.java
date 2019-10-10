package com.zawadzki.dbcon.ex4;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class Client {

  private Integer id;
  private String name;
  private String lastname;
  private String address;
  private String company;

  public Client() {
  }

  @JdbiConstructor
  public Client(@ColumnName("id") Integer id,
                @ColumnName("name") String name,
                @ColumnName("lastname") String lastname,
                @ColumnName("address") String address,
                @ColumnName("company") String company) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.company = company;
  }

  public Integer getId() {
    return id;
  }

  public Client setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Client setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public Client setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public Client setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getCompany() {
    return company;
  }

  public Client setCompany(String company) {
    this.company = company;
    return this;
  }

  @Override
  public String toString() {
    return "Client{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", company='" + company + '\'' +
            '}';
  }

}
