package com.lzhlyle.spring.demo.java.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

public class JavaBeanApplication {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    Class propertyType = propertyDescriptor.getPropertyType();
                    if ("age".equals(propertyType.getName())) {
                        // String -> Integer
                        propertyDescriptor.setPropertyEditorClass(MyStringToIntegerEditor.class);
                    }
                });
    }
}
