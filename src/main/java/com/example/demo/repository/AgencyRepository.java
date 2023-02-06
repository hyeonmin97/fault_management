package com.example.demo.repository;

import com.example.demo.domain.Agency;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AgencyRepository {

    private final EntityManager em;

    public Optional<List<Object[]>> calDistance(Point storePoint) {

        return Optional.ofNullable(em.createNativeQuery(
                "select a.agency_code, a.name, " +
                        "st_distance_sphere(a.point, :storePoint) as distance, " +
                        "count(*) as total_count, " +
                        "count(case when e.status='WORK' then 1 end) as work_count, " +
                        "a.status " +
                        "from agency a " +
                        "left outer join engineer e on a.id = e.agency_id " +
                        "group by a.id")
                .setParameter("storePoint", storePoint)
                .setMaxResults(10)
                .getResultList());
    }

    public void save(Agency agency) {
        em.persist(agency);
    }

    public Optional<Agency> findByCode(String agencyCode) {
        return em.createQuery("select a from Agency a where a.agencyCode = :agencyCode", Agency.class).setParameter("agencyCode", agencyCode).getResultStream().findAny();
    }
}
