package com.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;
    
    private String email;
    
    private String password;
    
    private String gender;
    
    private String dob;
    
    private Long ssnNumber;
    
    private String mobileNumber;
    
    private String status;
    
    
    private boolean active; 
    
    @CreationTimestamp
    @Column(name="createddate",updatable=false)
    private LocalDate createdDate;
    
    @UpdateTimestamp
    @Column(name="updateddate",insertable=false)
    private LocalDate updatedDate;
    
    
    private Integer CreatedByfk;
    
    private Integer UpdatedByfk;

    @ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="roleId")
    private UserRoleEntity userrole;
    
    
	

}
