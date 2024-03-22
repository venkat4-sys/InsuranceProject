package com.ashokit.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="KID_DTLS_TBL")
public class KidEntity {
	
	@Id
	@GeneratedValue
	private Integer kidId;
	
	private String kidName;
	
	private String kidDob;
	
	private String ssnNumber;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "caseNumber")
    private CitizenEntity citizen;

}
