package com.halasa.graphqldemo.api;

public record Car (
    Long id,
    String make,
    String model,
    Integer productYear,
    String color,
    String vin
) {}
