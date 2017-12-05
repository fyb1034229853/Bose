package com.itheima.service.base.impl

import com.itheima.dao.base.SubAreaRepository
import com.itheima.domain.base.Standard
import com.itheima.domain.base.SubArea
import com.itheima.service.base.SubAreaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SubAreaServiceImpl  implements SubAreaService {
    @Autowired
    private SubAreaRepository subAreaRepository;
    @Override
    void save(SubArea subArea) {
        subAreaRepository.save(subArea);
    }

    @Override
    Page<Standard> findPage(Pageable pageable) {
        return subAreaRepository.findAll(pageable);
    }
}
