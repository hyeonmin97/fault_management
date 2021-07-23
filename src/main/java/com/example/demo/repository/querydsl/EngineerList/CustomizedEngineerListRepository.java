package com.example.demo.repository.querydsl.EngineerList;

import com.example.demo.dto.UpdateDto;

public interface CustomizedEngineerListRepository {
	public void update(UpdateDto updateDto);
	public void increaseAge(int value);
}
