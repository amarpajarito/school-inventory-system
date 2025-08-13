package com.pajarito.inventory.serviceimpl;
import com.pajarito.inventory.entity.ManufacturerData;
import com.pajarito.inventory.model.Manufacturer;
import com.pajarito.inventory.repository.ManufacturerRepository;
import com.pajarito.inventory.service.ManufacturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
	Logger logger = LoggerFactory.getLogger(ManufacturerServiceImpl.class);
	@Autowired
	ManufacturerRepository manufacturerDataRepository;
	@Autowired
	@Override
	public Manufacturer[] getAll() {
		List<ManufacturerData> manufacturersData = new ArrayList<>();
		List<Manufacturer> manufacturers = new ArrayList<>();
		manufacturerDataRepository.findAll().forEach(manufacturersData::add);
		Iterator<ManufacturerData> it = manufacturersData.iterator();
		while(it.hasNext()) {
			ManufacturerData manufacturerData = it.next();
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setId(manufacturerData.getId());
			manufacturer.setName(manufacturerData.getName());
			manufacturers.add(manufacturer);
		}
		Manufacturer[] array = new Manufacturer[manufacturers.size()];
		for  (int i=0; i<manufacturers.size(); i++){
			array[i] = manufacturers.get(i);
		}
		return array;
	}
	@Override
	public Manufacturer create(Manufacturer manufacturer) {
		logger.info(" add:Input " + manufacturer.toString());
		ManufacturerData manufacturerData = new ManufacturerData();
		manufacturerData.setName(manufacturer.getName());
		manufacturerData = manufacturerDataRepository.save(manufacturerData);
		logger.info(" add:Input " + manufacturerData.toString());
			Manufacturer newManufacturer = new Manufacturer();
			newManufacturer.setId(manufacturerData.getId());
			newManufacturer.setName(manufacturerData.getName());
		return newManufacturer;
	}

	@Override
	public Manufacturer update(Manufacturer manufacturer) {
		Manufacturer updatedManufacturer = null;
		int id = manufacturer.getId();
		Optional<ManufacturerData> optional  = manufacturerDataRepository.findById(manufacturer.getId());
		if(optional.isPresent()){
			ManufacturerData originalManufacturerData = new ManufacturerData();
			originalManufacturerData.setId(manufacturer.getId());
			originalManufacturerData.setName(manufacturer.getName());
			originalManufacturerData.setCreated(optional.get().getCreated());
			ManufacturerData manufacturerData = manufacturerDataRepository.save(originalManufacturerData);
			updatedManufacturer = new Manufacturer();
			updatedManufacturer.setId(manufacturerData.getId());
			updatedManufacturer.setName(manufacturerData.getName());
			updatedManufacturer.setCreated(manufacturerData.getCreated());
			updatedManufacturer.setLastUpdated(manufacturerData.getLastUpdated());
		}
		else {
			logger.error("Manufacturer record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedManufacturer;
	}

	@Override
	public Manufacturer get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Manufacturer manufacturer = null;
		Optional<ManufacturerData> optional = manufacturerDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			manufacturer = new Manufacturer();
			manufacturer.setId(optional.get().getId());
			manufacturer.setName(optional.get().getName());
			manufacturer.setCreated(optional.get().getCreated());
			manufacturer.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return manufacturer;
	}
	@Override
	public void delete(Integer id) {
		Manufacturer manufacturer = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<ManufacturerData> optional = manufacturerDataRepository.findById(id);
		if( optional.isPresent()) {
			ManufacturerData manufacturerDatum = optional.get();
			manufacturerDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Manufacturer record with id: " + Integer.toString(id));
			manufacturer = new Manufacturer();
			manufacturer.setId(optional.get().getId());
			manufacturer.setName(optional.get().getName());
			manufacturer.setCreated(optional.get().getCreated());
			manufacturer.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate manufacturer with id:" +  Integer.toString(id));
		}
	}
}
