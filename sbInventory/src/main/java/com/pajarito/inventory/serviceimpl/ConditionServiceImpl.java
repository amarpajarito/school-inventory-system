package com.pajarito.inventory.serviceimpl;
import com.pajarito.inventory.entity.ConditionData;
import com.pajarito.inventory.model.Condition;
import com.pajarito.inventory.repository.ConditionDataRepository;
import com.pajarito.inventory.service.ConditionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ConditionServiceImpl implements ConditionService {
	Logger logger = LoggerFactory.getLogger(ConditionServiceImpl.class);
	@Autowired
	ConditionDataRepository conditionDataRepository;
	@Autowired
	@Override
	public Condition[] getAll() {
		List<ConditionData> conditionsData = new ArrayList<>();
		List<Condition> conditions = new ArrayList<>();
		conditionDataRepository.findAll().forEach(conditionsData::add);
		Iterator<ConditionData> it = conditionsData.iterator();
		while(it.hasNext()) {
			ConditionData conditionData = it.next();
			Condition condition = new Condition();
			condition.setId(conditionData.getId());
			condition.setName(conditionData.getName());
			conditions.add(condition);
		}
		Condition[] array = new Condition[conditions.size()];
		for  (int i=0; i<conditions.size(); i++){
			array[i] = conditions.get(i);
		}
		return array;
	}
	@Override
	public Condition create(Condition condition) {
		logger.info(" add:Input " + condition.toString());
		ConditionData conditionData = new ConditionData();
		conditionData.setName(condition.getName());
		conditionData = conditionDataRepository.save(conditionData);
		logger.info(" add:Input " + conditionData.toString());
			Condition newCondition = new Condition();
			newCondition.setId(conditionData.getId());
			newCondition.setName(conditionData.getName());
		return newCondition;
	}

	@Override
	public Condition update(Condition condition) {
		Condition updatedCondition = null;
		int id = condition.getId();
		Optional<ConditionData> optional  = conditionDataRepository.findById(condition.getId());
		if(optional.isPresent()){
			ConditionData originalConditionData = new ConditionData();
			originalConditionData.setId(condition.getId());
			originalConditionData.setName(condition.getName());
			originalConditionData.setCreated(optional.get().getCreated());
			ConditionData conditionData = conditionDataRepository.save(originalConditionData);
			updatedCondition = new Condition();
			updatedCondition.setId(conditionData.getId());
			updatedCondition.setName(conditionData.getName());
			updatedCondition.setCreated(conditionData.getCreated());
			updatedCondition.setLastUpdated(conditionData.getLastUpdated());
		}
		else {
			logger.error("Condition record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedCondition;
	}

	@Override
	public Condition get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Condition condition = null;
		Optional<ConditionData> optional = conditionDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			condition = new Condition();
			condition.setId(optional.get().getId());
			condition.setName(optional.get().getName());
			condition.setCreated(optional.get().getCreated());
			condition.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return condition;
	}
	@Override
	public void delete(Integer id) {
		Condition condition = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<ConditionData> optional = conditionDataRepository.findById(id);
		if( optional.isPresent()) {
			ConditionData conditionDatum = optional.get();
			conditionDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Status record with id: " + Integer.toString(id));
			condition = new Condition();
			condition.setId(optional.get().getId());
			condition.setName(optional.get().getName());
			condition.setCreated(optional.get().getCreated());
			condition.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate condition with id:" +  Integer.toString(id));
		}
	}
}
