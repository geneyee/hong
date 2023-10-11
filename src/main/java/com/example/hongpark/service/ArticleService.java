package com.example.hongpark.service;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import com.example.hongpark.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {

        Article articleEntity = dto.toEntity();

        if (articleEntity.getId() != null) {
            // 이미 있는 데이터 변경을 막기 위해
            // id는 자동 생성되므로 create시 따로 입력 안함
            return null;
        }

        return articleRepository.save(articleEntity);
    }

    // 수정
    public Article update(Long id, ArticleForm dto) {

        // 1. 수정용 entity 생성
        Article entity = dto.toEntity();
        log.info("id : {}, article : {}", id, entity.toString());

        // 2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나 id가 다른경우)
        if (target == null || id != entity.getId()) {

            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}", id, entity.toString());

            return null;
        }

        // 4. 업데이트
        target.patch(entity);
        Article updated = articleRepository.save(target);

        return updated;

    }

    // 삭제
    public Article delete(Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }

        // 대상 삭제
        articleRepository.delete(target);

        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
       List<Article> articleList= dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
/*
        List<Article> articleList = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            ArticleForm dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
        }
*/
        // entity 묶음을 db로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

/*        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            articleRepository.save(article);
        }
        */

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결재 실패"));

        // 결과값 반환
        return articleList;
    }
}
