package com.pajarito.inventory.transform;
import com.pajarito.inventory.entity.SchoolInventoryData;
import com.pajarito.inventory.model.SchoolInventory;
import org.springframework.stereotype.Service;
@Service
public class TransformSchoolInventoryServiceImpl implements TransformSchoolInventoryService {
	@Override
	public SchoolInventoryData transform(SchoolInventory schoolInventory){
		SchoolInventoryData schoolInventoryData = new SchoolInventoryData();
		schoolInventoryData.setId(schoolInventory.getId());
		schoolInventoryData.setName(schoolInventory.getName());
		schoolInventoryData.setDescription(schoolInventory.getDescription());
		schoolInventoryData.setEquipmentId(schoolInventory.getEquipmentId());
		schoolInventoryData.setEquipmentName(schoolInventory.getEquipmentName());
		schoolInventoryData.setCategoryId(schoolInventory.getCategoryId());
		schoolInventoryData.setCategoryName(schoolInventory.getCategoryName());
		schoolInventoryData.setManufacturerId(schoolInventory.getManufacturerId());
		schoolInventoryData.setManufacturerName(schoolInventory.getManufacturerName());
		schoolInventoryData.setQuantityAvailable(schoolInventory.getQuantityAvailable());
		schoolInventoryData.setConditionId(schoolInventory.getConditionId());
		schoolInventoryData.setConditionName(schoolInventory.getConditionName());
		schoolInventoryData.setSerialNumber(schoolInventory.getSerialNumber());
		return schoolInventoryData;
	}
	@Override

	public SchoolInventory transform(SchoolInventoryData schoolInventoryData){;
		SchoolInventory schoolInventory = new SchoolInventory();
		schoolInventory.setId(schoolInventoryData.getId());
		schoolInventory.setName(schoolInventoryData.getName());
		schoolInventory.setDescription(schoolInventoryData.getDescription());
		schoolInventory.setEquipmentId(schoolInventoryData.getEquipmentId());
		schoolInventory.setEquipmentName(schoolInventoryData.getEquipmentName());
		schoolInventory.setCategoryId(schoolInventoryData.getCategoryId());
		schoolInventory.setCategoryName(schoolInventoryData.getCategoryName());
		schoolInventory.setManufacturerId(schoolInventoryData.getManufacturerId());
		schoolInventory.setManufacturerName(schoolInventoryData.getManufacturerName());
		schoolInventory.setQuantityAvailable(schoolInventoryData.getQuantityAvailable());
		schoolInventory.setConditionId(schoolInventoryData.getConditionId());
		schoolInventory.setConditionName(schoolInventoryData.getConditionName());
		schoolInventory.setSerialNumber(schoolInventoryData.getSerialNumber());
		schoolInventory.setCreated(schoolInventoryData.getCreated());
		schoolInventory.setLastUpdated(schoolInventoryData.getLastUpdated());
		return schoolInventory;
	}
}
