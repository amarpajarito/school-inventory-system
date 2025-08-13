package com.pajarito.inventory.service;
import com.pajarito.inventory.model.SchoolInventory;
public interface SchoolInventoryService {
	SchoolInventory[] getAll() throws Exception;
	SchoolInventory get(Integer id) throws Exception;
	SchoolInventory create(SchoolInventory schoolInventory) throws Exception;
	SchoolInventory update(SchoolInventory schoolInventory) throws Exception;
	void delete(Integer id) throws Exception;
}
