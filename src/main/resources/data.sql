insert into article(id, title, content) values (1, '제목1', '내용1');
insert into article(id, title, content) values (2, '제목2', '내용2');
insert into article(id, title, content) values (3, '제목3', '내용3');

-- article 더미 데이터
insert into article(id, title, content) values (4, '제목4', '내용4');
insert into article(id, title, content) values (5, '제목5', '내용5');
insert into article(id, title, content) values (6, '제목6', '내용6');

-- comment 더미 데이터
---- 4번 게시글의 댓글
insert into comments(id, article_id, nickname, body) values (1, 4, '댓글1', '댓글내용 4-1');
insert into comments(id, article_id, nickname, body) values (2, 4, '댓글2', '댓글내용 4-2');
insert into comments(id, article_id, nickname, body) values (3, 4, '댓글3', '댓글내용 4-3');

---- 5번 게시글의 댓글
insert into comments(id, article_id, nickname, body) values (4, 5, '댓글1', '댓글내용 5-1');
insert into comments(id, article_id, nickname, body) values (5, 5, '댓글2', '댓글내용 5-2');
insert into comments(id, article_id, nickname, body) values (6, 5, '댓글3', '댓글내용 5-3');

---- 6번 게시글의 댓글
insert into comments(id, article_id, nickname, body) values (7, 6, '댓글1', '댓글내용 6-1');
insert into comments(id, article_id, nickname, body) values (8, 6, '댓글2', '댓글내용 6-2');
insert into comments(id, article_id, nickname, body) values (9, 6, '댓글3', '댓글내용 6-3');