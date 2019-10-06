package com.demo.proxy;

import com.demo.cfg.Configuration;
import com.demo.cfg.Mapper;
import com.demo.excutor.Excutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class MapperProxy implements InvocationHandler{

    private Connection connection;
    private Configuration configuration;

    public MapperProxy(Configuration configuration, Connection connection){
        this.configuration = configuration;
        this.connection = connection;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String key = className+"."+methodName;
        Map<String, Mapper> mapperMap = configuration.getMapper();
        Mapper mapper = mapperMap.get(key);
        if (null == mapper){
            throw new RuntimeException("没有找到对应的方法");
        }
        return new Excutor().selectList(mapper, connection);
    }
}
