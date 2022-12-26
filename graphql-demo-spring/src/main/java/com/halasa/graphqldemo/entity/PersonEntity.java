package com.halasa.graphqldemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Proxy;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "Person")
@Proxy(lazy = false)
public class PersonEntity {

    @Id
    private Long id;

    private String givenNames;
    private String surname;
    private LocalDate birthdate;

    @OneToMany(mappedBy = "owner")
    private Set<CarEntity> cars;

    @OneToMany(mappedBy = "person")
    private Set<AddressEntity> addresses;

    public PersonEntity() {
    }

    public PersonEntity(String givenNames, String surname, LocalDate birthdate) {
        this.givenNames = givenNames;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGivenNames() {
        return givenNames;
    }

    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }

    public Set<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressEntity> addresses) {
        this.addresses = addresses;
    }
}
