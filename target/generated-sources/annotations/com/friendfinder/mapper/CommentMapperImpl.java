package com.friendfinder.mapper;

import com.friendfinder.dto.commentDto.CommentRequestDto;
import com.friendfinder.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T23:46:58+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.user( requestDto.getUser() );
        comment.post( requestDto.getPost() );
        comment.datetime( requestDto.getDatetime() );
        comment.commentaryText( requestDto.getCommentaryText() );

        return comment.build();
    }
}
