package com.demo.session.defaultImpl;

import com.demo.cfg.Configuration;
import com.demo.session.SqlSession;
import com.demo.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {


    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
