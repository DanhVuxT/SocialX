package danhvu.example.identity_service.service;

import danhvu.example.identity_service.dto.request.PostRequest;
import danhvu.example.identity_service.dto.response.CommentResponse;
import danhvu.example.identity_service.dto.response.PostResponse;
import danhvu.example.identity_service.entity.Post;
import danhvu.example.identity_service.entity.User;
import danhvu.example.identity_service.enums.ErrorCode;
import danhvu.example.identity_service.exception.AppException;
import danhvu.example.identity_service.mapper.PostMapper;
import danhvu.example.identity_service.repository.CommentRepository;
import danhvu.example.identity_service.repository.PostLikeRepository;
import danhvu.example.identity_service.repository.PostRepository;
import danhvu.example.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    PostLikeRepository likeRepository;
    PostMapper postMapper;
    CommentRepository commentRepository;

    public PostResponse createPost(PostRequest request, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Post post = postMapper.toPost(request, user);
        post.setUser(user);

        return postMapper.toSimplePostResponse(postRepository.save(post), 0 , 0, false);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long postId, String userId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        long likes = likeRepository.countByPostId(postId);

        long comments = commentRepository.countByPostId(postId);

        boolean isLiked = likeRepository.existsByUserIdAndPostId(userId, postId);

        return postMapper.toSimplePostResponse(post, likes, comments, isLiked);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostsByUserName(String username, String currentUserId) {
        if (!userRepository.existsByUsername(username)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        List<Post> posts = postRepository.findAllByUser_UsernameOrderByCreatedAtDesc(username);

        return posts.stream()
                .map(post -> {
                    long likes = likeRepository.countByPostId(post.getId());
                    long comments = commentRepository.countByPostId(post.getId());
                    boolean isLiked = likeRepository.existsByUserIdAndPostId(currentUserId, post.getId());
                    return postMapper.toSimplePostResponse(post, likes,comments, isLiked);
                })
                .collect(Collectors.toList());
    }

    public PostResponse updatePost(Long postId, PostRequest request, String userId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        if(!post.getUser().getId().equals(userId)){
            throw new AppException(ErrorCode.POST_PERMISSION_DENIED);
        }

        postMapper.updatePost(request, post);
        long likes = likeRepository.countByPostId(postId);
        long comments = commentRepository.countByPostId(postId);
        boolean isLiked = likeRepository.existsByUserIdAndPostId(userId, postId);

        return postMapper.toSimplePostResponse(post, likes,comments, isLiked);
    }

    public void deletePost(Long postId, String userId, String userRole) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getId().equals(userId) && !userRole.equals("ADMIN")) {
            throw new AppException(ErrorCode.NOT_POST_OWNER);
        }

        postRepository.delete(post);
    }
}

