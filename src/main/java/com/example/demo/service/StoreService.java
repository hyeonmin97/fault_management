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

}
