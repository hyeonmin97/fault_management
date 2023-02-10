package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.domain.ReceivedIncident;
import com.example.demo.domain.Store;
import com.example.demo.domain.StoreAgencyStatus;
import com.example.demo.repository.AgencyRepository;
import com.example.demo.repository.ReceivedIncidentRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;
    private final AgencyRepository agencyRepository;
    private final ReceivedIncidentRepository receivedIncidentRepository;

    public int getMaxPage() {
        Long size = storeRepository.countByStore();
        return (int)(size % 50 ==0 ? size/50 : size/50 + 1);
    }
    public int getMaxPage(String type, String keyword) {
        Long size = storeRepository.countByQuery(type, keyword);

        return (int)(size % 50 ==0 ? size/50 : size/50 + 1);
    }

    public Optional<List<StoreListDto>> getStoresListWithStartAndSize(int start, int size) {

        int startPage = (start-1) * size;//1페이지면 0번째부터 불러오기

        List<Store> list = storeRepository.findByStoreWithStartAndSize(startPage, size);
        ArrayList<StoreListDto> dtoList = new ArrayList<>();
        for (Store store : list) {
            StoreListDto dto = StoreListDto.of(store);
            dtoList.add(dto);
        }
        return Optional.ofNullable(dtoList);
    }

    /**
     * 조건에 해당하는 컬럼 리스트 검색
     * @param type 검색 타입
     * @param keyword 키워드
     * @param startPage 검색할 페이지
     * @param size 검색할 개수
     * @return StoreListDto 리스트
     */
    public Optional<List<StoreListDto>> getStoresListWithSearch(String type, String keyword, int startPage, int size) {

        int startIndex = (startPage-1) * size;//1페이지면 0번째부터 불러오기
        List<Store> list = storeRepository.findByStoreWithQuery(type, keyword, startIndex, size);
        ArrayList<StoreListDto> dtoList = new ArrayList<>();
        for (Store store : list) {
            StoreListDto dto = StoreListDto.of(store);
            dtoList.add(dto);
        }
        return Optional.ofNullable(dtoList);
    }

    /**
     * 점포 근처의 대리점 정보와 과거 장애 접수 내역을 IncidentRequestDto에 담아서 리턴
     * @param storeCode 점포코드
     * @return IncidentRequestDto
     * @throws NoSuchElementException
     */
    public IncidentRequestDto incidentRequestData(String storeCode) throws NoSuchElementException{

        //점포 코드로 점포 찾기 >  > agency의 좌표와 비교해서 출력
        Optional<Store> findStore = storeRepository.findByStoreCode(storeCode);
        Store store = findStore.orElseThrow(() -> new NoSuchElementException("해당하는 점포 코드가 없음"));
        Point storePoint = store.getPoint();
        log.info("store point {}", storePoint);

        //근처 대리점 검색
        //거리 기반으로 정렬 => 가용 엔지니어 확인
        List<AgencyAndStoreDistanceDto> agencyAndStoreDistanceDtoList = getAgencyAndStoreDistanceDtoList(storePoint);
        
        //과거 내역 조회
        List<IncidentHistoryDto> incidentHistoryDtoList = getIncidentHistoryDtoList(store);

        return IncidentRequestDto.builder()
                .storeCode(store.getStoreCode())
                .storeName(store.getName())
                .storeTelephone(store.getTelephone())
                .storeAddress(store.getAddress())
                .storeAddressDetail(store.getAddressDetail())
                .managerName(store.getManagerName())
                .managerPhone(store.getManagerPhone())
                .agencyAndStoreDistanceDtoList(agencyAndStoreDistanceDtoList)
                .incidentHistoryLIst(incidentHistoryDtoList)
                .point(storePoint)
                .build();
    }

    /**
     * 점포의 좌표를 기준으로 가까이에 있는 대리점 검색
     * @param storePoint 점포 좌표
     * @return AgencyAndStoreDistanceDto 리스트
     */
    private List<AgencyAndStoreDistanceDto> getAgencyAndStoreDistanceDtoList(Point storePoint) {
        Optional<List<Object[]>> findAgencies = agencyRepository.calDistance(storePoint);
        List<Object[]> objects = findAgencies.orElseThrow(() -> new NoSuchElementException("대리점을 찾을 수 없음"));
        List<AgencyAndStoreDistanceDto> agencyAndStoreDistanceDtoList = objects.stream().map(object ->
                AgencyAndStoreDistanceDto.builder()
                        .agencyCode((String) object[0])
                        .name((String) object[1])
                        .distance((Double) object[2])
                        .totalCount(((BigInteger) object[3]).intValue())
                        .workCount(((BigInteger) object[4]).intValue())
                        .agencyStatus(StoreAgencyStatus.valueOf((String) object[5]))
                        .build()
        ).collect(Collectors.toList());
        log.info("result {}", agencyAndStoreDistanceDtoList);
        return agencyAndStoreDistanceDtoList;
    }

    /**
     * 점포의 과거 장애 접수내역 검색
     * @param store 점포 엔티티
     * @return IncidentHistoryDto 리스트
     */
    private List<IncidentHistoryDto> getIncidentHistoryDtoList(Store store) {
        List<ReceivedIncident> receivedIncidentList = receivedIncidentRepository.findByStore(store);
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