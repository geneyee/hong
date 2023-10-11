//package com.example.hongpark.v1.api;
//
//
//import com.example.hongpark.dto.ArticleForm;
//import com.example.hongpark.entity.Article;
//import com.example.hongpark.repository.ArticleRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//public class ArticleApiController_ex {
//
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    // GET
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article indexDetail(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto){
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    // PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@RequestBody ArticleForm dto, @PathVariable Long id) {
//
//        // 1. 수정용 entity 생성 (dto->entity)
//        Article article = dto.toEntity(); //dto는 클라이언트에서 수정해서 가지고온 data를 받은거 // 이걸 entity(article)로 변환
//        log.info("id : {}, article : {}", id, article.toString());
//
//        // 2. 대상 엔티티 조회 // 넘어온 url의 id로 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리(대상이 없거나 id가 다른경우)
//        if (target == null || id != article.getId()) {
//            // 400, 잘못된 요청 응답
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 4. 업데이트 및 정상 응답(200)
//        target.patch(article); // 수정할 때 title or content가 null일 경우 처리할 method
//        Article updated = articleRepository.save(target);
//
//        // Article updated = articleRepository.save(article);
//
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//
//        // 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 대상 삭제
//        articleRepository.delete(target);
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//}
//
