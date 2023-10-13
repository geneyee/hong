package com.example.hongpark.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class Conversion {

    private String name;
    private int price;
    private List<String> ingredients;

    public Conversion() {}

}



