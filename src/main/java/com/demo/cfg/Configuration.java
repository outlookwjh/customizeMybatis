package com.demo.cfg;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    public Configuration(){
        this.mapper = new HashMap<String, Mapper>();
    }

    private String username;

    private String password;

    private String driver;

    private String url;

    private Map<String,Mapper> mapper;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Mapper> getMapper() {
        return mapper;
    }

    public void setMapper(Map<String, Mapper> mapper) {
        this.mapper.putAll( mapper);
    }
}
