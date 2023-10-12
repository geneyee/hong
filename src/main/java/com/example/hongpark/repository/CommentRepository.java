package com.example.hongpark.repository;

import com.example.hongpark.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM comments WHERE article_id = :articleId", nativeQuery = true)
   List<Comments> findByArticleId(@Param("articleId") Long articleId);

    // 특정 닉네임의 모든 댓글 조회
//    @Query(value = "SELECT * FROM comments WHERE nickname = :nickname", nativeQuery = true)
    List<Comments> findByNickname(@Param("nickname")String nickname);
}
