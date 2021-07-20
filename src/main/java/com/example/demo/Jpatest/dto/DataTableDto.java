package com.example.demo.Jpatest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataTableDto extends RequestDto implements Serializable{
	private int draw;
    private int recordsTotal;
    private int recordsFiltered;
}
