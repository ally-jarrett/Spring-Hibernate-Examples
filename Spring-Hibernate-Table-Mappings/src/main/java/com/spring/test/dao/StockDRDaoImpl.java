package com.spring.test.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.model.Stock;
import com.spring.test.model.StockDailyRecord;

@Transactional(propagation = Propagation.REQUIRED)
public class StockDRDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Object obj) {
		em.persist(obj);
	}
	
	public List<StockDailyRecord> getAll() {
		return em.createQuery("SELECT s FROM stock_daily_record s", StockDailyRecord.class)
				.getResultList();
	}
	
	public StockDailyRecord retrieveStock(Integer stockID) {
		//String queryString = "SELECT s FROM stock_daily_record WHERE "
		//return em.createNamedQuery(arg0);
		return em.find(StockDailyRecord.class, stockID);
	}
	
	public int rowCount(String table){
		String queryString = "SELECT Count(*) FROM " + table;  
		BigInteger count = (BigInteger) em.createNativeQuery(queryString).getSingleResult();
		return count.intValue();
	}

}
