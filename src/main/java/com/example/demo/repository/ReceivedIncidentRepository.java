package com.example.demo.repository;

import com.example.demo.domain.Agency;
import com.example.demo.domain.Engineer;
import com.example.demo.domain.ReceivedIncident;
import com.example.demo.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReceivedIncidentRepository {
    private final EntityManager em;

    public ReceivedIncident find(long id) {
        return em.find(ReceivedIncident.class, id);
    }
    
    public List<ReceivedIncident> findByStore(Store store) {
        return getReceivedIncidentQuery().setParameter("store", store).getResultList();
    }

    public List<ReceivedIncident> findByStore(Store store, int startIndex, int size) {
        return getReceivedIncidentQuery().setParameter("store", store).setFirstResult(startIndex).setMaxResults(size).getResultList();
    }

    private Query getReceivedIncidentQuery() {
        return em.createQuery("select r from ReceivedIncident r " +
                "join fetch r.agency " +
                "join fetch r.employee " +
                "left join fetch r.engineer " +
                "join fetch r.incidentType " +
                "join fetch r.store " +
                "where r.store=:store order by r.createDate desc");
    }

    public List<ReceivedIncident> findByAgency(Agency agency, int startIndex, int size){
        return em.createQuery("select r from ReceivedIncident r " +
                "join fetch r.agency " +
                "join fetch r.employee " +
                "left join fetch r.engineer " +
                "join fetch r.incidentType " +
                "join fetch r.store " +
                "where r.agency = :agency " +
                "order by r.createDate desc")
                .setParameter("agency", agency)
                .setFirstResult(startIndex)
                .setMaxResults(size)
                .getResultList();
    }
    public List<ReceivedIncident> findByEngineer(Engineer engineer, int startIndex, int size){
        return em.createQuery("select r from ReceivedIncident r " +
                "join fetch r.agency " +
                "join fetch r.employee " +
                "join fetch r.engineer " +
                "join fetch r.incidentType " +
                "join fetch r.store " +
                "where r.engineer = :engineer " +
                "order by r.createDate desc")
                .setParameter("engineer", engineer)
                .setFirstResult(startIndex)
                .setMaxResults(size)
                .getResultList();
    }

    public void save(ReceivedIncident receivedIncident) {
        em.persist(receivedIncident);
    }

    public Long countByStoreCode(Store store) {
        return em.createQuery("select count(r.id) from ReceivedIncident r where r.store=:store", Long.class).setParameter("store", store).getSingleResult();
    }

    public Long countByAgency(Agency agency) {
        return em.createQuery("select count(r.id) from ReceivedIncident r where r.agency=:agency", Long.class).setParameter("agency", agency).getSingleResult();
    }

    public Long countByEngineer(Engineer engineer) {
        return em.createQuery("select count(r.id) from ReceivedIncident r where r.engineer=:engineer", Long.class).setParameter("engineer", engineer).getSingleResult();
    }

    public List<ReceivedIncident> findUnCompleteByAgency(Agency agency) {
        //완료되지 않은 장애 조회
        return em.createQuery("select r from ReceivedIncident r " +
                        "join fetch r.agency " +
                        "join fetch r.employee " +
                        "left join fetch r.engineer " +
                        "join fetch r.incidentType " +
                        "join fetch r.store " +
                        "where r.agency = :agency and r.completionDate is null " +
                        "order by r.createDate asc")
                .setParameter("agency", agency)
                .getResultList();
    }

    //최근 완료한 장애목록 조회
    public List<ReceivedIncident> findSomeCompletedByAgency(Agency agency, int size) {
        //엔지니어 할당 없이 완료될 수(예약 취소 같은 경우?)도 있기때문에 엔지니어는 레프트 조인
        return em.createQuery("select r from ReceivedIncident r " +
                "join fetch r.agency " +
                "join fetch r.employee " +
                "left join fetch r.engineer " +
                "join fetch r.incidentType " +
                "join fetch r.store " +
                "where r.agency=:agency and r.completionDate is not null " +
                "order by r.createDate desc")
                .setParameter("agency", agency)
                .setFirstResult(0).setMaxResults(size)
                .getResultList();
    }
}
