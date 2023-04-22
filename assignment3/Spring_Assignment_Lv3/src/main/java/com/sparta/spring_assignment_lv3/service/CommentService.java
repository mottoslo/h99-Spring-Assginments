package com.sparta.spring_assignment_lv3.service;

import com.sparta.spring_assignment_lv3.dto.CommentEditRequestDto;
import com.sparta.spring_assignment_lv3.dto.CommentPostRequestDto;
import com.sparta.spring_assignment_lv3.dto.CommentResponseDto;
import com.sparta.spring_assignment_lv3.dto.DeleteResponseDto;
import com.sparta.spring_assignment_lv3.entity.Article;
import com.sparta.spring_assignment_lv3.entity.Comment;
import com.sparta.spring_assignment_lv3.entity.Users;
import com.sparta.spring_assignment_lv3.enums.userRole;
import com.sparta.spring_assignment_lv3.utils.jwt.JwtUtil;
import com.sparta.spring_assignment_lv3.repository.ArticleRepository;
import com.sparta.spring_assignment_lv3.repository.CommentRepository;
import com.sparta.spring_assignment_lv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public CommentResponseDto postComment(CommentPostRequestDto requestDto, HttpServletRequest requestToken){
        Users user = getUser(requestToken);
        Article article = getArticle(requestDto.getPostId());
        Comment comment = new Comment(requestDto, user, article);
        commentRepository.save(comment);
        article.addComment(comment);    // article에 Comment추가해주고, Comment에도 Article 추가
        user.addComment(comment);

        return new CommentResponseDto(comment);
    }
    @Transactional
    public CommentResponseDto editComment(
            Long id,
            CommentEditRequestDto requestDto,
            HttpServletRequest requestToken
    ){
        Comment comment = getComment(id);
        if(checkAuth(comment, requestToken)){
            comment.update(requestDto);
        }
        return new CommentResponseDto(comment);
    }

    @Transactional
    public DeleteResponseDto deleteComment(
            Long id,
            HttpServletRequest requestToken
    ){
        Comment comment = getComment(id);
        if(checkAuth(comment, requestToken)){
            commentRepository.deleteById(id);
            return new DeleteResponseDto(true);
        }
        else{
            return new DeleteResponseDto(false);
        }

    }

    ///내부메소드들

    private Article getArticle(Long postId) {
        return articleRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시글을 찾을 수 없습니다.")
        );
    }

    private String getUsername(HttpServletRequest request){ // 토큰검사하고 Username 반환
        String token = jwtUtil.resolveToken(request);
        if(jwtUtil.validateToken(token)){
            return jwtUtil.getUserInfoFromToken(token).getSubject();
        }
        else {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }

    private Users getUser(HttpServletRequest request){
        return userRepository.findByUserId(getUsername(request)).orElseThrow(
                () -> new NullPointerException("유저를 찾을 수 없습니다.")
        );
    }

    private Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다.")
        );
    }

    private boolean checkAuth(Comment comment, HttpServletRequest requestToken){
        Users user = getUser(requestToken);
        if (user.getRole() == userRole.ADMIN){
            return true;
        }
        else return user.getUserId().equals(comment.getUser().getUserId());
    }
}
