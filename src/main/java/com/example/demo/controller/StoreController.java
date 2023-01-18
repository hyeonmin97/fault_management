package com.example.demo.controller;

import com.example.demo.controller.dto.StoreListDto;
import com.example.demo.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
@Slf4j
public class StoreController {

    private final StoreService storeService;
    private static final int STORES_SIZE = 50;

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

        return "/store/list";
    }
}
