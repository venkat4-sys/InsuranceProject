package com.ashokit.utility;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AgeCalculator {

	public int getAge(String dob) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		LocalDate localDate = LocalDate.parse(dob, formatter);

		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(localDate, currentDate);
		return period.getYears();

	}
	
	public  boolean ageCheck(List<Integer> kidAges) {
	    for (int age : kidAges) {
	        if (age < 16 ) {
	            return true;
	        }
	    }
	    return false;
	}

}
