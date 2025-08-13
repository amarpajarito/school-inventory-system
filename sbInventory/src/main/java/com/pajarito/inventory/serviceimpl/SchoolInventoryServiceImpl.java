package com.pajarito.inventory.serviceimpl;
import com.pajarito.inventory.entity.SchoolInventoryData;
import com.pajarito.inventory.model.SchoolInventory;
import com.pajarito.inventory.repository.SchoolInventoryDataRepository;
import com.pajarito.inventory.service.SchoolInventoryService;
import com.pajarito.inventory.transform.TransformSchoolInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class SchoolInventoryServiceImpl implements SchoolInventoryService {
	Logger logger = LoggerFactory.getLogger(SchoolInventoryServiceImpl.class);
	@Autowired
	SchoolInventoryDataRepository schoolInventoryDataRepository;
	@Autowired
	TransformSchoolInventoryService tansformSchoolInventoryService;
	@Override
	public SchoolInventory[] getAll() {
		List<SchoolInventoryData> schoolInventoriesData = new ArrayList<>();
		List<SchoolInventory> schoolInventories = new ArrayList<>();
		schoolInventoryDataRepository.findAll().forEach(schoolInventoriesData::add);
		Iterator<SchoolInventoryData> it = schoolInventoriesData.iterator();
		while(it.hasNext()) {
			SchoolInventoryData schoolInventoryData = it.next();
			SchoolInventory ecommerce = tansformSchoolInventoryService.transform(schoolInventoryData);
			schoolInventories.add(ecommerce);
		}
		SchoolInventory[] array = new SchoolInventory[schoolInventories.size()];
		for  (int i=0; i<schoolInventories.size(); i++){
			array[i] = schoolInventories.get(i);
		}
		return array;
	}
	@Override
	public SchoolInventory create(SchoolInventory schoolInventory) {
		logger.info(" add:Input " + schoolInventory.toString());
		SchoolInventoryData schoolInventoryData = tansformSchoolInventoryService.transform(schoolInventory);
		schoolInventoryData = schoolInventoryDataRepository.save(schoolInventoryData);
		logger.info(" add:Input " + schoolInventoryData.toString());
			SchoolInventory newSchoolInventory = tansformSchoolInventoryService.transform(schoolInventoryData);
		return newSchoolInventory;
	}

	@Override
	public SchoolInventory update(SchoolInventory schoolInventory) {
		SchoolInventory updatedSchoolInventory = null;
		int id = schoolInventory.getId();
		Optional<SchoolInventoryData> optional  = schoolInventoryDataRepository.findById(schoolInventory.getId());
		if(optional.isPresent()){
			SchoolInventoryData originalSchoolInventoryData = tansformSchoolInventoryService.transform(schoolInventory);
			originalSchoolInventoryData.setCreated(optional.get().getCreated());
			SchoolInventoryData schoolInventoryData = schoolInventoryDataRepository.save(originalSchoolInventoryData);
			updatedSchoolInventory = tansformSchoolInventoryService.transform(schoolInventoryData);
		}
		else {
			logger.error("School Inventory record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedSchoolInventory;
	}

	@Override
	public SchoolInventory get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		SchoolInventory schoolInventory = null;
		Optional<SchoolInventoryData> optional = schoolInventoryDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			schoolInventory = tansformSchoolInventoryService.transform(optional.get());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return schoolInventory;
	}
	@Override
	public void delete(Integer id) {
		SchoolInventory schoolInventory = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<SchoolInventoryData> optional = schoolInventoryDataRepository.findById(id);
		if( optional.isPresent()) {
			SchoolInventoryData schoolInventoryDatum = optional.get();
			schoolInventoryDataRepository.delete(optional.get());
			logger.info(" Successfully deleted School Inventory record with id: " + Integer.toString(id));
			schoolInventory = tansformSchoolInventoryService.transform(optional.get());
		}
		else {
			logger.error(" Unable to locate school inventory with id:" +  Integer.toString(id));
		}
	}
}
