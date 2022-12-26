package com.halasa.graphqldemo.mapper;

import com.halasa.graphqldemo.api.Country;
import com.halasa.graphqldemo.entity.CountryEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public Country entityToApi(CountryEntity entity) {
        return new Country(
                entity.getCode(),
                entity.getName(),
                entity.getContinent()
        );
    }
}
