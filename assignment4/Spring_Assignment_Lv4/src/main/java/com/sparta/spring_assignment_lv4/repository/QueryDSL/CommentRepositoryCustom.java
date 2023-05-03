package com.sparta.spring_assignment_lv4.repository.QueryDSL;

import com.sparta.spring_assignment_lv4.dto.CommentResponseDto;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentResponseDto> findByRootArticleIdWithUserLiked(Long rootArticleId, Long UserId);
}
