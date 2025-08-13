package com.pajarito.inventory.repository;
import com.pajarito.inventory.entity.ManufacturerData;
import org.springframework.data.repository.CrudRepository;
public interface ManufacturerRepository extends CrudRepository<ManufacturerData,Integer> {}