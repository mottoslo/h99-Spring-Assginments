package com.sparta.spring_assignment_lv3.service;

import com.sparta.spring_assignment_lv3.dto.CommentEditRequestDto;
import com.sparta.spring_assignment_lv3.dto.CommentPostRequestDto;
import com.sparta.spring_assignment_lv3.dto.CommentResponseDto;
import com.sparta.spring_assignment_lv3.dto.DeleteResponseDto;
import com.sparta.spring_assignment_lv3.entity.Article;
import com.sparta.spring_assignment_lv3.entity.Comment;
import com.sparta.spring_assignment_lv3.entity.Users;
import com.sparta.spring_assignment_lv3.jwt.JwtUtil;
import com.sparta.spring_assignment_lv3.repository.ArticleRepository;
import com.sparta.spring_assignment_lv3.repository.CommentRepository;
import com.sparta.spring_assignment_lv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;
    public CommentResponseDto postComment(CommentPostRequestDto requestDto, HttpServletRequest requestToken){
        Users user = getUser(requestToken);
        Article article = getArticle(requestDto.getPostId());
        Comment comment = new Comment(requestDto, user, article);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    public CommentResponseDto editComment(
            Long id,
            CommentEditRequestDto requestDto,
            HttpServletRequest requestToken
    ){
        Users user = getUser(requestToken);
        Comment comment = getComment(id);
        if(comment.getUser().getUserId().equals(user.getUserId())){
            comment.update(requestDto);
        }

        return new CommentResponseDto(comment);
    }

    public DeleteResponseDto deleteComment(
            Long id,
            HttpServletRequest requestToken
    ){
        Users user = getUser(requestToken);
        Comment comment = getComment(id);
        if(comment.getUser().getUserId().equals(user.getUserId())){
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
                () -> new NullPointerException("Article Not found")
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
                () -> new IllegalArgumentException("등록된 유저가 없습니다.")
        );
    }

    private Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 댓글을 찾을 수 없습니다.")
        );
    }
}
