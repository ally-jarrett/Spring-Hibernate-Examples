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

import com.spring.test.dao.StockDRDaoImpl;
import com.spring.test.dao.StockDaoImpl;
import com.spring.test.model.Stock;
import com.spring.test.model.StockDailyRecord;
import com.spring.test.model.StockDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

	@Autowired
	StockDaoImpl stockDao;
	
	@Autowired
	StockDRDaoImpl stockDRDao;

	private static Stock rhtStock;
	private static StockDetail rhtStockDetail;
	private static StockDailyRecord rhtStockDailyRecord;

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
		
		rhtStockDailyRecord = new StockDailyRecord();
		rhtStockDailyRecord.setPriceOpen(new Float("1.2"));
		rhtStockDailyRecord.setPriceClose(new Float("1.1"));
		rhtStockDailyRecord.setPriceChange(new Float("10.0"));
		rhtStockDailyRecord.setVolume(3000000L);
		rhtStockDailyRecord.setDate(new Date());
		
		rhtStockDailyRecord.setStock(rhtStock);        
		rhtStock.getStockDailyRecords().add(rhtStockDailyRecord);

		rhtStock.setStockDetail(rhtStockDetail);
		rhtStockDetail.setStock(rhtStock);
		
	}

	@Test
	public void testStockQuoteDao() {
		
		// Check our Object has been created 
		assertNotNull(rhtStockDailyRecord);

		// Save Stock Quote
		stockDao.save(rhtStock);
		stockDao.save(rhtStockDailyRecord);
		
		// Assert the Stock Count in DB is now 1
		assertTrue(stockDao.rowCount("stock_daily_record") == 1);
		assertTrue(stockDao.rowCount("stock") == 1);
		assertTrue(stockDao.rowCount("stock_detail") == 1);

		/**
		 *  Test Retrieve Statement by passing our Stock ID
		 *  We assume the AUTO-Generated id is 1 
		 */
		Stock retrievedStock = stockDao.retrieveStock(1);
		assertNotNull(retrievedStock);
		
		StockDailyRecord sdr = stockDRDao.retrieveStock(retrievedStock.getStockId());
		assertNotNull(sdr);
		
	}

}
