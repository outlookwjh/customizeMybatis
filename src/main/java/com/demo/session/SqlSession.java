package com.demo.session;

public interface SqlSession {

    <T> T getMapper(Class<T> daoInterfaceClass);

    void closed();

}
