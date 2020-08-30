package com.lzhlyle.spring.demo.bean.factory.demo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public static void main(String[] args) {
//        Resource resource = new ClassPathResource("beanFactoryTest.xml");
//        BeanFactory bf = new XmlBeanFactory(resource);

        ApplicationContext bf = new ClassPathXmlApplicationContext("beanFactoryTest.xml");

        MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
}
