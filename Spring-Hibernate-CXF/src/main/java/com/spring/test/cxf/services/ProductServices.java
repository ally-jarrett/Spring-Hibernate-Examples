package com.spring.test.cxf.services;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.spring.test.cxf.model.Product;

@WebService(serviceName = "productServices")
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface ProductServices {
	
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/product/{id}")
	public Product getProduct(@PathParam("id") int id);
	
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/product/all")
	public List<Product> getAllProducts();
	
	@PUT
    @Path("/product/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateProduct(@PathParam("id") int id, Product product);
	
	@POST
    @Path("/product")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response saveProduct(Product product);
	
	@DELETE
    @Path("/product/{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response deleteProduct(@PathParam("id") int id);

}
