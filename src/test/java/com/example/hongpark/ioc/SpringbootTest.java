package com.example.hongpark.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpringbootTest {

    @Autowired
    IngredientFactory2 ingredientFactory2;
    // @Component를 사용해서 IngredientFactory를 IoC에 등록하고
    // @Autowired로 IoC 컨테이너가 객체를 DI하게 함

    @Autowired
    Chef chef;

    @Test
    void 돈가스_요리하기() {
        // 준비
//        Chef chef = new Chef();
        String menu = "돈가스";

        // 수행
        String food = chef.cookPork(menu);

        // 예상
        String expected = "한돈 등심으로 만든 돈가스";

        // 검증
        assertEquals(expected.toString(), food.toString());
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기() {
        // 준비
//        Chef chef = new Chef();
        String menu = "스테이크";

        // 수행
        String food = chef.cookBeef(menu);

        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";

        // 검증
        assertEquals(expected.toString(), food.toString());
        System.out.println(food);
    }

    @Test
    void 크리스피_치킨_요리하기() {
        // 준비
//        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef_DI chef = new Chef_DI(ingredientFactory2);
        String menu = "치킨";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "국내산 닭으로 만든 치킨";

        // 검증
        assertEquals(expected.toString(), food.toString());
        System.out.println(food);
    }


}
