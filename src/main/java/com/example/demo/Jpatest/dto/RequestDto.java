package com.example.demo.Jpatest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto implements Serializable {	
	private int draw;
    private int recordsTotal;
    private int recordsFiltered;
}