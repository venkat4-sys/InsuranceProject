package com.ashokit.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ashokit.binding.CitizenBinding;
import com.ashokit.entity.CitizenEntity;
import com.ashokit.entity.User;
import com.ashokit.repo.Citizenrepo;
import com.ashokit.service.Citizenservice;

@Service
public class Citizenserviceimpl implements Citizenservice {

	@Autowired
	private Citizenrepo citizenrepo;

	private String SAVE_CITIZEN_URL = "http://65.2.166.5:8080/ssa/citizen";

	public String saveCitizen(CitizenBinding citizen) {

		Integer maxSequenceNumber = citizenrepo.getMaxSequenceNumber();

		if (maxSequenceNumber == null) {
			maxSequenceNumber = 1234;

		} else {
			maxSequenceNumber++;
		}

	    RestTemplate rt = new RestTemplate();

		ResponseEntity<CitizenBinding> respEntity = rt.postForEntity(SAVE_CITIZEN_URL, citizen, CitizenBinding.class);

		CitizenBinding body = respEntity.getBody();

		if (body.getState().equals("Rhode Island")) {

			CitizenEntity entity = new CitizenEntity();
			
			/*entity.setCity(body.getCity());
			entity.setDob(body.getDob());
			entity.setFullname(body.getFullname());
			entity.setGender(body.getGender());
			entity.setHouseNum(body.getHouseNum());
			entity.setSsn(body.getSsn());
			entity.setState(body.getState());*/
			
			BeanUtils.copyProperties(body, entity);
			
			entity.setEmail(citizen.getEmail());

			User user = new User();
			user.setUserId(citizen.getUserId());

			entity.setUser(user);
			entity.setMobNumber(citizen.getMobNumber());

			entity.setCaseNumber(maxSequenceNumber);

			citizenrepo.save(entity);

			return "Citizen added Sucessfully";

		}

		return "Citizen not allowed";
	}

}
