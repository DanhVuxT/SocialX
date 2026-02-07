package danhvu.example.identity_service.mapper;

import danhvu.example.identity_service.dto.request.CommentRequest;
import danhvu.example.identity_service.dto.response.CommentResponse;
import danhvu.example.identity_service.entity.Comment;
import danhvu.example.identity_service.entity.Post;
import danhvu.example.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "request.text", target = "text")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "post", target = "post")
    Comment toComment(CommentRequest request, User user, Post post);

    @Mapping(source = "user.username", target = "username")
    CommentResponse toCommentResponse(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateComment(CommentRequest request,@MappingTarget Comment comment);
}
