package com.itheima.service.base.impl;

import com.itheima.dao.base.AreaRepository;
import com.itheima.domain.base.Area;
import com.itheima.service.base.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;
    @Override
    public void saveImport(List<Area> list) {
        areaRepository.save(list);
    }

    @Override
    public Page<Area> findPage(Pageable pageable) {
        return areaRepository.findAll(pageable);
    }

    @Override
    public List<Area> findAll() {

        return areaRepository.findAll();
    }

    @Override
    public List<Area> findByQ(String q) {

        return areaRepository.findByQ("%"+q+"%");
    }
}
