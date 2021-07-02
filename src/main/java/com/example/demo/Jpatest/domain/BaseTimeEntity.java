package com.example.demo.Jpatest.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass//상속받은 jpa entity 클래스들이 날짜들도 컬럼으로 인식하게함
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity {
	
	@CreatedDate
	private LocalDateTime createdDate;//생성날짜
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;//수정날짜
}
