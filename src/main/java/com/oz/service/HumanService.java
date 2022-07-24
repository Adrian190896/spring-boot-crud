package com.oz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oz.common.ResponseCustom;
import com.oz.model.human.HumanModel;
import com.oz.model.human.HumanModelDAO;
import com.oz.repository.HumansRepository;

@Service
public class HumanService {

	@Autowired
	HumansRepository humansRepository;

	public ResponseEntity<?> getHumans() {
		List<HumanModelDAO> humans = new ArrayList<>();
		try {
			humans = humansRepository.getHumans();
			if (humans != null) {
				List<HumanModel> listHumanModel = new ArrayList<HumanModel>();
				for (HumanModelDAO human : humans) {
					HumanModel humanModel = new HumanModel(human.getName(), human.getLastName(), human.getAge());
					listHumanModel.add(humanModel);
				}
				return new ResponseEntity<List<HumanModel>>(listHumanModel, HttpStatus.OK);
			} else {
				return responseService("There are no humans", "", HttpStatus.NOT_FOUND); 
			}
		}catch(Exception e) {
			return responseService("Database error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}

	public ResponseEntity<?> getHuman(Long idHuman) {
		if (validateLong(idHuman)) {
			try {
				HumanModelDAO human = humansRepository.getHuman(idHuman);
				if (human != null) {
					HumanModel humanModel = new HumanModel(human.getName(), human.getLastName(), human.getAge());
					return new ResponseEntity<HumanModel>(humanModel, HttpStatus.OK);
				} else {
					return responseService("Human doesn't exist", "", HttpStatus.NOT_FOUND); 
				}
			}
			catch(Exception e) {
				return responseService("Database error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
			}
		} else {
			return responseService("Validate humanId", "", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> createHuman(HumanModel newHuman) {
		if (validateHuman(newHuman)) {
			try {
				humansRepository.save(new HumanModelDAO(
						newHuman.getName(), newHuman.getLastName(), newHuman.getAge()));
			}
			catch (Exception e) {
				return responseService("Database error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
			}
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return responseService("Validate the request", "", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> updateHuman(HumanModel updateHuman, Long idHuman) {
		if (validateHuman(updateHuman) && validateLong(idHuman)) {
			try {
				HumanModelDAO human = humansRepository.getHuman(idHuman);
				if(human == null) {
					return responseService("HumanId doesn't exist", "", HttpStatus.NOT_FOUND); 
				}
				human.setAge(updateHuman.getAge());
				human.setLastName(updateHuman.getLastName());
				human.setName(updateHuman.getName());
				humansRepository.save(human);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			catch(Exception e) {
				return responseService("Database error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  
			}
		} else {
			return responseService("Validate the request", "", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> updatePartialHuman(HumanModel updateHuman, Long idHuman) {
		if (validateLong(idHuman)) {
			try {
				HumanModelDAO human = humansRepository.getHuman(idHuman);
				if(human == null) {
					return responseService("HumanId doesn't exist", "", HttpStatus.NOT_FOUND);
				}
				human.setLastName(validateString(updateHuman.getLastName()) == true ? updateHuman.getLastName() : human.getLastName());
				human.setName(validateString(updateHuman.getName()) == true ? updateHuman.getName() : human.getName());
				human.setAge(validateInteger(updateHuman.getAge()) == true ? updateHuman.getAge() : human.getAge());
				humansRepository.save(human);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
			catch(Exception e) {
				return responseService("Database error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
			}
		} else {
			return responseService("Validate humanId", "", HttpStatus.BAD_REQUEST);
		}
	}


	public ResponseEntity<?> deteleHuman(Long idHuman) {
		try {
			humansRepository.deleteById(idHuman);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return responseService("Database error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	public Boolean validateInteger(Integer value) {
		if(value != null && value > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean validateLong(Long value) {
		if(value != null && value > 0) {
			return true;
		} else {
			return false;
		}
	}
	public Boolean validateString(String value) {
		if(value !=null  && !value.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean validateHuman(HumanModel newHuman) {
		if (newHuman.getLastName() != null && !newHuman.getLastName().isEmpty() && newHuman.getName() != null
				&& !newHuman.getName().isEmpty() && newHuman.getAge() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public ResponseEntity<ResponseCustom> responseService(String message, String error, HttpStatus httpStatus) {
		ResponseCustom responseCustom = new ResponseCustom();
		responseCustom.setError(error.isEmpty() ? null : error);
		responseCustom.setMessage(message.isEmpty() ? null : message);
		return ResponseEntity
	            .status(httpStatus)
	            .body(responseCustom);
	}

}
