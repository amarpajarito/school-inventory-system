package com.pajarito.inventory.service;
import com.pajarito.inventory.model.Manufacturer;
public interface ManufacturerService {
	Manufacturer[] getAll() throws Exception;
	Manufacturer get(Integer id) throws Exception;
	Manufacturer create(Manufacturer manufacturer) throws Exception;
	Manufacturer update(Manufacturer manufacturer) throws Exception;
	void delete(Integer id) throws Exception;
}
