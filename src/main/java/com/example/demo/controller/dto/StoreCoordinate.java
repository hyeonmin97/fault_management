package com.example.demo.controller.dto;

import com.example.demo.domain.Store;
import lombok.Getter;
import org.locationtech.jts.geom.Coordinate;

@Getter
public class StoreCoordinate extends StoreDto {

    private Coordinate point;
    public StoreCoordinate(Store store) {
        super(store);
        if (store.getPoint() != null) {

            this.point = store.getPoint().getCoordinate();
        }
    }
}
