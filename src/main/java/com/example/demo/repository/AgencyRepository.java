package com.example.demo.repository;

import com.example.demo.domain.*;
import com.example.demo.domain.searchType.SearchTypeAgency;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AgencyRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

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

    public Optional<Agency> findByAgencyCode(String agencyCode){
        return em.createQuery("select a from Agency a where a.agencyCode = :agencyCode").setParameter("agencyCode", agencyCode).getResultList().stream().findAny();
    }

    public Optional<Agency> findByCode(String agencyCode) {
        return em.createQuery("select a from Agency a where a.agencyCode = :agencyCode", Agency.class).setParameter("agencyCode", agencyCode).getResultStream().findAny();
    }

    public Long count() {
        return em.createQuery("select count(a.id) from Agency a", Long.class).getSingleResult();
    }

    public Long countByQuery(String type, String keyword) {
        QAgency qAgency = new QAgency("a");
        BooleanBuilder builder = getBooleanBuilder(type, keyword, qAgency);

        return jpaQueryFactory.select(qAgency.count()).from(qAgency).where(builder).fetchOne();
    }

    public List<Agency> findWithStartIndex(int startIndex, int size) {

        return em.createQuery("select a from Agency a order by a.agencyCode").setFirstResult(startIndex).setMaxResults(size).getResultList();
    }

    public List<Agency> findByAgencyWithQuery(String type, String keyword, int startIndex, int size) {
        QAgency qAgency = new QAgency("a");
        BooleanBuilder builder = getBooleanBuilder(type, keyword, qAgency);

        return jpaQueryFactory.selectFrom(qAgency).where(builder).orderBy(qAgency.agencyCode.asc()).offset(startIndex).limit(size).fetch();
    }

    private static BooleanBuilder getBooleanBuilder(String type, String keyword, QAgency qAgency) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(type) && StringUtils.hasText(keyword)) {
            if (type.equals(SearchTypeAgency.NAME.name())) {
                builder.and(qAgency.name.contains(keyword));
            } else if (type.equals(SearchTypeAgency.POSTAL_CODE.name())) {
                builder.and(qAgency.postalCode.contains(keyword));
            } else if (type.equals(SearchTypeAgency.ADDRESS.name())) {
                builder.and(qAgency.address.contains(keyword));
                builder.or(qAgency.addressDetail.contains(keyword));
            } else {
                //없으면 대리점 코드로 검색
                builder.and(qAgency.agencyCode.contains(keyword));
            }
        }
        return builder;
    }

    public List<AgencyControl> findAgencyControlsWithAgency(Agency agency){

        return em.createQuery("select a from AgencyControl a join fetch a.location where a.agency = :agency").setParameter("agency", agency).getResultList();
    }

}
