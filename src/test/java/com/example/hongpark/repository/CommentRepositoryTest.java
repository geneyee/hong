package com.example.hongpark.repository;

import com.example.hongpark.entity.Article;
import com.example.hongpark.entity.Comments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1 : 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
           List<Comments> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L, "제목4", "내용4");
            Comments a = new Comments(1L, article, "댓글1", "댓글내용 4-1");
            Comments b = new Comments(2L, article, "댓글2", "댓글내용 4-2");
            Comments c = new Comments(3L, article, "댓글3", "댓글내용 4-3");
            List<Comments> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
        }

        /* Case 2 : 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comments> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(1L, "제목1", "내용1");
            List<Comments> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
        /* Case 3 : 9번 게시글의 모든 댓글 조회 */
        /* Case 4 : 999번 게시글의 모든 댓글 조회 */
        /* Case 5 : -1번 게시글의 모든 댓글 조회 */

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1 : "댓글2"의 모든 댓글 조회 */

        // 입력 데이터 준비
        String nickname = "댓글2";

        // 실제 수행
        List<Comments> comments = commentRepository.findByNickname(nickname);

        // 예상하기
        Comments a = new Comments(2L, new Article(4L, "제목4", "내용4"), "댓글2", "댓글내용 4-2");
        Comments b = new Comments(5L, new Article(5L, "제목5", "내용5"), "댓글2", "댓글내용 5-2");
        Comments c = new Comments(8L, new Article(6L, "제목6", "내용6"), "댓글2", "댓글내용 6-2");
        List<Comments> expected = Arrays.asList(a, b, c);

        // 빌더 왜 사용하는지 이유는 알겠음 구현에 대해서 더 공부..
//        commentRepository.save(Article.builder()
//                .id(2L)
//                .title("제목2")
//                .content("내용2"));

        // 검증
        assertEquals(expected.toString(), comments.toString(), "닉네임 댓글2의 모든 댓글을 조회");

        /* Case 2 : "댓글3"의 모든 댓글 조회 */
        /* Case 3 : null의 모든 댓글 조회 */
        /* Case 4 : ""(공백)의 모든 댓글 조회 */
        /* Case 5 : "%글"의 모든 댓글 조회 */
    }
}