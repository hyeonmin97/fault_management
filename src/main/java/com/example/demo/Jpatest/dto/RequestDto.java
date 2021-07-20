package com.example.demo.Jpatest.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.example.demo.Jpatest.dto.RequestDto.OrderCriterias;
import com.example.demo.Jpatest.dto.RequestDto.SearchCriterias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto implements Serializable {	
	private int draw;
    private int start;
    private int length;
    private Map<SearchCriterias, String> search;
    private List<Map<OrderCriterias, String>> order;
//    private List<Map<Object, String>> columns;
    public enum SearchCriterias {
        value,
        regex
    }
    public enum OrderCriterias {
        column,
        dir
    }
//    public enum ColumnCriterias {
//        data,
//        name,
//        searchable,
//        orderable,
//        searchValue,
//        searchRegex
//    }
}
