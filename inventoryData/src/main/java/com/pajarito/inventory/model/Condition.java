package com.pajarito.inventory.model;
import lombok.Data;
import java.util.Date;

@Data
public class Condition {
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
}
