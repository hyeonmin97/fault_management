package com.example.demo.repository;

import com.example.demo.domain.IncidentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IncidentTypeRepository {

    private final EntityManager em;

    public Optional<IncidentType> find(Long id) {
        return Optional.ofNullable(em.find(IncidentType.class, id));
    }
}
