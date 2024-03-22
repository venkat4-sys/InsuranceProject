package com.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class PlanSelectionEntity {
	
	@Id
	@GeneratedValue
	private Integer planGenerationId;
	
	private Integer caseNumber;
	
	@OneToOne
	@JoinColumn(name="planId")
	private PlansEntity plan;

}
