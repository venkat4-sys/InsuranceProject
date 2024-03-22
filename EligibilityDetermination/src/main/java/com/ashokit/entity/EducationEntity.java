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
@Table(name="EDUCATION_DTLS_TBL")
public class EducationEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String highestDegree;
	
	private Long graduationYear;
	
	private String universityName;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "caseNumber")
    private CitizenEntity citizen;

}
