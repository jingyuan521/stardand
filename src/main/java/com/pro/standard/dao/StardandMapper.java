package com.pro.standard.dao;

import com.pro.standard.pojo.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StardandMapper extends JpaRepository<Standard, Long> {

    //Ôö¼Ó
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO standard.standard (std_num,zhname,sversion,skeys,release_date,impl_date) " +
            "VALUES (#{standard.std_num},#{standard.zhname},#{standard.sversion},#{standard.skeys},#{standard.releasedate},#{standard.impldate})",nativeQuery = true)
    public int addStard(Standard standard);

}
