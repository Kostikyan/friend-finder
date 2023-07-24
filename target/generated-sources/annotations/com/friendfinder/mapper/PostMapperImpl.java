package com.friendfinder.mapper;

import com.friendfinder.dto.postDto.PostRequestDto;
import com.friendfinder.dto.postDto.PostResponseDto;
import com.friendfinder.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T23:46:58+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post map(PostRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.description( requestDto.getDescription() );
        post.imgName( requestDto.getImgName() );
        post.musicFileName( requestDto.getMusicFileName() );
        post.postDatetime( requestDto.getPostDatetime() );
        post.user( requestDto.getUser() );

        return post.build();
    }

    @Override
    public List<PostResponseDto> mapResp(List<Post> post) {
        if ( post == null ) {
            return null;
        }

        List<PostResponseDto> list = new ArrayList<PostResponseDto>( post.size() );
        for ( Post post1 : post ) {
            list.add( postToPostResponseDto( post1 ) );
        }

        return list;
    }

    protected PostResponseDto postToPostResponseDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponseDto.PostResponseDtoBuilder postResponseDto = PostResponseDto.builder();

        postResponseDto.id( post.getId() );
        postResponseDto.description( post.getDescription() );
        postResponseDto.imgName( post.getImgName() );
        postResponseDto.musicFileName( post.getMusicFileName() );
        postResponseDto.likeCount( post.getLikeCount() );
        postResponseDto.dislikeCount( post.getDislikeCount() );
        postResponseDto.postDatetime( post.getPostDatetime() );
        postResponseDto.user( post.getUser() );

        return postResponseDto.build();
    }
}
