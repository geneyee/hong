package com.example.hongpark.dto;

import com.example.hongpark.entity.Comments;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {

    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    // service에서 entity를 dto로 변환할 때 사용
    // static -> class 메소드이기 때문에
    public static CommentDto createCommentDto(Comments entity) {
        return new CommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                entity.getNickname(),
                entity.getBody()
        );
    }
}
