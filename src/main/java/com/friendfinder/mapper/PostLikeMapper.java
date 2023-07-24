package com.friendfinder.mapper;

import com.friendfinder.dto.postLikeDto.PostLikeDto;
import com.friendfinder.entity.PostLike;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostLikeMapper {

    PostLike map(PostLikeDto requestDto);
}
