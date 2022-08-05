package com.example.company.components.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PojoData {
    private String data1;
    private String data2;
    private String data3;

//    @Override
//    public String toString() {
//        return "PojoData{" +
//                "data1='" + data1 + '\'' +
//                ", data2='" + data2 + '\'' +
//                ", data3='" + data3 + '\'' +
//                '}';
//    }
}
