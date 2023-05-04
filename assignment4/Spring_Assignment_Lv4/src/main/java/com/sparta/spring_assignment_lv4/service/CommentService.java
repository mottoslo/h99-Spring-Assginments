package com.sparta.spring_assignment_lv4.service;

import com.sparta.spring_assignment_lv4.dto.CommentEditRequestDto;
import com.sparta.spring_assignment_lv4.dto.CommentPostRequestDto;
import com.sparta.spring_assignment_lv4.dto.CommentResponseDto;
import com.sparta.spring_assignment_lv4.entity.Comment;
import com.sparta.spring_assignment_lv4.entity.CommentLikes;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.enums.Role;
import com.sparta.spring_assignment_lv4.repository.CommentLikesRepository;
import com.sparta.spring_assignment_lv4.repository.CommentRepository;
import com.sparta.spring_assignment_lv4.utils.Exceptions.CommentNotFoundException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.DuplicateLikeRequestException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.UnAuthorizedRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    public List<CommentResponseDto> getCommentsOnArticle(Long articleId, User user) {

        List<Long> userLikesOnArticleComments =
                commentLikesRepository.findAllByArticleIdAndUserId(articleId,user.getId())
                        .stream()
                        .map(CommentLikes::getCommentId)
                        .toList();

        return commentRepository.findByRootArticleIdAndRootCommentIdIsNull(articleId)
                .stream()
                .map(c -> new CommentResponseDto(c, userLikesOnArticleComments))
                .collect(Collectors.toList()); // 원시코멘트만 로드
    }

    @Transactional
    public List<CommentResponseDto> getCommentsOnComment(User user, Long articleId,Long rootCommentId ){
        List<Long> userLikesOnArticleComments =
                commentLikesRepository.findAllByArticleIdAndUserId(articleId, user.getId())
                        .stream()
                        .map(CommentLikes::getCommentId)
                        .toList();
        return commentRepository.findByRootCommentId(rootCommentId)
                .stream()
                .map(c -> new CommentResponseDto(c, userLikesOnArticleComments))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto commentOnArticle(User user, CommentPostRequestDto requestDto, Long articleId) {
        Comment comment = requestDto.toComment(user, articleId);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto commentOnComment(User user, CommentPostRequestDto requestDto, Long articleId, Long commentId) {
        Comment rootComment = loadComment(commentId);
        Comment childComment = requestDto.toComment(user, articleId, commentId);
        commentRepository.save(childComment);
        rootComment.addNumComment();
        return new CommentResponseDto(childComment);
    }

    @Transactional
    public CommentResponseDto editComment(User user, Long commentId, CommentEditRequestDto requestDto) {
        Comment comment = loadComment(commentId);
        checkCommentModifyAuthorization(user, comment);
        comment.updateContent(requestDto.getContent());
        return new CommentResponseDto(comment);
    }
    @Transactional
    public void deleteComment(User user, Long commentId) {
        Comment comment = loadComment(commentId);
        checkCommentModifyAuthorization(user, comment);
        comment.flagDeleted();
    }
    @Transactional
    public void likeComment(Long userId, Long commentId) {
    Comment comment = loadComment(commentId);
        if(commentLikesRepository.existsByCommentIdAndUserId(commentId, userId)){
        throw new DuplicateLikeRequestException("한 번만 추천할 수 있습니다");
    }
    CommentLikes like = new CommentLikes(comment.getRootArticleId(), commentId, userId);
        commentLikesRepository.save(like);
        comment.addLike();
}

    @Transactional
    public void likeCancelComment(Long userId, Long commentId){
        Comment comment = loadComment(commentId);
        if(commentLikesRepository.existsByCommentIdAndUserId(commentId, userId)){
            commentLikesRepository.deleteByCommentIdAndUserId(commentId, userId);
            comment.cancelLike();
        }
    }


////////////////////////////////////내부 검증 등////////////////////////////////////////

    private Comment loadComment(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                ()-> new CommentNotFoundException("댓글을 찾을 수 없습니다")
        );
}

    private void checkCommentModifyAuthorization(User user, Comment comment){
        if(!(user.getRole().equals(Role.ROLE_ADMIN) || commentOwnerValidation(user, comment))){
            throw new UnAuthorizedRequestException("요청에 대한 권한이 없습니다");
        }
    }

    private boolean commentOwnerValidation(User user, Comment comment){
        return user.getId().equals(comment.getUser().getId());
    }
}
