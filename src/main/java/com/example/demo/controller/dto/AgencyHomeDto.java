package com.example.demo.controller.dto;

import lombok.Getter;
import lombok.ToString;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class AgencyHomeDto {

    public static final int SEARCH_YEARS = 5;
    private String name;
    private Coordinate point; //agency의 좌표
    private int incumbentCount;//현재 재직자 수 
    private int retireeCount;//현재 퇴사자 수
    private int[] resignationCountArray;//연도별 퇴사자 수
    private int[] joinCountArray;//연도별 입사자 수
    private List<EngineerDto> engineerInfoDtoList = new ArrayList<>();//전체 엔지니어 정보
    private List<StoreCoordinate> storeCoordinateList = new ArrayList<>(); //관리 점포 정보
    private List<IncidentHistoryDto> recentlyCompletedIncidentList = new ArrayList<>(); //최근 장애목록
    private List<IncidentHistoryDto> waitingIncidentList = new ArrayList<>();//할당 대기중인 장애목록
    private List<IncidentHistoryDto> workingIncidentList = new ArrayList<>();//진행중인 장애목록

    public AgencyHomeDto(String name, Point point) {
        this.name = name;
        this.point = point.getCoordinate();
        this.resignationCountArray = new int[SEARCH_YEARS];
        this.joinCountArray = new int[SEARCH_YEARS];
    }

    public void engineerDistribution(int incumbentCount, int retireeCount) {
        this.incumbentCount = incumbentCount;
        this.retireeCount = retireeCount;
    }
}