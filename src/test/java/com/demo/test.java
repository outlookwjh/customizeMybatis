package com.demo;

import com.demo.dao.IareaDao;
import com.demo.domain.vo.Domian;
import com.demo.io.Resources;
import com.demo.session.SqlSession;
import com.demo.session.SqlSessionFactory;
import com.demo.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class test {

    @Test
    public void customize(){

        InputStream resource = Resources.getResourceAsStream("SqlConfig.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory factory = builder.build(resource);

        SqlSession session = factory.openSession();

        IareaDao mapper = session.getMapper(IareaDao.class);

        List<Domian> all = mapper.findALL();

        all.stream().forEach(domain-> System.out.println(domain.getC_POSTCODE()));



    }
}
