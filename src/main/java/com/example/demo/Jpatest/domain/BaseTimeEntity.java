package com.example.demo.Jpatest.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass//��ӹ��� jpa entity Ŭ�������� ��¥�鵵 �÷����� �ν��ϰ���
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity {
	
	@CreatedDate
	private LocalDateTime createdDate;//������¥
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;//������¥
}
