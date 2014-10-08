package com.spring.test.cxf.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.test.cxf.dao.ProductDao;
import com.spring.test.cxf.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-test.xml")
public class ProductDaoTest {
	
	@Autowired
	private ProductDao productDao;
	
	private static Product product1;
	private static Product product2;
	private static Product product3;
	private static Product product4;
	
	@BeforeClass
	public static void initPerson() {
		
		/**
		 * We dont provide an ID as its generated.. 
		 */
		product1 = new Product("Mac Book Pro", "Apple", "Silver");
        product2 = new Product("Mac Book Air", "Apple", "Silver");
        product3 = new Product("Mac Book Pro Retina", "Apple", "Silver");
        product4 = new Product("iPad", "Apple", "White");

	}
	
	@Test
	public void testProductDaoCRUD() {

		// ** C ** //  Create and Persist some products
		List<Product> productsArray = new ArrayList<Product>();
		productsArray.add(product1);
		productsArray.add(product2);
		productsArray.add(product3);
		productsArray.add(product4);
		
		for ( Product prod : productsArray ) {
			productDao.save(prod);
		}
		
        assertTrue(productDao.getAll().size() == 4);
        
        // ** R ** //  Read the products from the DB
        Product myNewProduct = productDao.retrieveProduct(1);
        
        assertTrue(myNewProduct.getId() == 1);
        assertTrue(myNewProduct.getProductName().equals("Mac Book Pro"));
        
        // ** U ** // Update the products from the DB 
        
        myNewProduct.setProductName("Mac Book Pro 15");
        myNewProduct.setColour("Black");
        
        productDao.update(myNewProduct);
        
        Product myNewUpdatedProduct = productDao.retrieveProduct(1);
        assertTrue(myNewUpdatedProduct.getId() == 1);
        assertTrue(myNewUpdatedProduct.getProductName().equals("Mac Book Pro 15"));
        assertTrue(myNewUpdatedProduct.getColour().equals("Black"));
        
        // ** D ** //  Delete the products from the DB
        
        productDao.deleteProduct(myNewUpdatedProduct);
        
        List<Product> updatedProductsArray = productDao.getAll();
        assertTrue(updatedProductsArray.size() == 3);
        
        for ( Product prod :  updatedProductsArray ) {
        	assertFalse(prod.getId() == 1);
        	assertFalse(prod.getProductName().equals("Mac Book Pro 15"));
        } 
		
	}
	
}
