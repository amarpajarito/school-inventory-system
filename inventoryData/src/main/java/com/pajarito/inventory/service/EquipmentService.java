package com.pajarito.inventory.service;
import com.pajarito.inventory.model.Equipment;
public interface EquipmentService {
	Equipment[] getAll() throws Exception;
	Equipment get(Integer id) throws Exception;
	Equipment create(Equipment equipment) throws Exception;
	Equipment update(Equipment equipment) throws Exception;
	void delete(Integer id) throws Exception;
}
