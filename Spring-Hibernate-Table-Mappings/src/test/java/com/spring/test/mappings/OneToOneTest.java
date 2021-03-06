package com.spring.test.mappings;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
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

	private static Stock rhtStock;
	private static StockDetail rhtStockDetail;

	@BeforeClass
	public static void setupDB() {
		
		// Create new Stock and StockDetail objects
		rhtStock = new Stock();

		rhtStock.setStockCode("12345");
		rhtStock.setStockName("RHT");

		rhtStockDetail = new StockDetail();
		rhtStockDetail.setCompName("RedHat Inc");
		rhtStockDetail.setCompDesc("Open Source Software");
		rhtStockDetail.setRemark("Test Remark");
		rhtStockDetail.setListedDate(new Date());

		rhtStock.setStockDetail(rhtStockDetail);
		rhtStockDetail.setStock(rhtStock);
		
	}

	@Test
	public void testStockQuoteDao() {
		
		// Check our Object has been created 
		assertNotNull(rhtStock);

		// Save Stock Quote
		stockDao.save(rhtStock);
		
		// Assert the Stock Count in DB is now 1
		assertTrue(stockDao.rowCount("stock") == 1);

		/**
		 *  Test Retrieve Statement by passing our Stock ID
		 *  We assume the AUTO-Generated id is 1 
		 */
		Stock retrievedStock = stockDao.retrieveStock(1);
		assertNotNull(retrievedStock);
		assertTrue(retrievedStock.getStockName().equals("RHT"));
		assertTrue(retrievedStock.getStockDetail().getCompName()
				.equals("RedHat Inc"));
	}

}
