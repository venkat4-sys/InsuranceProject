package com.ashokit.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="INCOME_TBL")
public class IncomeEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private double monthSal;
	
	private double rentIncome;
	
	private double propertyIncome;
	
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "caseNumber")
    private CitizenEntity citizen;

}
