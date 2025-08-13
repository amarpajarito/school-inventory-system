package com.pajarito.inventory.controller;
import com.pajarito.inventory.model.SchoolInventory;
import com.pajarito.inventory.service.SchoolInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class SchoolInventoryController {
	Logger logger = LoggerFactory.getLogger( SchoolInventoryController.class);
	@Autowired
	private SchoolInventoryService schoolInventoryService;
	@GetMapping("/api/schoolInventory")
	public ResponseEntity<?> listEcommerce()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			SchoolInventory[] schoolInventory = schoolInventoryService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(schoolInventory);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/schoolInventory")
	public ResponseEntity<?> add(@RequestBody SchoolInventory schoolInventory){
		logger.info("Input >> " + schoolInventory.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			SchoolInventory newSchoolInventory = schoolInventoryService.create(schoolInventory);
			logger.info("created school inventory >> " + newSchoolInventory.toString() );
			response = ResponseEntity.ok(newSchoolInventory);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve school inventory with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/schoolInventory")
	public ResponseEntity<?> update(@RequestBody SchoolInventory schoolInventory){
		logger.info("Update Input >> schoolInventory.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			SchoolInventory newSchoolInventory = schoolInventoryService.update(schoolInventory);
			response = ResponseEntity.ok(schoolInventory);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve school inventory with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/schoolInventory/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input school inventory id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			SchoolInventory schoolInventory = schoolInventoryService.get(id);
			response = ResponseEntity.ok(schoolInventory);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/schoolInventory/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			schoolInventoryService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
