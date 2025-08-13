package com.pajarito.inventory.controller;
import com.pajarito.inventory.model.Manufacturer;
import com.pajarito.inventory.service.ManufacturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ManufacturerController {
	Logger logger = LoggerFactory.getLogger( ManufacturerController.class);
	@Autowired
	private ManufacturerService manufacturerService;
	@GetMapping("/api/manufacturer")
	public ResponseEntity<?> listInventory()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manufacturer[] manufacturer = manufacturerService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(manufacturer);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/manufacturer")
	public ResponseEntity<?> add(@RequestBody Manufacturer manufacturer){
		logger.info("Input >> " + manufacturer.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manufacturer newManufacturer = manufacturerService.create(manufacturer);
			logger.info("created manufacturer >> " + newManufacturer.toString() );
			response = ResponseEntity.ok(newManufacturer);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve manufacturer with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/manufacturer")
	public ResponseEntity<?> update(@RequestBody Manufacturer manufacturer){
		logger.info("Update Input >> manufacturer.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manufacturer newManufacturer = manufacturerService.update(manufacturer);
			response = ResponseEntity.ok(manufacturer);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve manufacturer with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/manufacturer/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input manufacturer id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Manufacturer manufacturer = manufacturerService.get(id);
			response = ResponseEntity.ok(manufacturer);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/manufacturer/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			manufacturerService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
