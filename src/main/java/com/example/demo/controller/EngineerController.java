package com.example.demo.controller;

import com.example.demo.controller.dto.AddEngineerDto;
import com.example.demo.controller.dto.EngineerInfoDto;
import com.example.demo.controller.dto.PatchEngineerForm;
import com.example.demo.domain.Agency;
import com.example.demo.repository.AgencyRepository;
import com.example.demo.service.EngineerService;
import com.example.demo.service.ReceivedIncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/engineer")
public class EngineerController {

    private final EngineerService engineerService;
    private final ReceivedIncidentService receivedIncidentService;
    private final AgencyRepository agencyRepository;

    private final int ENGINEER_INCIDENT_HISTORY_SIZE = 20;

    @GetMapping("/{engineerId}")
    public String getEngineer(@PathVariable Long engineerId, @RequestParam(defaultValue = "1") int page, Model model) {
        int maxPage = receivedIncidentService.getEngineerMaxPage(engineerId);

        EngineerInfoDto engineerInfoDto = engineerService.getEngineerInfoDto(engineerId, page, ENGINEER_INCIDENT_HISTORY_SIZE);
        model.addAttribute("engineerInfoDto", engineerInfoDto);

        //Paging 객체 생성
        Paging paging = Paging.of(maxPage, page);
        model.addAttribute("paging", paging);

        return "engineer/info";
    }

    @GetMapping("/{engineerId}/edit")
    public String getEngineerEditPage(@PathVariable Long engineerId, Model model){
        EngineerInfoDto engineerInfoDto = engineerService.getEngineerInfoDtoWithoutIncidentList(engineerId);
        model.addAttribute("engineerInfoDto", engineerInfoDto);

        return "engineer/edit";
    }
    @PatchMapping("/{engineerId}/edit")
    public String patchEngineer(@PathVariable Long engineerId, PatchEngineerForm patchEngineerForm){
        log.info("patchEngineer form {}", patchEngineerForm.toString());
        engineerService.patchEngineerInfo(patchEngineerForm);
        log.info("patchEngineer end");
        return "redirect:/engineer/" + engineerId;
    }

    //엔지니어 추가 폼
    @GetMapping("/{agencyCode}/add")
    public String getAddForm(@PathVariable String agencyCode, Model model) {

        Agency findAgency = agencyRepository.findByAgencyCode(agencyCode).orElseThrow(() -> new NoSuchElementException("대리점이 없습니다."));
        model.addAttribute("agencyId", findAgency.getId());
        model.addAttribute("agencyName", findAgency.getName());
        model.addAttribute("today", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
        return "engineer/addForm";
    }

    //엔지니어 추가
    @PostMapping("/add")
    public String addEngineer(AddEngineerDto addEngineerDto) {
        Long engineerId = engineerService.addEngineer(addEngineerDto);
        return "redirect:/engineer/" + engineerId;
    }
}
