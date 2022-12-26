package com.halasa.graphqldemo.api;

public record Address (
    Long id,
    String street,
    String number,
    String city,

    Country country
) {}
