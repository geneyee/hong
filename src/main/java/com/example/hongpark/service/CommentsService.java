package com.example.hongpark.service;

import com.example.hongpark.dto.CommentDto;
import com.example.hongpark.entity.Article;
import com.example.hongpark.entity.Comments;
import com.example.hongpark.repository.ArticleRepository;
import com.example.hongpark.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentsService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {

/*        // 댓글 목록 조회
        List<Comments> entities = commentRepository.findByArticleId(articleId);

        // 엔티티를 dto로 변환(List<CommentDto>로 반환해야 하기 때문에)
        ArrayList<CommentDto> dtos = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {
            Comments entity = entities.get(i);
            CommentDto dto = CommentDto.createCommentDto(entity);
            dtos.add(dto);
        }

        // 반환
        return dtos;*/

        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        log.info("=================================="+article.toString());

        // 댓글 entity 생성
        Comments comment = Comments.createComment(dto, article);
        log.info("=================================="+comment.toString());

        // 댓글 entity를 db로 저장
        Comments created = commentRepository.save(comment);

        // entity를 dto로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 수정할 댓글 조회 및 예외 발생
        Comments target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // db로 갱싱
        Comments updated = commentRepository.save(target);

        // 댓글 entity -> dto 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
       Comments target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

       commentRepository.delete(target);

       return CommentDto.createCommentDto(target);
    }
}
