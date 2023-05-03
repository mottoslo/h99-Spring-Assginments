package com.sparta.spring_assignment_lv4.repository.QueryDSL;


import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spring_assignment_lv4.dto.CommentResponseDto;
import com.sparta.spring_assignment_lv4.dto.QCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.spring_assignment_lv4.entity.QComment.comment;
import static com.sparta.spring_assignment_lv4.entity.QCommentLikes.commentLikes;
@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentResponseDto> findByRootArticleIdWithUserLiked(Long rootArticleId, Long userId){
        return queryFactory
                .select(new QCommentResponseDto(
                        comment.id,
                        comment.content,
                        comment.user.userId,
                        comment.createdAt,
                        comment.modifiedAt,
                        comment.numLikes,
                        comment.isDeleted,
                        JPAExpressions
                                .selectFrom(commentLikes)
                                .where(commentLikes.commentId.eq(comment.id).and(commentLikes.userId.eq(userId)))
                                .exists()))
                .from(comment)
                .where(comment.rootCommentId.eq(rootArticleId))
                .fetch();
    }


}
