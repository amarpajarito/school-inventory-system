package com.pajarito.inventory.serviceimpl;
import com.pajarito.inventory.entity.EquipmentData;
import com.pajarito.inventory.model.Equipment;
import com.pajarito.inventory.repository.EquipmentDataRepository;
import com.pajarito.inventory.service.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class EquipmentServiceImpl implements EquipmentService {
	Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);
	@Autowired
	EquipmentDataRepository equipmentDataRepository;
	@Autowired
	@Override
	public Equipment[] getAll() {
		List<EquipmentData> equipmentsData = new ArrayList<>();
		List<Equipment> equipments = new ArrayList<>();
		equipmentDataRepository.findAll().forEach(equipmentsData::add);
		Iterator<EquipmentData> it = equipmentsData.iterator();
		while(it.hasNext()) {
			EquipmentData equipmentData = it.next();
			Equipment equipment = new Equipment();
			equipment.setId(equipmentData.getId());
			equipment.setName(equipmentData.getName());
			equipments.add(equipment);
		}
		Equipment[] array = new Equipment[equipments.size()];
		for  (int i=0; i<equipments.size(); i++){
			array[i] = equipments.get(i);
		}
		return array;
	}
	@Override
	public Equipment create(Equipment equipment) {
		logger.info(" add:Input " + equipment.toString());
		EquipmentData equipmentData = new EquipmentData();
		equipmentData.setName(equipment.getName());
		equipmentData = equipmentDataRepository.save(equipmentData);
		logger.info(" add:Input " + equipmentData.toString());
			Equipment newEquipment = new Equipment();
			newEquipment.setId(equipmentData.getId());
			newEquipment.setName(equipmentData.getName());
		return newEquipment;
	}

	@Override
	public Equipment update(Equipment equipment) {
		Equipment updatedEquipment = null;
		int id = equipment.getId();
		Optional<EquipmentData> optional  = equipmentDataRepository.findById(equipment.getId());
		if(optional.isPresent()){
			EquipmentData originalEquipmentData = new EquipmentData();
			originalEquipmentData.setId(equipment.getId());
			originalEquipmentData.setName(equipment.getName());
			originalEquipmentData.setCreated(optional.get().getCreated());
			EquipmentData equipmentData = equipmentDataRepository.save(originalEquipmentData);
			updatedEquipment = new Equipment();
			updatedEquipment.setId(equipmentData.getId());
			updatedEquipment.setName(equipmentData.getName());
			updatedEquipment.setCreated(equipmentData.getCreated());
			updatedEquipment.setLastUpdated(equipmentData.getLastUpdated());
		}
		else {
			logger.error("Equipment record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedEquipment;
	}

	@Override
	public Equipment get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Equipment equipment = null;
		Optional<EquipmentData> optional = equipmentDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			equipment = new Equipment();
			equipment.setId(optional.get().getId());
			equipment.setName(optional.get().getName());
			equipment.setCreated(optional.get().getCreated());
			equipment.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return equipment;
	}
	@Override
	public void delete(Integer id) {
		Equipment equipment = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<EquipmentData> optional = equipmentDataRepository.findById(id);
		if( optional.isPresent()) {
			EquipmentData equipmentDatum = optional.get();
			equipmentDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Equipment record with id: " + Integer.toString(id));
			equipment = new Equipment();
			equipment.setId(optional.get().getId());
			equipment.setName(optional.get().getName());
			equipment.setCreated(optional.get().getCreated());
			equipment.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate equipment with id:" +  Integer.toString(id));
		}
	}
}
