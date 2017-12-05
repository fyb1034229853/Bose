package com.itheima.dao.base;

import com.itheima.domain.base.TakeTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeTimeRepository extends JpaRepository<TakeTime,Long> {
}
