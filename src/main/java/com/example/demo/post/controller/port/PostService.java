package com.example.demo.post.controller.port;

import com.example.demo.post.domain.dto.Post;
import com.example.demo.post.domain.dto.PostCreate;
import com.example.demo.post.domain.dto.PostUpdate;

public interface PostService {

    Post getById(long id);

    Post create(PostCreate postCreate);

    Post update(long id, PostUpdate postUpdate);
}
