package com.lzhlyle.spring.demo.java.bean;

import java.beans.PropertyEditorSupport;

public class MyStringToIntegerEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setValue(Integer.valueOf(text));
    }
}
