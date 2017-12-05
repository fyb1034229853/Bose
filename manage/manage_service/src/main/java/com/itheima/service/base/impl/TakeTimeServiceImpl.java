package com.itheima.service.base.impl;

import com.itheima.dao.base.TakeTimeRepository;
import com.itheima.domain.base.TakeTime;
import com.itheima.service.base.TakeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService{
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    @Override
    public List<TakeTime> findAll() {
        return takeTimeRepository.findAll();
    }
}
