package com.example.hongpark.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "comments")
@Getter
@ToString
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

//    @Builder
    public Comments(Long id, Article article, String nickname, String body) {
        this.id = id;
        this.article = article;
        this.nickname = nickname;
        this.body = body;
    }
}
