package com.itheima.service.base.impl;

import com.itheima.dao.base.CourierRepository;
import com.itheima.domain.base.Courier;
import com.itheima.service.base.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierRepository courierRepository;

    @Override
    public void save(Courier courier) {
        courierRepository.save(courier);
    }

    @Override
    public Page<Courier> findPage(Pageable pageable) {
        return courierRepository.findAll(pageable);

    }

    @Override
    public Page<Courier> findPage(Specification<Courier> specification, Pageable pageable) {
        return courierRepository.findAll(specification,pageable);
    }

    @Override
    public void updaedelete(String sid) {
        String[] split = sid.split(",");
        for (String id : split){
            courierRepository.updateDelete(Long.parseLong(id));
        }
    }

    @Override
    public void updaerestor(String sid) {
        String[] split = sid.split(",");
        for (String id : split){
            courierRepository.updaterestor(Long.parseLong(id));
        }
    }

    @Override
    public List<Courier> findAll() {
        return courierRepository.findByDeltagIsNull();
    }
}
