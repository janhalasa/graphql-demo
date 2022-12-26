package com.halasa.graphqldemo.mapper;

import com.halasa.graphqldemo.api.Person;
import com.halasa.graphqldemo.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    private final CarMapper carMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public PersonMapper(
            CarMapper carMapper,
            AddressMapper addressMapper) {
        this.carMapper = carMapper;
        this.addressMapper = addressMapper;
    }

    public Person entityToApi(PersonEntity entity) {
        return new Person(
                entity.getId(),
                entity.getGivenNames(),
                entity.getSurname(),
                entity.getBirthdate(),
                this.carMapper.entitiesToApi(entity.getCars()),
                this.addressMapper.entitiesToApi(entity.getAddresses())
        );
    }

    public List<Person> entitiesToApi(Collection<PersonEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::entityToApi)
                .collect(Collectors.toList());
    }
}
