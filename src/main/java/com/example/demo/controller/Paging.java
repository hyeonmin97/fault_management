package com.example.demo.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Slf4j
public class Paging {
    private boolean enablePrev;
    private boolean enableNext;
    private int currentPage;
    private int maxSize;
    private int startPage;
    private int endPage;

    /**
     * 
     * @param maxSize 최대 페이지 수
     * @param currentPage 현재 페이지 번호
     * @return Paging
     */
    public static Paging of(int maxSize, int currentPage) {
        Paging paging = new Paging();
        paging.setCurrentPage(currentPage);
        paging.setMaxSize(maxSize);
        paging.setEnablePrev(currentPage != 1);
        paging.setEnableNext(currentPage < maxSize);

        if (currentPage <= 10) {
            paging.setStartPage(1);
            paging.setEndPage(10);
        } else if (currentPage >= maxSize-9) {
            paging.setStartPage(maxSize-9);
            paging.setEndPage(maxSize);
        } else {
            paging.setStartPage(currentPage - 4);
            paging.setEndPage(currentPage + 5);
        }

        log.info("paging {} ", paging);
        return paging;
    }

}
