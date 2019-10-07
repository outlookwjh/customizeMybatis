package com.testinstance.dao;

import com.demo.annotation.Select;
import com.testinstance.domain.vo.Domian;

import java.util.List;

public interface IareaDao {

    @Select(value = "select * from  t_area")
    List<Domian> findALL();
}
