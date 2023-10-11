package com.example.hongpark.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@AllArgsConstructor
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
