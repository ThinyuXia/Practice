package com.xiaxinyu.spring.ioc.context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.lang.reflect.*;

public class ClassPathXmlApplicationContext implements ApplicationContext{
    private Map iocContainer = new HashMap<>();

    public ClassPathXmlApplicationContext(){
        try{
            String filePath = this.getClass().getResource("/myApplicationContext.xml").getPath();
            filePath = new URLDecoder().decode(filePath,"utf-8");
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filePath));

            List<Node> beans = document.getRootElement().selectNodes("bean");
            for(Node node : beans){
                Element element = (Element) node;
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Class c = Class.forName(className);
                Object obj = c.newInstance();

                List<Node> properties = element.selectNodes("property");
                for(Node property : properties){
                    Element element1 = (Element) property;
                    String propName = ((Element) property).attributeValue("name");
                    String propValue = ((Element) property).attributeValue("value");

                    String setMethodName = "set" + propName.substring(0,1).toUpperCase() + propName.substring(1);
                    System.out.println("准备执行" + setMethodName + "方法注入数据");
                    Method setMethod = c.getMethod(setMethodName,String.class);
                    setMethod.invoke(obj,propValue);

                }

                iocContainer.put(id,obj);
            }
            System.out.println("IoC容器初始化完毕");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanId) {
        return iocContainer.get(beanId);
    }
}
