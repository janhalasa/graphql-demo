package com.halasa.graphqldemo.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record Person (
    Long id,
    String givenNames,
    String surname,
    LocalDate birthdate,
    List<Car> cars,
    List<Address> addresses
) {}
