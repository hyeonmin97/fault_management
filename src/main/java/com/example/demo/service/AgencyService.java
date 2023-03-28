package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.domain.*;
import com.example.demo.repository.AgencyRepository;
import com.example.demo.repository.EngineerRepository;
import com.example.demo.repository.ReceivedIncidentRepository;
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

    private List<IncidentHistoryDto> getIncidentHistoryDtoList(List<ReceivedIncident> receivedIncidentList) {
        List<IncidentHistoryDto> incidentHistoryDtoList = receivedIncidentList.stream()
                .map(receivedIncident -> getIncidentHistoryDto(receivedIncident))
                .collect(Collectors.toList());

        return incidentHistoryDtoList;
    }

    public AgencyHomeDto getAgencyHome(String agencyCode, int incidentSize) {

        Agency findAgency = agencyRepository.findByAgencyCode(agencyCode).orElseThrow(() -> new NoSuchElementException("대리점이 없습니다"));
        List<Engineer> engineerList = engineerRepository.findEngineerWithAgencyCode(findAgency);

        AgencyHomeDto agencyHomeDto = new AgencyHomeDto(findAgency.getName(), findAgency.getPoint());

        //엔지니어 정보 다 불러오고 재직현황 계산
        ArrayList<EngineerDto> engineerDtoList = new ArrayList<>();
        int incumbentCount = 0;//재직자
        int retireeCount = 0;//퇴사자
        int year = LocalDateTime.now().getYear();//년

        //EngineerDto생성
        for (Engineer engineer : engineerList) {
            EngineerDto engineerDto = EngineerDto.builder()
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
            engineerDtoList.add(engineerDto);

            //재직중인지 확인
            if (engineerDto.getEngineerStatus() == EngineerStatus.WORK)
                incumbentCount++;
            else
                retireeCount++;

            //SEARCH_YEARS 사이 입사자, 퇴사자 확인
            if (engineerDto.getJoinDate() != null && engineerDto.getResignationDate() != null) {
                int joinYearDiff = year - engineerDto.getJoinDate().getYear();
                if (joinYearDiff < AgencyHomeDto.SEARCH_YEARS) {
                    int index = AgencyHomeDto.SEARCH_YEARS - 1 - joinYearDiff;
                    agencyHomeDto.getJoinCountArray()[index]++;
                }

                int resignationYearDiff = year - engineerDto.getResignationDate().getYear();
                if (resignationYearDiff < AgencyHomeDto.SEARCH_YEARS) {
                    int index = AgencyHomeDto.SEARCH_YEARS - 1 - resignationYearDiff;
                    agencyHomeDto.getResignationCountArray()[index]++;
                }
            }
        }

        //입사자, 퇴사자 수 지정
        agencyHomeDto.engineerDistribution(incumbentCount, retireeCount);
        //엔지니어 추가
        agencyHomeDto.getEngineerInfoDtoList().addAll(engineerDtoList);

        //할당, 진행 장애목록은 새로운 쿼리로 다 불러와서 서버에서 나누기, 완료일이 지정되지 않았거나 엔지니어 할당이 되지 않은 것만 검색
        //완료일이 지정되지 않은것 => 할당이나 진행중인것.
        List<ReceivedIncident> unCompleteIncidentList = receivedIncidentRepository.findUnCompleteByAgency(findAgency);
        for (ReceivedIncident receivedIncident : unCompleteIncidentList) {
            IncidentHistoryDto incidentHistoryDto = getIncidentHistoryDto(receivedIncident);

            //엔지니어 할당이 안되었다면
            if(receivedIncident.getEngineer() == null)
                agencyHomeDto.getWaitingIncidentList().add(incidentHistoryDto);//대기중
            else
                agencyHomeDto.getWorkingIncidentList().add(incidentHistoryDto);//진행중
        }

        //최근 완료한 장애목록 추가
        List<ReceivedIncident> someCompletedByAgency = receivedIncidentRepository.findSomeCompletedByAgency(findAgency, incidentSize);
        List<IncidentHistoryDto> someIncidentHistoryDtoList = getIncidentHistoryDtoList(someCompletedByAgency);
        agencyHomeDto.getRecentlyCompletedIncidentList().addAll(someIncidentHistoryDtoList);

        //관리하는 점포
        List<Store> managedStoreList = agencyRepository.findManagedStoreWithLocation(findAgency);
        List<StoreCoordinate> StoreCoordinateList = managedStoreList.stream().map(store -> new StoreCoordinate(store)).collect(Collectors.toList());
        agencyHomeDto.getStoreCoordinateList().addAll(StoreCoordinateList);

        return agencyHomeDto;
    }

    private IncidentHistoryDto getIncidentHistoryDto(ReceivedIncident receivedIncident) {
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

    //대리점의 장애 내역만 불러오는 함수
    public List<IncidentHistoryDto> getIncidentHistory(String agencyCode, int startPage, int size) {
        //대리점이 처리한 장애 내역
        //1페이지면 0번째부터 불러오기
        Agency findAgency = agencyRepository.findByCode(agencyCode).orElseThrow(()->new NoSuchElementException("대리점이 없습니다."));
        int startIndex = (startPage-1) * size;
        return getIncidentHistoryDtoWithPagingList(findAgency, startIndex, size);
    }

}
