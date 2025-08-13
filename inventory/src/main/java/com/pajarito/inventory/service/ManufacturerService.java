package com.pajarito.inventory.service;
import com.pajarito.inventory.model.Manufacturer;
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
public class ManufacturerService {
	Logger logger = LoggerFactory.getLogger(ManufacturerService.class);
	@Value("${service.api.endpoint}")
	private String endpointUrl = "http://localhost:8080/api/manufacturer";

	protected static ManufacturerService service= null;
	public static ManufacturerService getService(){
		if(service == null){
			service = new ManufacturerService();
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

	public Manufacturer get(Integer id) {
		String url = endpointUrl + "/" + Integer.toString(id);
		logger.info("get: "  + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Manufacturer> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Manufacturer.class);
		return response.getBody();
	}

	public Manufacturer[] getAll() {
		String url = endpointUrl;
		logger.info("getInventorys: " + url);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity request = new HttpEntity<>(null, headers);
		final ResponseEntity<Manufacturer[]> response =
		getRestTemplate().exchange(url, HttpMethod.GET, request, Manufacturer[].class);
		Manufacturer[] manufacturers = response.getBody();
		return manufacturers;
	}

	public Manufacturer create(Manufacturer manufacturer) {
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Manufacturer> request = new HttpEntity<>(manufacturer, headers);
		final ResponseEntity<Manufacturer> response =
		getRestTemplate().exchange(url, HttpMethod.PUT, request, Manufacturer.class);
		return response.getBody();
	}
	public Manufacturer update(Manufacturer manufacturer) {
		logger.info("update: " + manufacturer.toString());
		String url = endpointUrl;
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Manufacturer> request = new HttpEntity<>(manufacturer, headers);
		final ResponseEntity<Manufacturer> response =
		getRestTemplate().exchange(url, HttpMethod.POST, request, Manufacturer.class);
		return response.getBody();
	}

	public void delete(Integer id){
		logger.info("delete: " + Integer.toString(id));
		String url = endpointUrl + " / " + Integer.toString(id);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Manufacturer> request = new HttpEntity<>(null, headers);
		final ResponseEntity<Manufacturer> response =
		getRestTemplate().exchange(url, HttpMethod.DELETE, request, Manufacturer.class);
	}
}
