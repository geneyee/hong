package com.example.hongpark.service;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스트된다
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void index() {

        // 예상
        Article a = new Article(1L, "제목1", "내용1");
        Article b = new Article(2L, "제목2", "내용2");
        Article c = new Article(3L, "제목3", "내용3");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        //예상
        Long id = 1L;
        Article expected = new Article(id, "제목1", "내용1");

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        //예상
        Long id = -1L;
        Article expected = null;

        //실제
        Article article = articleService.show(id);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력() {

        //예상
        String title = "테스트 코드";
        String content = "테스트 코드";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    void create_실패_id가_포함된_dto가_입력() {

        //예상
        String title = "테스트 코드";
        String content = "테스트 코드";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        //실제
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력 (){
        
        // 예상
        Long id = 3L;
        String title = "update";
        String content = "update";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);

        // 실제
        Article article = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
        
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {


    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id_dto_입력() {

    }
    @Test
    @Transactional
    void update_실패_id만_있는_dto_입력() {

    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {

    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력() {

    }
}