package com.pajarito.inventory.service;
import com.pajarito.inventory.model.Condition;
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
public class ConditionService {
	Logger logger = LoggerFactory.getLogger(ConditionService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/condition";

	protected static ConditionService service= null;
	public static ConditionService getService(){
		if(service == null){
			service = new ConditionService();
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

	public Condition get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Condition> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Condition.class);
		return response.getBody();
	}

	public Condition[] getAll() {
		String url = endpointUrl;
		logger.info("getConditions: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Condition[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Condition[].class);
		Condition[] conditions = response.getBody();
		return conditions;
	}

	public Condition create(Condition condition) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Condition> request = new HttpEntity<>(condition, headers);
		final ResponseEntity<Condition> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Condition.class);
		return response.getBody();
	}
	public Condition update(Condition condition) {
		logger.info("update: " + condition.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Condition> request = new HttpEntity<>(condition, headers);
		final ResponseEntity<Condition> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Condition.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Condition> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Condition> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Condition.class);
	}
}
