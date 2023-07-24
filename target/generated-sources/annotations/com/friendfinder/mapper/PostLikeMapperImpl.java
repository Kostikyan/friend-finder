package com.friendfinder.mapper;

import com.friendfinder.dto.postLikeDto.PostLikeDto;
import com.friendfinder.entity.PostLike;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T23:46:58+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class PostLikeMapperImpl implements PostLikeMapper {

    @Override
    public PostLike map(PostLikeDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        PostLike.PostLikeBuilder postLike = PostLike.builder();

        postLike.id( requestDto.getId() );
        postLike.likeStatus( requestDto.getLikeStatus() );
        postLike.post( requestDto.getPost() );
        postLike.user( requestDto.getUser() );

        return postLike.build();
    }
}
