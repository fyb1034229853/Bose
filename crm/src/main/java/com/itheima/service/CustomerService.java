package com.itheima.service;

import com.itheima.domain.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


public interface CustomerService {
    @Path("/save")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @POST
    public void save(Customer customer);
    @Path("/findByUnbend")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @GET
    public List<Customer> findByUnbend();
    @Path("/findBybend")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @GET
    public List<Customer> findBybend(@QueryParam("id")String id);
    @Path("/bend")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @PUT
    public void bend(@QueryParam("ids")List<Long> ids,@QueryParam("fixedAreaId")String fixedAreaId);
}
