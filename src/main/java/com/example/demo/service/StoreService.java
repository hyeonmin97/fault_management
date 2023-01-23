package com.example.demo.service;

import com.example.demo.controller.dto.StoreListDto;
import com.example.demo.domain.Store;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;

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
}
