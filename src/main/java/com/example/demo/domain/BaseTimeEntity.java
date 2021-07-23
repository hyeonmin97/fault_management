package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass//상속받은 jpa entity 클래스들이 날짜들도 컬럼으로 인식하게 함
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseTimeEntity {
	
	@CreatedDate
	@Column(name = "createDate", updatable = false)
	private LocalDateTime createdDate;//생성날짜
	
	@LastModifiedDate
	@Column(name = "updateDate")
	private LocalDateTime updateDate;//수정날짜
}
