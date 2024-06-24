package com.example.demo.service;

import com.example.demo.repository.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource("classpath:test-application.properties")
@Sql("/sql/user-repository-test-data.sql")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다")
    void getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        String email = "kok202@naver.com";
        // when
        UserEntity result = userService.getByEmail(email);
        // then
        assertThat(result.getNickname()).isEqualTo("kok202");
    }

    @Test
    @DisplayName("getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다")
    void getByEmail은_PENDING_상태인_유저를_찾아올_수_없다() {
        // given
        String email = "test@naver.com";
        // when
        UserEntity result = userService.getByEmail(email);
        // then
        assertThat(result.getNickname()).isEqualTo("test");
    }

}
