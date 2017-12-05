package com.itheima.dao.base;

import com.itheima.domain.base.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier,Long>,JpaSpecificationExecutor{
    @Modifying
    @Query("update Courier set deltag = 1 where id = ?")
    public void updateDelete(long id);
    @Modifying
    @Query("update Courier set deltag ='' where id = ?")
    public void updaterestor(long id);

    public List<Courier> findByDeltagIsNull();
}
