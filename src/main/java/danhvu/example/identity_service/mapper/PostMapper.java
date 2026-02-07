package danhvu.example.identity_service.mapper;

import danhvu.example.identity_service.dto.request.PostRequest;
import danhvu.example.identity_service.dto.response.PostResponse;
import danhvu.example.identity_service.entity.Post;
import danhvu.example.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CommentMapper.class})
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    Post toPost(PostRequest request, User user);

    PostResponse toPostResponse(Post post, long like, boolean isLiked);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updatePost(PostRequest request, @MappingTarget Post post);

    @Mapping(target = "username", source = "post.user.username")
    PostResponse toSimplePostResponse(Post post, long likeCount,long commentCount, boolean isLikedByMe);
}
