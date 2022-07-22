package com.oz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oz.model.Human;
import com.oz.repository.HumansRepository;

@Service
public class EarthService {

	@Autowired
	HumansRepository humansRepository;
	
	public ResponseEntity<List<Human>> getHumans() {

		List<Human> humans = new ArrayList<>();
		humans = humansRepository.getHumans();
		if(humans != null ){
			return new ResponseEntity<List<Human>>(humans, HttpStatus.OK);
		} else 
		{
			return new ResponseEntity<List<Human>>(HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<Human> getHuman(Long idHuman) {
		String wso2 = System.getProperty("global.wso2") ;
		Human human = humansRepository.getHuman(idHuman);
		human.setId(null);
		if (idHuman != null) {
			return new ResponseEntity<Human>(human, HttpStatus.OK);
		} else {
			return new ResponseEntity<Human>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Human> createHuman(Human newHuman) {
		if(validateHuman(newHuman)) {
			humansRepository.save(newHuman);
			return new ResponseEntity<Human>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Human>(HttpStatus.BAD_REQUEST);
		}
	}

	public Boolean validateHuman(Human newHuman) {
		if (newHuman.getLastName() != null && !newHuman.getLastName().isEmpty()
				&& newHuman.getName() != null && !newHuman.getName().isEmpty() 
				&& newHuman.getEdad() != null) {
			return true;
		} else {
			return false;
		}
	}

	public ResponseEntity<Human> updateHuman(Human updateHuman) {
		if(validateHuman(updateHuman) && updateHuman.getId() != null) {
			Human human = humansRepository.getHuman(updateHuman.getId());
			human.setEdad(updateHuman.getEdad());
			human.setLastName(updateHuman.getLastName());
			human.setName(updateHuman.getName());
			humansRepository.save(human);
			return new ResponseEntity<Human>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Human>(HttpStatus.BAD_REQUEST);
		}
	}
}
