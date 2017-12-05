package com.itheima.dao;

import com.itheima.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("from Customer where fixedAreaId is null")
    public List<Customer> findByUnbend();

    public List<Customer> findByFixedAreaId(String id);
    @Query("update Customer  set fixedAreaId=null  where fixedAreaId=?")
    @Modifying
    void setNnull(String fixedAreaId);
    @Query("update Customer  set fixedAreaId=?  where id=?")
    @Modifying
    void bend(String fixedAreaId,Long id);
}
