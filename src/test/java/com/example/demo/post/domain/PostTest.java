package com.example.demo.post.domain;

import com.example.demo.mock.TestClockHolder;
import com.example.demo.post.domain.dto.Post;
import com.example.demo.post.domain.dto.PostCreate;
import com.example.demo.post.domain.dto.PostUpdate;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class PostTest {

    @Test
    void PostCreate으로_게시물을_만들_수_있다() {
    	// given
        User writer =  User.builder()
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode(UUID.randomUUID().toString())
                .build();

        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("helloWorld")
                .build();

        // when
        Post post = Post.from(writer,postCreate,new TestClockHolder(1678530673958L));

    	// then
        assertThat(post.getContent()).isEqualTo("helloWorld");
        assertThat(post.getWriter().getEmail()).isEqualTo("se.kim@naver.com");
        assertThat(post.getCreatedAt()).isEqualTo(1678530673958L);
    }

    @Test
    void PostUpdate로_게시글을_수정할_수_있다() {
        // given
        User writer =  User.builder()
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
                .build();

        Post post = Post.builder()
                .id(1L)
                .content("helloworld")
                .createdAt(1678530673958L)
                .modifiedAt(0L)
                .writer(writer)
                .build();

        PostUpdate postUpdate = PostUpdate.builder()
                .content("foobar")
                .build();

        // when
        post = post.update(postUpdate, new TestClockHolder(1678530673958L));

        // then
        assertThat(post.getContent()).isEqualTo("foobar");
        assertThat(post.getWriter().getEmail()).isEqualTo("se.kim@naver.com");
        assertThat(post.getCreatedAt()).isEqualTo(1678530673958L);
    }



}
