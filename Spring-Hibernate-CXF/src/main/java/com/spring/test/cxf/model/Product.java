package com.spring.test.cxf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column
	private String productName;
	
	@Column
	private String productManufacturer;
	
	@Column
	private String colour;
	
	public Product() {}
	
	public Product(String productName, String productManufacturer, String colour) {
		this.productName = productName;
		this.productManufacturer = productManufacturer;
		this.colour = colour;
	}

	public Product(int id, String productName, String productManufacturer, String colour) {
		this.id = id;
		this.productName = productName;
		this.productManufacturer = productManufacturer;
		this.colour = colour;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductManufacturer() {
		return productManufacturer;
	}
	
	public void setProductManufacturer(String productManufacturer) {
		this.productManufacturer = productManufacturer;
	}
	
	public String getColour() {
		return colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	} 
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName
				+ ", productManufacturer=" + productManufacturer + ", colour="
				+ colour + "]";
	}

}
