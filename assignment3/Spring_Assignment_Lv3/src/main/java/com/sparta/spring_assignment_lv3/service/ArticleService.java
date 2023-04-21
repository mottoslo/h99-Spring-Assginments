package com.sparta.spring_assignment_lv3.service;


import com.sparta.spring_assignment_lv3.dto.ArticleRequestDto;
import com.sparta.spring_assignment_lv3.dto.ArticleResponseDto;
import com.sparta.spring_assignment_lv3.dto.DeleteResponseDto;
import com.sparta.spring_assignment_lv3.entity.Article;
import com.sparta.spring_assignment_lv3.entity.Users;
import com.sparta.spring_assignment_lv3.jwt.JwtUtil;
import com.sparta.spring_assignment_lv3.repository.ArticleRepository;
import com.sparta.spring_assignment_lv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public Article findById(Long id){
        return articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> getAllArticle() {
        return articleRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
    } // 내림차순으로 정렬하여 ResponseDto로 반환

    @Transactional
    public ArticleResponseDto postArticle(ArticleRequestDto requestDto, HttpServletRequest requestToken) {
        Users user = getUser(requestToken);
        Article article = new Article(requestDto, user);
        articleRepository.save(article);
        user.addArticle(article);
        return new ArticleResponseDto(article);
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto getArticleById(Long id) {
        Article found = findById(id);
        return new ArticleResponseDto(found);
    }
    @Transactional
    public ArticleResponseDto editArticleById(Long id, ArticleRequestDto requestDto, HttpServletRequest requestToken) {
        Article found = findById(id);
        if (found.getUser().getUserId().equals(getUsername(requestToken))){
            found.update(requestDto);
        }
        return new ArticleResponseDto(found);
    }
    @Transactional
    public DeleteResponseDto deleteArticleById(Long id, HttpServletRequest requestToken) {
        Article found = findById(id);
        if(found.getUser().getUserId().equals(getUsername(requestToken))) {
            articleRepository.deleteById(id);
            return new DeleteResponseDto(true);
        }
        return new DeleteResponseDto(false);
    }

    ////내부 함수들
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
}
