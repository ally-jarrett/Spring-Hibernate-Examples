package com.spring.test.cxf.services.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.test.cxf.dao.ProductDao;
import com.spring.test.cxf.model.Product;
import com.spring.test.cxf.services.ProductServices;

public class ProductServicesImpl implements ProductServices {
	
	@Autowired
	ProductDao productDao;

	public Product getProduct(int id) {
		return productDao.retrieveProduct(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAll();
	}

	@Override
	public Response saveProduct(Product product) {
		int newProdId = productDao.save(product);
		product.setId(newProdId);
		return Response.status(201).entity(product).build();
	}

	@Override
	public Response updateProduct(int id, Product product) {		
		if (id <= 0 ) {
			return Response.status(422).build();
		}
		product.setId(id);
		productDao.update(product);
		return Response.status(200).entity(product).build();
	}

	@Override
	public Response deleteProduct(int id) {
		productDao.deleteById(id);
		return Response.status(200).build();
	}
		

}
