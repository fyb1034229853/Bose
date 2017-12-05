package com.itheima.service.base;

import com.itheima.domain.base.Standard;
import com.itheima.domain.base.SubArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubAreaService {
    public void save(SubArea subArea);

    Page<Standard> findPage(Pageable pageable);
}
