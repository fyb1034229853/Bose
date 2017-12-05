package com.itheima.service.impl;

import com.itheima.dao.CustomerRepository;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findByUnbend() {
        return customerRepository.findByUnbend();
    }

    @Override
    public List<Customer> findBybend(String id) {
        return customerRepository.findByFixedAreaId(id);
    }

    @Override
    public void bend(List<Long> ids, String fixedAreaId) {
        customerRepository.setNnull(fixedAreaId);
        if(ids.size()==0){
            return ;
        }
        for (Long aLong : ids) {
            customerRepository.bend(fixedAreaId,aLong);
        }
    }
}
