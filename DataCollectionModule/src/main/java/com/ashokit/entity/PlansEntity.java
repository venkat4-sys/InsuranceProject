package com.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "PLANS_TBL")
public class PlansEntity {

	@Id
	@GeneratedValue
	private Integer planId;

	private String planName;

	private String planStartDate;

	private String planEndDate;

	private String planCategory;

	private boolean active;

	@CreationTimestamp
	@Column(name = "UpdatedDate", updatable = false)
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "createdDate", insertable = false)
	private LocalDate updatedDate;

	private Integer CreatedByfk;

	private Integer UpdatedByfk;

}
