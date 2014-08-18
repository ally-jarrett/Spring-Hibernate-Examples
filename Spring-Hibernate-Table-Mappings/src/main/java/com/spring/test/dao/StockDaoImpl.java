package com.spring.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.model.Stock;

@Transactional(propagation = Propagation.REQUIRED)
public class StockDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Stock stock) {
		em.persist(stock);
	}
	
	public List<Stock> getAll() {
		return em.createQuery("SELECT s FROM Stock s", Stock.class)
				.getResultList();
	}
	
	public Stock retrieveStock(Integer stockID) {
		return em.find(Stock.class, stockID);
	}

}
