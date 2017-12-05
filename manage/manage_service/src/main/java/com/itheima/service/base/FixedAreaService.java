package com.itheima.service.base;

import com.itheima.domain.base.FixedArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface FixedAreaService {
    public void  save(FixedArea fixedArea);

  Page<FixedArea> findPage(Pageable pageable);



    void beanCourier(Long id, Long couriersId, Long takeTimeId);
}
