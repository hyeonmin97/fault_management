package com.example.demo.service;

import com.example.demo.controller.dto.AgencyListDto;
import com.example.demo.controller.dto.StoreListDto;
import com.example.demo.domain.Agency;
import com.example.demo.domain.Location;
import com.example.demo.domain.Store;
import com.example.demo.repository.AgencyRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;

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

}
