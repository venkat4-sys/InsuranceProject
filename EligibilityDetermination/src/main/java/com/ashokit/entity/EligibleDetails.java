package com.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = " ED_ELIG_DTLS")
public class EligibleDetails {

	@Id
	@GeneratedValue
	private Integer traceId;

	private String planName;

	private String planStatus;

	private String eligibleStartDate;

	private String eligibleEndDate;

	private Integer benfitAmount;

	private String denialReason;

	@CreationTimestamp
	private LocalDate createdDate;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "caseNumber")
	private CitizenEntity citizen;
}
