package com.itheima.service.base.impl;

import com.itheima.dao.base.StandardRepository;
import com.itheima.domain.base.Standard;
import com.itheima.service.base.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("standardService")
public class StandarServiceImpl implements StandardService {
    @Autowired
    private StandardRepository standardRepository;

    @Override
    public void save(Standard standard) {
        standardRepository.save(standard);
        System.out.println("接收到了请求");


    }

    @Override
    public Page<Standard> findPage(Pageable page) {
        return standardRepository.findAll(page);
    }

    @Override
    public List<Standard> findAll() {

        return standardRepository.findAll();
    }



}
