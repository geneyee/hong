package com.example.hongpark.ioc;

import org.springframework.stereotype.Component;

@Component // 해당 클래스를 객체로 만들고, 이를 IoC 컨테이너에 등록하게 함
public class IngredientFactory2 {
    public Ingredient get(String menu) {
        switch (menu) {
            case "돈가스":
                return new Pork("한돈 등심");
            case "스테이크":
                return new Beef("한우 꽃등심");
            case "치킨":
                return new Chicken("국내산 닭");
            default:
                return null;
        }
    }
}
