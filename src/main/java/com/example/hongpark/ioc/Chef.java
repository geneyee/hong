package com.example.hongpark.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Chef {

    // 셰프는 식재료 공장을 알고있음

    private IngredientFactory ingredientFactory;



    // 셰프가 식재로 공장과 협업하기 위한 DI
    // DI : 의존성 주입. 동작에 필요한 객체를 외부에서 받아옴

    public Chef() {
    }

    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }


    public String cookPork(String menu) {
        // 재료 준비
        Pork pork = new Pork("한돈 등심");

        // 요리 반환
        return pork.getName() + "으로 만든 " + menu;
    }

    public String cookBeef(String menu) {
        // 재료 준비
        Beef beef = new Beef("한우 꽃등심");


        // 요리 반환
        return beef.getName() + "으로 만든 " + menu;
    }

    public String cook(String menu) {
        // Ingredient : Pork와 Beef의 부모 클래스
        Ingredient ingredient = ingredientFactory.get(menu);

        return ingredient.getName() + "으로 만든 " + menu;
    }

}
