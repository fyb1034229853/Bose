package com.itheima.service.base;

import com.itheima.domain.base.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourierService {
    public void  save(Courier courier);
    public Page<Courier> findPage(Pageable pageable);
    public Page<Courier> findPage(Specification<Courier> specification,Pageable pageable);
    public void  updaedelete(String sid);
    public void  updaerestor(String sid);

    List<Courier> findAll();
}
