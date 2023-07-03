package com.friendfinder.mapper;

import com.friendfinder.dto.postDto.PostRequestDto;
import com.friendfinder.dto.postDto.PostResponseDto;
import com.friendfinder.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post map(PostRequestDto requestDto);
    List<PostResponseDto> mapResp(List<Post> post);
}
