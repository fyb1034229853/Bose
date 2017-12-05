package com.itheima.service.base;

import com.itheima.domain.base.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StandardService {
    public void  save(Standard standard);

    Page<Standard> findPage(Pageable page);
    List<Standard> findAll();

}
