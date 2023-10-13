package com.example.hongpark.entity;

import com.example.hongpark.dto.CommentDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    // 생성 method - static - class method
    public static Comments createComment(CommentDto dto, Article article) {

        // 예외
        // id는 입력하는 값이 아니기 때문에 null이 아니면 문제생김
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID가 없어야 합니다.");

        // dto에서 넘어온 articleId(게시글번호)와 entity article id가 다르면 문제
        if (dto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 ID가 잘못되었습니다.");
        }
        // 엔티티 생성 및 반환
        return new Comments(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        }

        // 객체 갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }

        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}
