package com.demo.excutor;

import com.demo.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Excutor {

    public<E> List<E> selectList(Mapper mapper, Connection connection) throws Exception{
        PreparedStatement pstm = null;
        ResultSet resultSet = null;

        String sql = mapper.getSql();//select * from user
        String resultType = mapper.getResultType();//com.itheima.domain.User
        Class domainClass = Class.forName(resultType);

        List<E> list = new ArrayList<E>();

        try {
            pstm  = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while (resultSet.next()){

                E o = (E)domainClass.newInstance();

                ResultSetMetaData metaData = resultSet.getMetaData();

                int count = metaData.getColumnCount();

                for (int i = 1; i <= count; i++) {
                    //获取每列的名称，列名的序号是从1开始的
                    String columnName = metaData.getColumnName(i);
                    //根据得到列名，获取每列的值
                    Object columnValue = resultSet.getObject(columnName);
                    //给obj赋值：使用Java内省机制（借助PropertyDescriptor实现属性的封装）
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);//要求：实体类的属性和数据库表的列名保持一种
                    //获取它的写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列的值，给对象赋值
                    writeMethod.invoke(o,columnValue);
                }
                list.add(o);
            }
        }catch (Exception e){

        }finally {
            release(pstm,resultSet);
        }

        return list;
    }

    private void release(PreparedStatement pstm,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(pstm != null){
            try {
                pstm.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
