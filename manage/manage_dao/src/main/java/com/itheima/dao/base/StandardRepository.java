package com.itheima.dao.base;

import com.itheima.domain.base.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StandardRepository extends JpaRepository<Standard,Long> {


}
