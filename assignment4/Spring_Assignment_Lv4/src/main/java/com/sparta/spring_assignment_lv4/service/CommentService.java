package com.sparta.spring_assignment_lv4.service;

import com.sparta.spring_assignment_lv4.dto.CommentEditRequestDto;
import com.sparta.spring_assignment_lv4.dto.CommentPostRequestDto;
import com.sparta.spring_assignment_lv4.dto.CommentResponseDto;
import com.sparta.spring_assignment_lv4.entity.Comment;
import com.sparta.spring_assignment_lv4.entity.User;
import com.sparta.spring_assignment_lv4.enums.Role;
import com.sparta.spring_assignment_lv4.repository.CommentRepository;
import com.sparta.spring_assignment_lv4.utils.Exceptions.CommentDeletedException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.CommentNotFoundException;
import com.sparta.spring_assignment_lv4.utils.Exceptions.UnAuthorizedRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    @Transactional
    public CommentResponseDto commentOnArticle(User user, CommentPostRequestDto requestDto, Long articleId) {
        Comment comment = requestDto.toComment(user, articleId);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto commentOnComment(User user, CommentPostRequestDto requestDto, Long articleId, Long commentId) {
        Comment comment = requestDto.toComment(user, articleId, commentId);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
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
