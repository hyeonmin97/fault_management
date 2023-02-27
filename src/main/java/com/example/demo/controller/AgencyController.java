package com.example.demo.controller;

import com.example.demo.controller.dto.AgencyInfoDto;
import com.example.demo.controller.dto.AgencyListDto;
import com.example.demo.controller.dto.StoreListDto;
import com.example.demo.domain.searchType.SearchTypeAgency;
import com.example.demo.service.AgencyService;
import com.example.demo.service.ReceivedIncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("agency")
public class AgencyController {

    private final AgencyService agencyService;
    private final ReceivedIncidentService receivedIncidentService;
    private final int AGENCY_SIZE = 50;
    private final int AGENCY_INCIDENT_HISTORY_SIZE = 50;

    @GetMapping("/list")
    public String agencyList(@RequestParam(defaultValue = "1") int page, Model model) {
        //최대 페이지 수
        int maxPage = agencyService.getMaxPage();

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);

        //현재 페이지에 해당하는 agency목록 불러오기
        Optional<List<AgencyListDto>> optionalList = agencyService.getAgencyListWithPage(page, AGENCY_SIZE);
        if (optionalList.isPresent()) {
            model.addAttribute("list", optionalList.get());
        }

        return "/agency/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "1") int page, @RequestParam String type, @RequestParam String keyword, Model model) {
        //최대 페이지 수
        int maxPage = agencyService.getMaxPage(type, keyword);

        //주어진 조건으로 검색
        Optional<List<AgencyListDto>> optionalList = agencyService.getAgencyListWithKeyword(type, keyword, page, AGENCY_SIZE);
        if (optionalList.isPresent()) {
            model.addAttribute("list", optionalList.get());
        }

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);

        return "/agency/searchList";
    }

    @GetMapping("/{agencyCode}")
    public String getAgency(@PathVariable String agencyCode, @RequestParam(defaultValue = "1") int page, Model model){
        //최대 페이지 수
        int maxPage = receivedIncidentService.getAgencyMaxPage(agencyCode);

        AgencyInfoDto agencyInfo = agencyService.getAgencyInfo(agencyCode, page, AGENCY_INCIDENT_HISTORY_SIZE);
        model.addAttribute("agencyInfo", agencyInfo);

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);

        return "/agency/agencyInfo";
    }

}
