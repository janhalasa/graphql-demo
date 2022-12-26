package com.halasa.graphqldemo.controller;

import com.halasa.graphqldemo.api.Car;
import com.halasa.graphqldemo.api.Person;
import com.halasa.graphqldemo.entity.PersonEntity;
import com.halasa.graphqldemo.mapper.CarMapper;
import com.halasa.graphqldemo.mapper.PersonMapper;
import com.halasa.graphqldemo.repository.CarRepository;
import com.halasa.graphqldemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public PersonController(
            PersonRepository personRepository,
            PersonMapper personMapper,
            CarRepository carRepository, CarMapper carMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @QueryMapping
    public List<Person> findAllPersons() {
        return this.personMapper.entitiesToApi(this.personRepository.findAll());
    }

    @MutationMapping
    public Person createPerson(
            String givenNames,
            String surname,
            LocalDate birthdate) {

        PersonEntity entity = this.personRepository.persist(new PersonEntity(
                givenNames,
                surname,
                birthdate
        ));
        return this.personMapper.entityToApi(entity);
    }

    @SchemaMapping(typeName = "Post", field = "author")
    public List<Car> carsOfPerson(Person person) {
        return this.carMapper.entitiesToApi(this.carRepository.getByOwner(person.id()));
    }

    @BatchMapping
    public Map<Person, List<Car>> carsByPersons(List<Person> persons) {
        List<Long> ownerIds = persons.stream()
                .map(Person::id)
                .collect(Collectors.toList());
        Map<Long, Person> idToPersonMap = persons.stream()
                .collect(Collectors.toMap(
                        Person::id,
                        person -> person
                ));
        return this.carRepository.mapByOwners(ownerIds).entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> idToPersonMap.get(entry.getKey()),
                        entry -> this.carMapper.entitiesToApi(entry.getValue())
                ));
    }
}
