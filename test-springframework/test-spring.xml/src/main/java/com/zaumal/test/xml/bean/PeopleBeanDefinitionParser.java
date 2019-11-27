package com.zaumal.test.xml.bean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class PeopleBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class getBeanClass(Element element) {  
        return People.class;  
    }  
	
	@Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {  
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");  
        String age = element.getAttribute("age");  
        
        if (StringUtils.hasText(id)) {  
        	builder.addPropertyValue("id", id);  
        }  
        if (StringUtils.hasText(name)) {  
        	builder.addPropertyValue("name", name);  
        }  
        if (StringUtils.hasText(age)) {  
        	builder.addPropertyValue("age", Integer.valueOf(age));  
        }  
    }  
}
