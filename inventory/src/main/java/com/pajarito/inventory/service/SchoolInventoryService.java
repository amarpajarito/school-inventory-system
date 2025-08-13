package com.pajarito.inventory.service;
import com.pajarito.inventory.model.SchoolInventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SchoolInventoryService {
	Logger logger = LoggerFactory.getLogger(SchoolInventoryService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/schoolInventory";

	protected static SchoolInventoryService service= null;
	public static SchoolInventoryService getService(){
		if(service == null){
			service = new SchoolInventoryService();
		}
		return service;
	}

	RestTemplate restTemplate = null;
	public RestTemplate getRestTemplate() {
		if(restTemplate == null) {
		restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
		}
		return restTemplate;
	}

	public SchoolInventory get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<SchoolInventory> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, SchoolInventory.class);
		return response.getBody();
	}

	public SchoolInventory[] getAll() {
		String url = endpointUrl;
		logger.info("getSchoolInventories: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<SchoolInventory[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, SchoolInventory[].class);
		SchoolInventory[] schoolInventories = response.getBody();
		return schoolInventories;
	}

	public SchoolInventory create(SchoolInventory schoolInventory) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<SchoolInventory> request = new HttpEntity<>(schoolInventory, headers);
		final ResponseEntity<SchoolInventory> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, SchoolInventory.class);
		return response.getBody();
	}
	public SchoolInventory update(SchoolInventory schoolInventory) {
		logger.info("update: " + schoolInventory.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<SchoolInventory> request = new HttpEntity<>(schoolInventory, headers);
		final ResponseEntity<SchoolInventory> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, SchoolInventory.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<SchoolInventory> request = new HttpEntity<>(null, headers);
		final ResponseEntity<SchoolInventory> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, SchoolInventory.class);
	}
}
