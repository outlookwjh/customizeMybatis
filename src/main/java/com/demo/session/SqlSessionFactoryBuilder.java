package com.demo.session;

import com.demo.cfg.Configuration;
import com.demo.resovler.XMLResovler;
import com.demo.session.defaultImpl.DefaultSqlSessionFactory;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream path)  {
        Configuration configuration=null;
        try {
             configuration = XMLResovler.resovleXML(path);
        } catch (Exception e) {
            throw new RuntimeException("解析xml失败");
        }

        return new DefaultSqlSessionFactory(configuration);
    }



}
