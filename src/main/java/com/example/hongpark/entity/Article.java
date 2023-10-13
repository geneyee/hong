package com.example.hongpark.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@ToString
@NoArgsConstructor // 디폴트 생성자 추가
@Entity // DB가 해당 객체를 인식 가능
public class Article {

    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동생성
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

//    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
//    private List<Comments> comments;

//    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
//    private List<Comments> comment = new ArrayList<>();

    //@Builder
    public Article(Long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // 수정시 title이나 content의 값이 null일 경우 - 기존 작성값(update 전 data) 보여주는 메소드
    public void patch(Article article) { // article
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }

//    public Article(){}

//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
