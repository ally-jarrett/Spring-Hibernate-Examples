package com.spring.test.cxf.utilities;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.spring.test.cxf.dao.ProductDao;
import com.spring.test.cxf.model.Product;

/**
 * Really basic bean to insert some data into the DB on startup
 * 
 */
public class StartupBean {

	private static final Logger LOG = LoggerFactory.getLogger(StartupBean.class);

	@Autowired
	@Qualifier("productDao")
	ProductDao productDao;

	
	/**
	 * Populate DB with some records to play with on startup... 
	 * 
	 * @throws Exception
	 */
	public void create() throws Exception {
		
		int dbRecords = this.countDBRecords();	
		if (dbRecords > 0) {

			LOG.info("There are already " + dbRecords + " records in the DB ");

		} else {

			LOG.info("Attempting to Populate Database");

			List<Product> productsArray = new ArrayList<Product>();

			Product prod1 = new Product();
			prod1.setProductName("MAC Book Pro Retina 15");
			prod1.setProductManufacturer("Apple");
			prod1.setColour("Silver");
			productsArray.add(prod1);

			Product prod2 = new Product();
			prod2.setProductName("MAC Book Air");
			prod2.setProductManufacturer("Apple");
			prod2.setColour("Silver");
			productsArray.add(prod2);

			Product prod3 = new Product();
			prod3.setProductName("MAC Book Retina 13");
			prod3.setProductManufacturer("Apple");
			prod3.setColour("Silver");
			productsArray.add(prod3);

			for (Product p : productsArray) {
				this.insertNewRecord(p);
			}

			LOG.info("Database Successfully Populated");
			dbRecords = this.countDBRecords();

			if (dbRecords > 0) {
				LOG.info("There is a total of " + dbRecords
						+ " products in the DB");
			} else {
				LOG.info("There are 0 records in the DB, Something went wrong....");
			}
		}
	}

	/**
	 * Insert new Record
	 * 
	 * @param product
	 */
	private void insertNewRecord(Product product) {
		try {
			LOG.info("Inserting new Record into Database...");
			productDao.save(product);
		} catch (Exception e) {
			LOG.info("Something went wrong with DAO....");
			LOG.info("Exception: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Return number of records in the Product Table
	 * 
	 * @return
	 */
	private int countDBRecords() {
		return productDao.getAll().size();
	}
}
