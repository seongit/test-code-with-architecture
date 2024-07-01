package com.example.demo.post.domain;

import com.example.demo.post.domain.dto.Post;
import com.example.demo.post.domain.dto.PostCreate;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class PostTest {

    @Test
    void PostCreate으로_게시물을_만들_수_있다() {
    	// given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("helloWorld")
                .build();

        User writer =  User.builder()
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode(UUID.randomUUID().toString())
                .build();

    	// when
        Post post = Post.from(writer,postCreate);

    	// then
        assertThat(post.getContent()).isEqualTo("helloWorld");
        assertThat(post.getWriter().getEmail()).isEqualTo("se.kim@naver.com");
    }



}
