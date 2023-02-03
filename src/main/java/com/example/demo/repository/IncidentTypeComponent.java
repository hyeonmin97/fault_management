package com.example.demo.repository;

import com.example.demo.domain.IncidentType;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.*;

@Component
@Transactional(readOnly = true)
public class IncidentTypeComponent {
    private EntityManager em;
    private List<IncidentType> list;
    //Map<대분류, Map<중분류,List<JSONObject>>
    //대분류, 중분류는 같고 소분류 리스트에 소분류를 추가하는것
    private Map<String, Map> incidentTypeMap;

    //생성자에서 사고 타입을 초기화 하기 위해 생성자 주입 방식을 사용
    @Autowired
    public IncidentTypeComponent(EntityManager em) {
        this.em = em;
        list = findAll().orElseThrow(() ->new NoSuchElementException("IncidentType을 찾을 수 없습니다."));
        incidentTypeMap = new LinkedHashMap<>();
        for (IncidentType incidentType : list) {
            //정렬된 채로 리스트에 담기므로 LinkedHashMap, LinkedList 사용
            if (!incidentTypeMap.containsKey(incidentType.getLarge())) {
                incidentTypeMap.put(incidentType.getLarge(), new LinkedHashMap<String, List<JSONObject>>());
            }
            Map<String, List<JSONObject>> mediumMap = incidentTypeMap.get(incidentType.getLarge());
            if (!mediumMap.containsKey(incidentType.getMedium())) {
                mediumMap.put(incidentType.getMedium(), new LinkedList<JSONObject>());
            }
            List<JSONObject> jsonObjectList = mediumMap.get(incidentType.getMedium());
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", incidentType.getId());
            jsonObject.put("name", incidentType.getSmall());
            jsonObjectList.add(jsonObject);
        }
    }

    private Optional<List<IncidentType>> findAll() {

        return Optional.ofNullable(em.createQuery("select i from IncidentType i order by i.large, i.medium, i.small asc", IncidentType.class).getResultList());
    }

    public Map<String, Map> getIncidentTypeMap() {
        return incidentTypeMap;
    }
    
}
