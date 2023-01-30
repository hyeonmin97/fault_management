package com.example.demo.repository;

import com.example.demo.domain.IncidentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class IncidentTypeComponent {
    private EntityManager em;
    private List<IncidentType> list;
    private List<IncidentType> incidentTypeList;

    //생성자에서 사고 타입을 초기화 하기 위해 생성자 주입 방식을 사용
    @Autowired
    public IncidentTypeComponent(EntityManager em) {
        this.em = em;
        list = findAll().orElseThrow(() ->new NoSuchElementException("IncidentType을 찾을 수 없습니다."));
        incidentTypeList = Collections.unmodifiableList(list);//변경할 수 없는 리스트로 설정
    }

    private Optional<List<IncidentType>> findAll() {

        return Optional.ofNullable(em.createQuery("select i from IncidentType i order by i.large, i.medium, i.small asc", IncidentType.class).getResultList());
    }

    public List<IncidentType> getIncidentTypeList() {
        return this.incidentTypeList;
    }
    
}
