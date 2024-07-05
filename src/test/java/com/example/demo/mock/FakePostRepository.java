package com.example.demo.mock;

import com.example.demo.post.domain.dto.Post;
import com.example.demo.post.service.port.PostRepository;
import com.example.demo.user.domain.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class FakePostRepository implements PostRepository {

    private final Long id = 0L;

    private final ArrayList<Post> data = new ArrayList<>();

    @Override
    public Optional<Post> findById(long id) {
        return data.stream().filter(item -> item.getId().equals(id)).findAny();
    }

    @Override
    public Post save(Post post) {
        // id가 null이나 0이면 Insert 아니면 Update
        if (post.getId() == null || post.getId() == 0) {
            Post newPost = Post.builder()
                    .id(id)
                    .content(post.getContent())
                    .writer(post.getWriter())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .build();
            data.add(newPost);
            return newPost;
        } else {
            // 기존 데이터 삭제 후 새로 들어온 데이터 저장
            data.removeIf(item -> Objects.equals(item.getId(), post.getId()));
            data.add(post);
            return post;
        }
    }
}
