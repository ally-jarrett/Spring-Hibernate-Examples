package com.spring.test.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.model.Stock;

@Transactional(propagation = Propagation.REQUIRED)
public class StockDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Object obj) {
		em.persist(obj);
	}
	
	public List<Stock> getAll() {
		return em.createQuery("SELECT s FROM Stock s", Stock.class)
				.getResultList();
	}
	
	public Stock retrieveStock(Integer stockID) {
		return em.find(Stock.class, stockID);
	}
	
	public int rowCount(String table){
		String queryString = "SELECT Count(*) FROM " + table;  
		BigInteger count = (BigInteger) em.createNativeQuery(queryString).getSingleResult();
		return count.intValue();
	}

}
