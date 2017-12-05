package com.itheima.service.base.impl;

import com.itheima.dao.base.CourierRepository;
import com.itheima.dao.base.FixedArearRepository;
import com.itheima.dao.base.TakeTimeRepository;
import com.itheima.domain.base.Courier;
import com.itheima.domain.base.FixedArea;
import com.itheima.domain.base.TakeTime;
import com.itheima.service.base.FixedAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
    @Autowired
    private FixedArearRepository fixedArearRepository;
    @Override
    public void save(FixedArea fixedArea) {
        fixedArearRepository.save(fixedArea);
    }

    @Override
    public Page<FixedArea> findPage(Pageable pageable) {

         return fixedArearRepository.findAll(pageable);
    }
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    @Override
    public void beanCourier(Long id, Long couriersId, Long takeTimeId) {
        FixedArea fixedArea = fixedArearRepository.findOne(id);
        Courier courier = courierRepository.findOne(couriersId);
        TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
        courier.setTakeTime(takeTime);
        fixedArea.getCouriers().add(courier);
    }


}
