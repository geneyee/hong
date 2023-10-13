package com.example.hongpark.ioc;

public class Chef_DI {

    private IngredientFactory2 ingredientFactory2;

    public Chef_DI(IngredientFactory2 ingredientFactory2) {
        this.ingredientFactory2 = ingredientFactory2;
    }

    public String cook(String menu) {
        // Ingredient : Pork와 Beef의 부모 클래스
        Ingredient ingredient = ingredientFactory2.get(menu);

        return ingredient.getName() + "으로 만든 " + menu;
    }
}
