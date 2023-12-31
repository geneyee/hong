package com.example.hongpark.ioc;

public class IngredientFactory {

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
