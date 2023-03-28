package com.example.demo.controller.dto;

import com.example.demo.domain.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.Point;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class StoreInfoDto extends StoreDto{

    private Point point;
    private List<IncidentHistoryDto> incidentList;

    public StoreInfoDto(Store store, List<IncidentHistoryDto> list) {
        super(store);
        this.point = store.getPoint();
        this.incidentList = list;
    }
}
