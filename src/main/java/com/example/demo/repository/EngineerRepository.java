package com.example.demo.repository;

import com.example.demo.domain.Agency;
import com.example.demo.domain.Engineer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EngineerRepository {

    private final EntityManager em;

    public List<Engineer> findEngineerWithAgencyCode(Agency agency){
        return em.createQuery("select e from Engineer e join fetch e.agency where e.agency = :agency").setParameter("agency", agency).getResultList();
    }

    public Optional<Engineer> find(Long id) {
        return Optional.ofNullable(em.find(Engineer.class, id));
    }
}
