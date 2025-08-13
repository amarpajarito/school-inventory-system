package com.pajarito.inventory.service;
import com.pajarito.inventory.model.Condition;
public interface ConditionService {
	Condition[] getAll() throws Exception;
	Condition get(Integer id) throws Exception;
	Condition create(Condition condition) throws Exception;
	Condition update(Condition condition) throws Exception;
	void delete(Integer id) throws Exception;
}
