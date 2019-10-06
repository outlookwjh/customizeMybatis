package com.demo.session.defaultImpl;

import com.demo.cfg.Configuration;
import com.demo.proxy.MapperProxy;
import com.demo.session.SqlSession;
import com.demo.util.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Connection conn;

    public DefaultSqlSession(Configuration configuration){

        this.configuration = configuration;
        conn = DataSourceUtil.getConnection(configuration);
    }


    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T)Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},
                new MapperProxy(configuration,conn));
    }

    @Override
    public void closed() {
        if (null != conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
