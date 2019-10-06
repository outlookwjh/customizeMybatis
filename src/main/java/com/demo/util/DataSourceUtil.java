package com.demo.util;

import com.demo.cfg.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtil {

    public static Connection getConnection(Configuration configuration) {
        try {
            Class.forName(configuration.getDriver());
            return DriverManager.getConnection(configuration.getUrl(),
                    configuration.getUsername(), configuration.getPassword());

        } catch (Exception e) {
            throw new RuntimeException("获取数据源失败");
        }

    }
}