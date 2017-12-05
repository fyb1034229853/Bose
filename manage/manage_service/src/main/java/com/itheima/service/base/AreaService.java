package com.itheima.service.base;

import com.itheima.domain.base.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaService {
    public void saveImport(List<Area> list);
    public Page<Area> findPage(Pageable pageable);
    List<Area> findAll();

    List<Area> findByQ(String q);
}
