package com.example.demo.service;

import com.example.demo.controller.dto.AddIncidentDto;
import com.example.demo.domain.*;
import com.example.demo.repository.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class ReceivedIncidentService {

    private final ReceivedIncidentRepository receivedIncidentRepository;
    private final StoreRepository storeRepository;
    private final AgencyRepository agencyRepository;
    private final IncidentTypeRepository incidentTypeRepository;
    private final EngineerRepository engineerRepository;

    @Transactional
    public void addIncident(AddIncidentDto incidentDto) {

        Store findStore = storeRepository.findByStoreCode(incidentDto.getStoreCode()).orElseThrow(()->new NoSuchElementException("점포가 없습니다."));
        IncidentType findIncidentType = incidentTypeRepository.find(incidentDto.getIncidentType()).orElseThrow(()->new NoSuchElementException("장애 타입이 없습니다."));
        Agency findAgency = agencyRepository.findByCode(incidentDto.getAgencyCode()).orElseThrow(() -> new NoSuchElementException("대리점이 없습니다."));

        //TODO(직원은 로그인 세션으로 추가, 엔지니어 할당은 대리점에서(센터에서는 x))
        ReceivedIncident receivedIncident = ReceivedIncident.builder()
                .customerName(incidentDto.getCustomerName())
                .customerPhone(incidentDto.getCustomerPhone())
                .text(incidentDto.getText()).agency(findAgency).isRegular(false)
                .processMethod(incidentDto.getProcessMethod()).processStatus(ProcessStatus.PROCESS)
                .agency(findAgency)
                .employee(null)
                .engineer(null)
                .incidentType(findIncidentType)
                .store(findStore).build();

        receivedIncidentRepository.save(receivedIncident);

    }

    @Transactional(readOnly = true)
    public int getMaxPage(String storeCode){
        Store findStore = storeRepository.findByStoreCode(storeCode).orElseThrow(()->new NoSuchElementException("점포가 없습니다."));
        Long size = receivedIncidentRepository.countByStoreCode(findStore);
        return (int)(size % 50 ==0 ? size/50 : size/50 + 1);
    }

    @Transactional(readOnly = true)
    public int getAgencyMaxPage(String agencyCode){
        Agency agency = agencyRepository.findByAgencyCode(agencyCode).orElseThrow(()->new NoSuchElementException("대리점이 없습니다."));
        Long size = receivedIncidentRepository.countByAgency(agency);
        return (int)(size % 50 ==0 ? size/50 : size/50 + 1);
    }
    
    @Transactional(readOnly = true)
    public int getEngineerMaxPage(Long id){
        Engineer engineer = engineerRepository.find(id).orElseThrow(()->new NoSuchElementException("대리점이 없습니다."));
        Long size = receivedIncidentRepository.countByEngineer(engineer);
        return (int)(size % 50 ==0 ? size/50 : size/50 + 1);
    }
}
