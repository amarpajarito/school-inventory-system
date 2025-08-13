package com.pajarito.inventory.transform;
import com.pajarito.inventory.entity.SchoolInventoryData;
import com.pajarito.inventory.model.SchoolInventory;
public interface TransformSchoolInventoryService {
	SchoolInventoryData transform(SchoolInventory schoolInventory);
	SchoolInventory transform(SchoolInventoryData schoolInventoryData);
}
