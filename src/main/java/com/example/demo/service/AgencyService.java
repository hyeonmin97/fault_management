package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.domain.*;
import com.example.demo.repository.AgencyRepository;
import com.example.demo.repository.EngineerRepository;
import com.example.demo.repository.ReceivedIncidentRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgencyService {

    private final AgencyRepository agencyRepository;
    private final EngineerRepository engineerRepository;
    private final ReceivedIncidentRepository receivedIncidentRepository;

    public int getMaxPage() {
        Long size = agencyRepository.count();
        return (int)(size % 50 ==0 ? size/50 : size/50 + 1);
    }
    public int getMaxPage(String type, String keyword) {
        Long size = agencyRepository.countByQuery(type, keyword);

        return (int)(size % 50 ==0 ? size/50 : size/50 + 1);
    }

    public Optional<List<AgencyListDto>> getAgencyListWithPage(int startPage, int size) {

        int startIndex = (startPage-1) * size;//1페이지면 0번째부터 불러오기

        List<Agency> agencyList = agencyRepository.findWithStartIndex(startIndex, size);
        ArrayList<AgencyListDto> dtoList = getAgencyListDtos(agencyList);
        return Optional.ofNullable(dtoList);
    }

    private ArrayList<AgencyListDto> getAgencyListDtos(List<Agency> agencyList) {
        ArrayList<AgencyListDto> dtoList = new ArrayList<>();
        for (Agency agency : agencyList) {
            AgencyListDto dto = AgencyListDto.builder().agencyCode(agency.getAgencyCode())
                    .name(agency.getName())
                    .postalCode(agency.getPostalCode())
                    .address(agency.getAddress())
                    .addressDetail(agency.getAddressDetail())
                    .telephone(agency.getTelephone())
                    .fax(agency.getFax()).build();

            dtoList.add(dto);
        }
        return dtoList;
    }

    public Optional<List<AgencyListDto>> getAgencyListWithKeyword(String type, String keyword, int startPage, int size){
        int startIndex = (startPage-1) * size;//1페이지면 0번째부터 불러오기
        List<Agency> list = agencyRepository.findByAgencyWithQuery(type, keyword, startIndex, size);
        ArrayList<AgencyListDto> dtoList = getAgencyListDtos(list);
        return Optional.ofNullable(dtoList);
    }

    public AgencyInfoDto getAgencyInfo(String agencyCode, int startPage, int size){
        //점포 정보
        //소속된 엔지니어 리스트
        //최근 처리한 장애 내역

        Agency findAgency = agencyRepository.findByCode(agencyCode).orElseThrow(()-> new NoSuchElementException("점포코드에 해당하는 점포가 없습니다."));

        //대리점에 소속된 엔지니어 리스트 불러오기
        List<Engineer> engineerList = engineerRepository.findEngineerWithAgencyCode(findAgency);

        //대리점 정보 dto 생성
        AgencyInfoDto agencyInfoDto = AgencyInfoDto.builder()
                .agencyCode(findAgency.getAgencyCode())
                .name(findAgency.getName())
                .postalCode(findAgency.getPostalCode())
                .address(findAgency.getAddress())
                .addressDetail(findAgency.getAddressDetail())
                .telephone(findAgency.getTelephone())
                .fax(findAgency.getFax())
                .managerName(findAgency.getManagerName())
                .managerPhone(findAgency.getManagerPhone())
                .status(findAgency.getStoreAgencyStatus())
                .build();

        //엔지니어의 정보를 담은 Dto리스트 추가
        List<AgencyInfoEngineerDto> agencyInfoDtoEngineerList = agencyInfoDto.getEngineerList();
        for (Engineer engineer : engineerList) {
            //엔지니어를 AgencyInfoEngineerDto로 변경
            AgencyInfoEngineerDto agencyInfoEngineerDto = AgencyInfoEngineerDto.builder()
                    .id(engineer.getId())
                    .name(engineer.getName())
                    .age(engineer.getAge())
                    .phone(engineer.getPhone())
                    .joinDate(engineer.getJoinDate())
                    .resignationDate(engineer.getResignationDate())
                    .engineerStatus(engineer.getEngineerStatus())
                    .build();

            agencyInfoDtoEngineerList.add(agencyInfoEngineerDto);
        }

        //관리지역 정보 추가
        List<AgencyControl> agencyControlList = agencyInfoDto.getAgencyControlList();
        List<AgencyControl> agencyControlsWithAgency = agencyRepository.findAgencyControlsWithAgency(findAgency);
        agencyControlList.addAll(agencyControlsWithAgency);

        //대리점이 처리한 장애 내역
        //1페이지면 0번째부터 불러오기
        int startIndex = (startPage-1) * size;
        List<IncidentHistoryDto> incidentHistoryDtoList = getIncidentHistoryDtoWithPagingList(findAgency, startIndex, size);
        agencyInfoDto.getIncidentHistoryDtoList().addAll(incidentHistoryDtoList);

        return agencyInfoDto;
    }
    private List<IncidentHistoryDto> getIncidentHistoryDtoWithPagingList(Agency agency, int startIndex, int size) {
        List<ReceivedIncident> receivedIncidentList = receivedIncidentRepository.findByAgency(agency, startIndex, size);
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
