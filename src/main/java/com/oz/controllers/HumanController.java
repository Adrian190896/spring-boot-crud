package com.oz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oz.model.human.HumanModel;
import com.oz.service.HumanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class HumanController {

	@Autowired
	HumanService humanService;

	@Operation(summary = "Get humans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "List of all humans"),
            @ApiResponse(responseCode = "404",
            description = "There are no humans",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
            description = "Database error",
            content = {@Content(mediaType = "application/json")})
    })
	@GetMapping("/humans")
	public ResponseEntity<?> getHumans() {
		return humanService.getHumans();
	}
	
	@Operation(summary = "Get human")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "List human"),
            @ApiResponse(responseCode = "404",
            description = "Human doesn't exist",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
            description = "Database error",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
            description = "Validate humanId",
            content = {@Content(mediaType = "application/json")})
    })
	@GetMapping("/human/{idHuman}")
	public ResponseEntity<?> getHuman(@PathVariable Long idHuman) {	
		return humanService.getHuman(idHuman);
	}
	
	@Operation(summary = "Create human")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
            description = "Create human"),
            @ApiResponse(responseCode = "400",
            description = "Validate the request",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
            description = "Database error",
            content = {@Content(mediaType = "application/json")})
    })
	@PostMapping("/human")
	public ResponseEntity<?> createHuman(@RequestBody HumanModel newHuman) {
		return humanService.createHuman(newHuman);
	}

	@Operation(summary = "Update human")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Update human"),
            @ApiResponse(responseCode = "404",
            description = "HumanId doesn't exist",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
            description = "Database error",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
            description = "Validate the request",
            content = {@Content(mediaType = "application/json")})
    })
	@PutMapping("/human/{idHuman}")
	public ResponseEntity<?> updateHuman(@RequestBody HumanModel updateHuman, @PathVariable Long idHuman) {
		return humanService.updateHuman(updateHuman, idHuman);
	} 
	
	@Operation(summary = "Partial human update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Partial human update"),
            @ApiResponse(responseCode = "404",
            description = "HumanId doesn't exis",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
            description = "Database error",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
            description = "Validate humanId",
            content = {@Content(mediaType = "application/json")})
    })
	@PatchMapping("/human/{idHuman}")
	public ResponseEntity<?> patchHuman(@RequestBody HumanModel updateHuman, @PathVariable Long idHuman) {
		return humanService.updatePartialHuman(updateHuman, idHuman);
	} 
	
	@Operation(summary = "Create human")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
            description = "Delete human"),
            @ApiResponse(responseCode = "500",
            description = "Database error",
            content = {@Content(mediaType = "application/json")})
    })
	@DeleteMapping("/human/{idHuman}")
	public ResponseEntity<?> deleteHuman(@PathVariable Long idHuman) {
		return humanService.deteleHuman(idHuman);
	} 
}