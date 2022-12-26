package com.halasa.graphqldemo.mapper;

import com.halasa.graphqldemo.api.Car;
import com.halasa.graphqldemo.entity.CarEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public Car entityToApi(CarEntity entity) {
        return new Car(
                entity.getId(),
                entity.getMake(),
                entity.getModel(),
                entity.getProductYear(),
                entity.getColor(),
                entity.getVin()
        );
    }

    public List<Car> entitiesToApi(Collection<CarEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::entityToApi)
                .collect(Collectors.toList());
    }
}
