package com.example.hongpark.controller;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.dto.CommentDto;
import com.example.hongpark.entity.Article;
import com.example.hongpark.repository.ArticleRepository;
import com.example.hongpark.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm articleForm) {
//        System.out.println(articleForm.toString()); -> 로깅으로 대체
       // ArticleForm - dto
        log.info(articleForm.toString());

        //1.dto -> entity로 변환
        Article article = articleForm.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        //2.repository에게 entity를 db에 저장하게 함
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴
//        Optional<Article> articleEntity = articleRepository.findById(id); -> title null 됨...
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentsService.comments(id);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }

    // 리스트 조회
    @GetMapping("/articles")
    public String index(Model model){

        // 1. 모든 article을 가져온다
       List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 가져온 article 묶음을 뷰로 전달한다
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 설정
        return "articles/index";
    }

    // 수정하기
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    // 수정하기
    @PostMapping("/articles/update")
    public String update(ArticleForm form){

        log.info(form.toString());

        // 1. dto를 entity로 변환
       Article articleEntity = form.toEntity();
       log.info(articleEntity.toString());

        // 2. 엔티티를 db에 저장한다.
        // 2-1. db에 기존 데이터를 가져온다.
       Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터의 값을 수정한다.
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

    // 삭제하기
    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");

        // 1. 삭제 대상을 가져온다.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info(articleEntity.toString());

        // 2. 그 대상을 삭제한다.
        if (articleEntity != null) {
            articleRepository.delete(articleEntity);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        // 3. 결과 페이지로 리다이렉트한다.
        return "redirect:/articles";
    }

}
