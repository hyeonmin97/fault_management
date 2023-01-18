package com.example.demo.repository;

import com.example.demo.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepository {
    private final EntityManager em;

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
}
