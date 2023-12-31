package com.example.hongpark.api;

import com.example.hongpark.annotation.RunningTime;
import com.example.hongpark.dto.CommentDto;
import com.example.hongpark.entity.Comments;
import com.example.hongpark.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentsService commentsService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
//    public List<Comments> comments(@PathVariable Long articleId) {
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentsService.comments(articleId);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {

        // 서비스에게 위임
        CommentDto createdDto = commentsService.create(articleId, dto);
        // 결과 응답

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {

        CommentDto updatedDto = commentsService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 댓글 삭제
    @RunningTime
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {

       CommentDto deleted = commentsService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}
