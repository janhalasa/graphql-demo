package com.halasa.graphqldemo.mapper;

import com.halasa.graphqldemo.api.Address;
import com.halasa.graphqldemo.entity.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    private final CountryMapper countryMapper;

    @Autowired
    public AddressMapper(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    public Address entityToApi(AddressEntity entity) {
        return new Address(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getCity(),
                this.countryMapper.entityToApi(entity.getCountry())
        );
    }

    public List<Address> entitiesToApi(Collection<AddressEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::entityToApi)
                .collect(Collectors.toList());
    }
}
