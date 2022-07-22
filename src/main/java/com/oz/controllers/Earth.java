package com.oz.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oz.model.Human;
import com.oz.service.EarthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/earth")
public class Earth {

	@Autowired
	EarthService terraService;

	@Operation(summary = "Get humans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "List of all humans",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
            description = "Not found humans",
            content = @Content)
    })
	@GetMapping("/humans")
	public ResponseEntity<List<Human>> getHumans() {
		return terraService.getHumans();
	}
	
	@GetMapping("/humans/{idHuman}")
	public ResponseEntity<Human> getHuman(@PathVariable Long idHuman) {	
		System.setProperty("global.wso2", "value");
		return terraService.getHuman(idHuman);
	}
	
	@PostMapping("/humans")
	public ResponseEntity<Human> createHuman(@RequestBody Human newHuman) {
		return terraService.createHuman(newHuman);
	}
	
	@PutMapping("/humans")
	public ResponseEntity<Human> updateHuman(@RequestBody Human updateHuman) {
		
		
		
		return terraService.updateHuman(updateHuman);
	} 
}
