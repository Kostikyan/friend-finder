package com.friendfinder.mapper;

import com.friendfinder.dto.postDto.PostRequestDto;
import com.friendfinder.dto.postDto.PostResponseDto;
import com.friendfinder.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post map(PostRequestDto requestDto);

    PostResponseDto map(Post post);
}
