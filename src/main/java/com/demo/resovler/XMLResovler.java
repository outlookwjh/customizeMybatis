package com.demo.resovler;

import com.demo.annotation.Select;
import com.demo.cfg.Configuration;
import com.demo.cfg.Mapper;
import com.demo.io.Resources;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据传入的文件位置解析XML,并输出config文件
 */
public class XMLResovler {

    public static Configuration resovleXML(InputStream path) throws DocumentException, ClassNotFoundException {

        Configuration cfg = new Configuration();

        SAXReader reader = new SAXReader();

        Document doc = reader.read(path);

        Element root = doc.getRootElement();

        List<Element> propertys = root.selectNodes("//property");

        for (Element property : propertys) {
            String name = property.attributeValue("name");
            if ("driver".equals(name)){
                cfg.setDriver(property.attributeValue("value"));
            }else if ("url".equals(name)){
                cfg.setUrl(property.attributeValue("value"));
            }else if ("username".equals(name)){
                cfg.setUsername(property.attributeValue("value"));
            }else if ("password".equals(name)){
                cfg.setPassword(property.attributeValue("value"));
            }
        }

        List<Element> list = root.selectNodes("//mappers/mapper");
        for (Element element : list) {
            Attribute attribute = element.attribute("resource");
            if (null !=attribute){
                System.out.println("使用xml配置");
                String mapperPath = attribute.getValue();
                Map<String, Mapper> xmlMapper = getXMLMapper(mapperPath);
                cfg.setMapper(xmlMapper);
            }else {
                System.out.println("使用annotation配置");
                String attributeValue = element.attributeValue("class");
                Map<String, Mapper> annotationMapper = getAnnotationMapper(attributeValue);
                cfg.setMapper(annotationMapper);
            }
        }
        return cfg;
    }

    private static Map<String,Mapper> getXMLMapper(String mapperPath) throws DocumentException {
        HashMap<String, Mapper> mappers = new HashMap<>();
        InputStream in=null;
        try {
             in = Resources.getResourceAsStream(mapperPath);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            String namespace = root.attributeValue("namespace");
            List<Element> list = root.selectNodes("//select");
            for (Element element : list) {
                String resultType = element.attributeValue("resultType");
                String id = element.attributeValue("id");
                String sqltext = element.getText();
                String key = namespace+"."+id;
                Mapper mapper = new Mapper();
                mapper.setResultType(resultType);
                mapper.setSql(sqltext);
                mappers.put(key,mapper);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != in){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return mappers;
    }

    private static Map<String,Mapper> getAnnotationMapper(String mapperPath) throws ClassNotFoundException {

        Map<String,Mapper> mappers = new HashMap<>();

        Class<?> aClass = Class.forName(mapperPath);

        Method[] methods = aClass.getDeclaredMethods();

        for (Method method : methods) {
            boolean select = method.isAnnotationPresent(Select.class);
            if (select){
                Mapper mapper = new Mapper();
                Select annotation = method.getAnnotation(Select.class);
                String sqltext = annotation.value();
                Type genericReturnType = method.getGenericReturnType();
                if (genericReturnType instanceof ParameterizedType){
                    ParameterizedType ptype = (ParameterizedType)genericReturnType;
                    Type[] actualTypeArguments = ptype.getActualTypeArguments();
                    Class domian = (Class)actualTypeArguments[0];
                    String resultType = domian.getName();
                    mapper.setResultType(resultType);
                    mapper.setSql(sqltext);
                }
                String classname = method.getDeclaringClass().getName();
                String methodN = method.getName();
                String key = classname+"."+methodN;
                mappers.put(key,mapper);
            }
        }

        return  mappers;
    }

}
