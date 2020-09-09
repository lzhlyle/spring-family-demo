package com.lzhlyle.spring.demo.bean.lifecycle;

import com.lzhlyle.spring.demo.bean.lifecycle.domain.UserHolder;
import com.lzhlyle.spring.demo.ioc.overview.domain.SuperUser;
import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] paths = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection-context.xml"};
        reader.loadBeanDefinitions(paths);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                User user = (User) bean;
                user.setId(888L);

                // user 对象不允许属性赋值（配置元信息 -> 属性值）
                return false;
            }
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                final MutablePropertyValues propertyValues;

                if (pvs instanceof MutablePropertyValues) {
                    propertyValues = (MutablePropertyValues) pvs;
                } else {
                    propertyValues = new MutablePropertyValues();
                }

                propertyValues.addPropertyValue("number", "1217");
                if (propertyValues.contains("description")) {
                    PropertyValue propertyValue = propertyValues.getPropertyValue("description"); // 不可变
                    propertyValues.removePropertyValue("description");
                    propertyValues.addPropertyValue("description", "from postProcessProperties");
                }
                return propertyValues;
            }
            return null;
        }
    }
}
