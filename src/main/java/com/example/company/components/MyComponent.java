package com.example.company.components;

import com.example.company.components.model.PojoData;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Configuration
public class MyComponent {

    @Bean
    public PojoData getMyData() {
        System.out.println("getMyData");
        return new PojoData("first data", "second data", "third data");
    }

    @Bean
    public String getCarString() {
        System.out.println("getCarString");
        return "Tesla";
    }
}
