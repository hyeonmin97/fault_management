package com.example.demo.repository;

import com.example.demo.domain.ReceivedIncident;
import com.example.demo.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceivedIncidentRepository {
    private final EntityManager em;

    public List<ReceivedIncident> findByStore(Store store) {
        return em.createQuery("select r from ReceivedIncident r " +
                "join fetch r.agency " +
                "join fetch r.employee " +
                "join fetch r.engineer " +
                "join fetch r.incidentType " +
                "join fetch r.store " +
                "where r.store=:store order by r.createDate desc").setParameter("store", store).getResultList();
    }

    public void save(ReceivedIncident receivedIncident) {
        em.persist(receivedIncident);
    }
}
