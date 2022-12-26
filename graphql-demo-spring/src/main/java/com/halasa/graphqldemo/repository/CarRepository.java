package com.halasa.graphqldemo.repository;

import com.halasa.graphqldemo.entity.CarEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CarRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CarEntity> getByOwner(Long ownerId) {
        Query query = this.em.createQuery("SELECT car FROM Car as car JOIN car.owner AS owner WHERE owner.id = :personId");
        query.setParameter("personId", ownerId);
        return query.getResultList();
    }

    public Map<Long, List<CarEntity>> mapByOwners(List<Long> ownerIds) {
        Query query = this.em.createQuery("SELECT car FROM Car as car JOIN FETCH car.owner AS owner WHERE owner.id IN :personIds");
        query.setParameter("personIds", ownerIds);
        List<CarEntity> resultList = query.getResultList();
        return resultList.stream()
                .collect(Collectors.toMap(
                        carEntity -> carEntity.getOwner().getId(),
                        carEntity -> Collections.singletonList(carEntity),
                        (list1, list2) -> {
                            ArrayList<CarEntity> merged = new ArrayList<>();
                            merged.addAll(list1);
                            merged.addAll(list2);
                            return merged;
                        }
                ));
    }
}
