package com.spring.test.cxf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.cxf.model.Product;

@Transactional(propagation = Propagation.REQUIRED)
public class ProductDao {

	@PersistenceContext(unitName = "jpaSpringData")
	private EntityManager em;

	public int save(Product product) {
		em.persist(product);
		return product.getId();
	}

	public List<Product> getAll() {
		return em.createQuery("SELECT p FROM Product p", Product.class)
				.getResultList();
	}

	public Product retrieveProduct(int idProduct) {
		return em.find(Product.class, idProduct);
	}

	public int deleteProduct(Product product) {
		em.remove(em.contains(product) ? product : em.merge(product));
		return product.getId();
	}
	
	public void  deleteById(int id){
		em.remove(em.find(Product.class, id));
	}

	public Product update(Product product) {
		return em.merge(product);
	}

}
