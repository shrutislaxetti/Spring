package com.bridgelabz.springsexamples;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TestDepencencyUsingXml {
	public static void main(String[] args) {
	Resource r=new ClassPathResource("Dependency.xml");  
        BeanFactory factory=new XmlBeanFactory(r);  
          
      Employee s=(Employee)factory.getBean("e");  
        s.show(); 
        System.out.println(s.hashCode());
        s.setName("simran");
        s.show();
        
        Employee s1=(Employee)factory.getBean("e1");
       	s1.show();
       	System.out.println(s1.hashCode());
       	s1.display();
	}
}
