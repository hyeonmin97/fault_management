package com.example.demo.controller;

import com.example.demo.controller.dto.AddIncidentDto;
import com.example.demo.controller.dto.IncidentRequestDto;
import com.example.demo.controller.dto.StoreInfoDto;
import com.example.demo.controller.dto.StoreListDto;
import com.example.demo.domain.ProcessMethod;
import com.example.demo.domain.ReceivedIncident;
import com.example.demo.repository.IncidentTypeComponent;
import com.example.demo.service.ReceivedIncidentService;
import com.example.demo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreService storeService;
    private final IncidentTypeComponent incidentTypeComponent;
    private final ReceivedIncidentService receivedIncidentService;
    private static final int STORES_SIZE = 50;
    private static final int INCIDENT_SIZE = 20;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {

        //최대 페이지 수
        int maxPage = storeService.getMaxPage();

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);

        //현재 페이지에 해당하는 Store목록 불러오기
        Optional<List<StoreListDto>> optionalList = storeService.getStoresListWithStartAndSize(page, STORES_SIZE);
        if (optionalList.isPresent()) {
            model.addAttribute("list", optionalList.get());
        }

        return "/stores/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "1") int page, @RequestParam String type, @RequestParam String keyword, Model model) {
        //최대 페이지 수
        int maxPage = storeService.getMaxPage(type, keyword);

        //주어진 조건으로 검색
        Optional<List<StoreListDto>> optionalList = storeService.getStoresListWithSearch(type, keyword, page, STORES_SIZE);
        if (optionalList.isPresent()) {
            model.addAttribute("list", optionalList.get());
        }

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);

        return "/stores/searchList";
    }

    @GetMapping("/{storeCode}/incident")
    public String getIncident(@PathVariable String storeCode, Model model) {

        Map<String, Map> incidentTypeMap = incidentTypeComponent.getIncidentTypeMap();

        //장애 추가 페이지에 필요한 정보 저장
        IncidentRequestDto incidentRequestDto = storeService.incidentRequestData(storeCode);

        model.addAttribute("incidentType", incidentTypeMap);
        model.addAttribute("incidentRequestDto", incidentRequestDto);

        return "/stores/addIncident";
    }

    @PostMapping("/{storeCode}/incident")
    public String addIncident(@PathVariable String storeCode, AddIncidentDto addIncidentDto) {
        //ReceivedIncident 추가
        addIncidentDto.setStoreCode(storeCode);
        receivedIncidentService.addIncident(addIncidentDto);

        return "redirect:/stores/" + storeCode + "/info";
    }

    @GetMapping("/{storeCode}/info")
    public String getStore(@PathVariable String storeCode, @RequestParam(defaultValue = "1")int page, Model model) {
        //점포 장애내역 최대 페이지 조회
        //페이지 객체 생성해서 추가
        int maxPage = receivedIncidentService.getMaxPage(storeCode);

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);

        StoreInfoDto storeInfo = storeService.getStoreInfo(storeCode, page, INCIDENT_SIZE);
        model.addAttribute("storeInfo", storeInfo);
        return "/stores/storeInfo";
    }
}
