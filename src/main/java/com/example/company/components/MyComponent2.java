package com.example.company.components;

import com.example.company.components.model.PojoData;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyComponent2 {

    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PojoData getMyData() {
        System.out.println("getMyData");
        return new PojoData("first data", "second data", "third data");
    }
}
