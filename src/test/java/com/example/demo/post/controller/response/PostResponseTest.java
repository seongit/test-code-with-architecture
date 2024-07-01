package com.example.demo.post.controller.response;

import com.example.demo.post.domain.dto.Post;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PostResponseTest {
    @Test
    void Post로_응답객체를_생성할_수_있다() {
    	// given
        Post post = Post.builder()
                .writer(User.builder()
                        .id(1L)
                        .email("se.kim@naver.com")
                        .nickname("seong")
                        .address("Seoul")
                        .status(UserStatus.ACTIVE)
                        .certificationCode(UUID.randomUUID().toString())
                        .build())
                .content("test")
                .build();
    	// when
        PostResponse postResponse = PostResponse.from(post);
    	// then
        assertThat(postResponse.getContent()).isEqualTo("test");
    }
}
