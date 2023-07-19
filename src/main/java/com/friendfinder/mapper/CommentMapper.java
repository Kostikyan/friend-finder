package com.friendfinder.mapper;

import com.friendfinder.dto.commentDto.CommentRequestDto;
import com.friendfinder.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment map(CommentRequestDto requestDto);
}
