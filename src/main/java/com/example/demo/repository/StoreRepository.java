package com.example.demo.repository;

import com.example.demo.domain.QLocation;
import com.example.demo.domain.QStore;
import com.example.demo.domain.Store;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreRepository {
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public void save(Store store) {
        em.persist(store);
    }

    public Store find(Long id) {
        Store store = em.find(Store.class, id);
        return store;
    }

    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class).getResultList();
    }

    public Long countByStore() {
        return em.createQuery("select count(s.id) from Store s", Long.class).getSingleResult();
    }

    public List<Store> findByStoreWithStartAndSize(int start, int size) {
        return em.createQuery("select s from Store s join fetch s.location order by s.storeCode", Store.class).setFirstResult(start).setMaxResults(size).getResultList();
    }

    /**
     * 검색 조건에 해당하는 컬럼의 개수
     * @param type 검색 타입
     * @param keyword 키워드
     * @return 조건에 맞는 컬럼의 개수
     */
    public Long countByQuery(String type, String keyword) {
        QStore qStore = new QStore("s");
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(type) && StringUtils.hasText(keyword)) {
            if (type.equals(SearchType.Name.name())) {
                builder.and(qStore.name.contains(keyword));
            } else if (type.equals(SearchType.Telephone.name())) {
                builder.and(qStore.telephone.contains(keyword));
            } else if (type.equals(SearchType.ManagerName.name())) {
                builder.and(qStore.managerName.contains(keyword));
            } else if (type.equals(SearchType.ManagerPhone.name())) {
                builder.and(qStore.managerPhone.contains(keyword));
            } else {
                //없으면 점포코드로 검색
                builder.and(qStore.storeCode.contains(keyword));
            }
        }

        return jpaQueryFactory.select(qStore.count()).from(qStore).where(builder).fetchOne();
    }

    /**
     * 검색 조건에 맞는 컬럼 검색
     * @param type 검색 타입
     * @param keyword 키워드
     * @param startIndex 검색 시작할 인덱스
     * @param size 검색할 개수
     * @return 조건에 맞는 컬럼들 리스트 리턴
     */
    public List<Store> findByStoreWithQuery(String type, String keyword, int startIndex, int size) {
        QStore qStore = new QStore("s");
        QLocation location = QLocation.location;
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(type) && StringUtils.hasText(keyword)) {
            if (type.equals(SearchType.Name.name())) {
                builder.and(qStore.name.contains(keyword));
            } else if (type.equals(SearchType.Telephone.name())) {
                builder.and(qStore.telephone.contains(keyword));
            } else if (type.equals(SearchType.ManagerName.name())) {
                builder.and(qStore.managerName.contains(keyword));
            } else if (type.equals(SearchType.ManagerPhone.name())) {
                builder.and(qStore.managerPhone.contains(keyword));
            } else {
                //없으면 점포코드로 검색
                builder.and(qStore.storeCode.contains(keyword));
            }
        }

        return jpaQueryFactory.selectFrom(qStore).where(builder).orderBy(qStore.storeCode.asc()).offset(startIndex).limit(size).leftJoin(qStore.location, location).fetchJoin().fetch();
    }

    /**
     * 점포코드로 점포 검색
     * @param storeCode 점포코드
     * @return Store
     */
    public Optional<Store> findByStoreCode(String storeCode) {
        List<Store> storeList = em.createQuery("select s from Store s join fetch s.location where s.storeCode = :storeCode", Store.class)
                .setParameter("storeCode", storeCode)
                .getResultList();
        return storeList.stream().findAny();

    }

}
