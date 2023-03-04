package com.example.demo.service;

import com.example.demo.controller.dto.EngineerInfoDto;
import com.example.demo.controller.dto.IncidentHistoryDto;
import com.example.demo.domain.Agency;
import com.example.demo.domain.Engineer;
import com.example.demo.domain.EngineerStatus;
import com.example.demo.domain.ReceivedIncident;
import com.example.demo.repository.EngineerRepository;
import com.example.demo.repository.ReceivedIncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EngineerService {

    private final EngineerRepository engineerRepository;
    private final ReceivedIncidentRepository receivedIncidentRepository;


    @Transactional(readOnly = true)
    public EngineerInfoDto getEngineerInfoDto(Long engineerId, int startPage, int size) {

        Engineer engineer = engineerRepository.find(engineerId).orElseThrow(()->new NoSuchElementException("엔지니어가 없습니다."));
        EngineerInfoDto engineerInfoDto = EngineerInfoDto.builder()
                .id(engineer.getId())
                .name(engineer.getName())
                .age(engineer.getAge())
                .phone(engineer.getPhone())
                .createDate(engineer.getCreateDate())
                .updateDate(engineer.getUpdateDate())
                .joinDate(engineer.getJoinDate())
                .resignationDate(engineer.getResignationDate())
                .engineerStatus(engineer.getEngineerStatus())
                .agencyCode(engineer.getAgency().getAgencyCode())
                .agencyName(engineer.getAgency().getName())
                .build();

        //엔지니어가 처리한 장애 내역 불러오기
        //1페이지면 0번째부터 불러오기
        int startIndex = (startPage-1) * size;
        List<IncidentHistoryDto> incidentHistoryDtoWithPagingList = getIncidentHistoryDtoWithPagingList(engineer, startIndex, size);
        engineerInfoDto.getIncidentHistoryDtoList().addAll(incidentHistoryDtoWithPagingList);

        return engineerInfoDto;
    }
    private List<IncidentHistoryDto> getIncidentHistoryDtoWithPagingList(Engineer engineer, int startIndex, int size) {
        List<ReceivedIncident> receivedIncidentList = receivedIncidentRepository.findByEngineer(engineer, startIndex, size);
        List<IncidentHistoryDto> incidentHistoryDtoList = getIncidentHistoryDtoList(receivedIncidentList);

        return incidentHistoryDtoList;
    }

    private static List<IncidentHistoryDto> getIncidentHistoryDtoList(List<ReceivedIncident> receivedIncidentList) {
        List<IncidentHistoryDto> incidentHistoryDtoList = receivedIncidentList.stream().map(receivedIncident -> {
                    IncidentHistoryDto.IncidentHistoryDtoBuilder builder = IncidentHistoryDto.builder()
                            .incidentId(receivedIncident.getId())
                            .createDate(receivedIncident.getCreateDate())
                            .completionDate(receivedIncident.getCompletionDate())
                            .text(receivedIncident.getText())
                            .agencyName(receivedIncident.getAgency().getName())
                            .agencyCode(receivedIncident.getAgency().getAgencyCode())
                            .processMethod(receivedIncident.getProcessMethod())
                            .processStatus(receivedIncident.getProcessStatus())
                            .incidentType(receivedIncident.getIncidentType())
                            .customerName(receivedIncident.getCustomerName())
                            .customerPhone(receivedIncident.getCustomerPhone())
                            .isRegular(receivedIncident.getIsRegular())
                            .employeeId(receivedIncident.getEmployee().getId())
                            .employeeName(receivedIncident.getEmployee().getName());

                    if (receivedIncident.getEngineer() != null) {
                        builder.engineerName(receivedIncident.getEngineer().getName())
                                .engineerId(receivedIncident.getEngineer().getId())
                                .engineerStatus(receivedIncident.getEngineer().getEngineerStatus());
                    }

                    return builder.build();
                }
        ).collect(Collectors.toList());

        return incidentHistoryDtoList;
    }

}
