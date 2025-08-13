package com.pajarito.inventory.controller;
import com.pajarito.inventory.model.Condition;
import com.pajarito.inventory.service.ConditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ConditionController {
	Logger logger = LoggerFactory.getLogger( ConditionController.class);
	@Autowired
	private ConditionService conditionService;
	@GetMapping("/api/condition")
	public ResponseEntity<?> listStatus()
{
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Condition[] status = conditionService.getAll();
			response =  ResponseEntity.ok().headers(headers).body(status);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PutMapping("api/condition")
	public ResponseEntity<?> add(@RequestBody Condition condition){
		logger.info("Input >> " + condition.toString() );
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Condition newCondition = conditionService.create(condition);
			logger.info("created condition >> " + newCondition.toString() );
			response = ResponseEntity.ok(newCondition);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve condition with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@PostMapping("api/condition")
	public ResponseEntity<?> update(@RequestBody Condition condition){
		logger.info("Update Input >> condition.toString() ");
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Condition newCondition = conditionService.update(condition);
			response = ResponseEntity.ok(condition);
		}
		catch( Exception ex)
		{
			logger.error("Failed to retrieve condition with id : {}", ex.getMessage(), ex);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}

	@GetMapping("api/condition/{id}")
	public ResponseEntity<?> get(@PathVariable final Integer id){
		logger.info("Input condition id >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			Condition condition = conditionService.get(id);
			response = ResponseEntity.ok(condition);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
	@DeleteMapping("api/condition/{id}")
	public ResponseEntity<?> delete(@PathVariable final Integer id){
		logger.info("Input >> " + Integer.toString(id));
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<?> response;
		try {
			conditionService.delete(id);
			response = ResponseEntity.ok(null);
		}
		catch( Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return response;
	}
}
