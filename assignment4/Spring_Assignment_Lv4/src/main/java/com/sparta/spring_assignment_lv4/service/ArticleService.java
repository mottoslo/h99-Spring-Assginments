package com.sparta.spring_assignment_lv4.service;


import com.sparta.spring_assignment_lv4.dto.*;
import com.sparta.spring_assignment_lv4.entity.Article;
import com.sparta.spring_assignment_lv4.entity.ArticleLikes;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.enums.Role;
import com.sparta.spring_assignment_lv4.repository.ArticleLikesRepository;
import com.sparta.spring_assignment_lv4.repository.ArticleRepository;
import com.sparta.spring_assignment_lv4.repository.CommentRepository;
import com.sparta.spring_assignment_lv4.utils.Exceptions.ArticleDeletedException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.ArticleNotFoundException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.DuplicateLikeRequestException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.UnAuthorizedRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ArticleLikesRepository articleLikesRepository;

    @Transactional
    public ArticleDetailResponseDto postArticle(User user, ArticlePostRequestDto requestDto) {
        Article new_article = requestDto.toArticle(user);
        articleRepository.save(new_article);
        return new ArticleDetailResponseDto(new_article);
    }
    @Transactional
        public List<ArticleListResponseDto> getArticleList() {
            return articleRepository.findAllByIsDeletedFalse()
                    .stream()
                    .map(ArticleListResponseDto::new)
                    .collect(Collectors.toList());
    }

    @Transactional
    public ArticleDetailResponseDto getArticleById(Long articleId) {
    Article article = loadArticle(articleId);
    if (article.getIsDeleted()) throw new ArticleDeletedException("삭제된 게시글입니다");
    List<CommentResponseDto> comments =
            commentRepository.findByRootArticleId(articleId)
                    .stream()
                    .map(CommentResponseDto::new)
                    .collect(Collectors.toList());

    return new ArticleDetailResponseDto(article, comments);
    }

    @Transactional
    public ArticleDetailResponseDto editArticle(User user, Long articleId, ArticleEditRequestDto requestDto) {
        Article article = loadArticle(articleId);
        checkArticleModifyAuthorization(user,article);
        article.updateContent(requestDto.getContent());

        return new ArticleDetailResponseDto(article);
    }

    @Transactional
    public void deleteArticle(User user, Long articleId) {
        Article article = loadArticle(articleId);
        checkArticleModifyAuthorization(user, article);
        article.flagDeleted();
    }
    @Transactional
    public void likeArticle(Long userId, Long articleId) {
        Article article = loadArticle(articleId);
        if(articleLikesRepository.existsByArticleIdAndUserId(articleId, userId)){
            throw new DuplicateLikeRequestException("한 번만 추천할 수 있습니다");
        }
        ArticleLikes like = new ArticleLikes(articleId, userId);
        articleLikesRepository.save(like);
        article.addLike();
    }
    @Transactional
    public void likeCancelArticle(Long userId, Long articleId){
        Article article = loadArticle(articleId);
        if(articleLikesRepository.existsByArticleIdAndUserId(articleId,userId)){
            articleLikesRepository.deleteByArticleIdAndUserId(articleId, userId);
            article.cancelLike();
        }
    }


    ///////////////내부코드들//////////////////////////

    private Article loadArticle(Long articleId){
        return articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException("게시글을 찾을 수 없습니다")
        );
    }

    private void checkArticleModifyAuthorization(User user, Article article){
        if(!(user.getRole().equals(Role.ROLE_ADMIN) || articleOwnerValidation(user, article))){
            throw new UnAuthorizedRequestException("요청에 대한 권한이 없습니다");
        }
    }

    private boolean articleOwnerValidation(User user, Article article){
        return user.getId().equals(article.getUser().getId());
    }
}
