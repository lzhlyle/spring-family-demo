package com.lzhlyle.spring.demo.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        System.out.println(beanFactory.getParentBeanFactory()); // null

        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
//        System.out.println(beanFactory.getParentBeanFactory()); // object

        displayContainsLocalBean(applicationContext, "user"); // false
        displayContainsLocalBean(beanFactory, "user"); // false
        displayContainsLocalBean(parentBeanFactory, "user"); // true

        System.out.println("=============");

        displayContainsBean(applicationContext, "user"); // false
        displayContainsBean(beanFactory, "user"); // true
        displayContainsBean(parentBeanFactory, "user"); // true
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory [%s] 是否包含 Bean [name: %s]: %s\n", beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    // 类似双亲委派，先让上级找
    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = (HierarchicalBeanFactory) parentBeanFactory;
            if (containsBean(parentHierarchicalBeanFactory, beanName)) return true;
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory [%s] 是否包含 Local Bean [name: %s]: %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path = "META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(path);
        return beanFactory;
    }
}
