package com.example.demo.controller;

import com.example.demo.controller.dto.EngineerInfoDto;
import com.example.demo.service.EngineerService;
import com.example.demo.service.ReceivedIncidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/engineer")
public class EngineerController {

    private final EngineerService engineerService;
    private final ReceivedIncidentService receivedIncidentService;

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
}
