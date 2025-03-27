package org.example.springbasictest.context;

import org.assertj.core.api.Assertions;
import org.example.springbasictest.util.Hello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

class ApplicationContextUseTest {

    @Test
    void registerBeanToApplicationContext() {
        StaticApplicationContext ac = new StaticApplicationContext();

        ac.registerSingleton("hello_bean", Hello.class);
        Hello helloBean = ac.getBean("hello_bean", Hello.class);
        Assertions.assertThat(helloBean).isNotEqualTo(null);
    }

    @Test
    void registerBeanByBeanDefinition() {
        StaticApplicationContext ac = new StaticApplicationContext();

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        ac.registerBeanDefinition("hello", helloDef);

        Hello hello = (Hello) ac.getBean("hello");

        Assertions.assertThat(hello.print()).isEqualTo("Hello Spring!");
    }

    @Test
    void xmlReaderGenericApplicationContext() {
        GenericApplicationContext ac = new GenericApplicationContext();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("classpath:static/genericApplicationContext.xml");

        ac.refresh();

        Hello hello = ac.getBean("hello", Hello.class);

        Assertions.assertThat(ac.getBean("hello")).isEqualTo(hello);
    }
}