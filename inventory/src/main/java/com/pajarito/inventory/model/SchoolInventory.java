package com.pajarito.inventory.model;
import lombok.Data;
import java.util.Date;

@Data
public class SchoolInventory {
	private int id;
	private String name;
	private String description;
	private int equipmentId;
	private String equipmentName;
	private int categoryId;
	private String categoryName;
	private int manufacturerId;
	private String manufacturerName;
	private double quantityAvailable;
	private int conditionId;
	private String conditionName;
	private String serialNumber;
	private Date lastUpdated;
	private Date created;
	@Override
	public String toString(){
		return name;
	}
}
