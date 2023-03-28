package com.example.demo.controller;

import com.example.demo.controller.dto.AgencyHomeDto;
import com.example.demo.controller.dto.AgencyInfoDto;
import com.example.demo.controller.dto.AgencyListDto;
import com.example.demo.controller.dto.IncidentHistoryDto;
import com.example.demo.service.AgencyService;
import com.example.demo.service.ReceivedIncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("agency")
@Slf4j
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

    @GetMapping("/home/{agencyCode}")
    public String getAgencyHome(@PathVariable String agencyCode, Model model){
        final int someCompletedSize = 5;
        AgencyHomeDto agencyHome = agencyService.getAgencyHome(agencyCode, someCompletedSize);

        log.info("agencyHome : {}", agencyHome);

        model.addAttribute("agencyCode", agencyCode);
        model.addAttribute("agencyHome", agencyHome);
        model.addAttribute("year", LocalDateTime.now().getYear());
        model.addAttribute("searchYears", AgencyHomeDto.SEARCH_YEARS);
        return "agency/home";
    }

    @PostMapping("/engineerAssignment")
    public String assignmentIncident(HttpServletRequest request, @RequestParam long incidentId, @RequestParam long engineerId) {

        receivedIncidentService.assigmentEngineer(incidentId, engineerId);
        String referer = request.getHeader("Referer");
        log.info("referer {}", referer);
        log.info("incidentId {} ", incidentId);
        log.info("engineerId {} ", engineerId);
        
        //TODO(대리점 권한 홈페이지로 이동, referer방식이 아닌 다른방식으로 이동해보기)
        return "redirect:" + referer;
    }

    //전체 장애 조회
    @GetMapping("/home/{agencyCode}/incident")
    public String getAgencyIncident(@RequestParam(defaultValue = "1") int page, @PathVariable String agencyCode, Model model){
        model.addAttribute("agencyCode", agencyCode);

        //최대 페이지 수
        int maxPage = receivedIncidentService.getAgencyMaxPage(agencyCode);

        List<IncidentHistoryDto> incidentHistory = agencyService.getIncidentHistory(agencyCode, page, AGENCY_INCIDENT_HISTORY_SIZE);
        model.addAttribute("incidentHistory", incidentHistory);

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);
        return "agency/incident";
    }
}
