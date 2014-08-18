package com.spring.test.mappings;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.test.dao.StockDaoImpl;
import com.spring.test.model.Stock;
import com.spring.test.model.StockDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToOneTest {
	
	@Autowired
	StockDaoImpl stockDao;
	
	@Test
	public void testStockQuote(){
		
		// Create new Stock and CtockDetail objects
		Stock stock = new Stock();
		 
		stock.setStockCode("12345");
		stock.setStockName("RHT");
 
		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("RedHat Inc");
		stockDetail.setCompDesc("Open Source Software");
		stockDetail.setRemark("Test Remark");
		stockDetail.setListedDate(new Date());
 
		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);
		
		// Save Stock Quote
		stockDao.save(stock);
		
		// Get all Stock from Table, obviously should only be 1
		List<Stock> listStock = stockDao.getAll();
		assertNotNull(listStock);
		assertTrue(listStock.size() == 1);
		
		// Get the Stock ID of Saved Stock Object 
		int retrieveID = listStock.get(0).getStockId();
		System.out.println(retrieveID);		
		
		// Test Retrieve Statement by passing our Stock ID  
		Stock retrievedStock = stockDao.retrieveStock(retrieveID);
		assertNotNull(retrievedStock);
		assertTrue(retrievedStock.getStockName().equals("RHT"));
		assertTrue(retrievedStock.getStockDetail().getCompName().equals("RedHat Inc"));
	}

}
