package com.example.demo.medium;

import com.example.demo.common.exception.ResourceNotFoundException;
import com.example.demo.user.domain.User;
import com.example.demo.user.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestPropertySource("classpath:test-application.properties")

@SqlGroup({
        @Sql(value = "/sql/user-repository-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    @DisplayName("getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다")
    void getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다() {
        // given
        String email = "kok202@naver.com";
        // when
        User result = userService.getByEmail(email);
        // then
        assertThat(result.getNickname()).isEqualTo("kok202");
    }

    @Test
    @DisplayName("getByEmail은_PENDING_상태인_유저를_찾아올_수_없다")
    void getByEmail은_PENDING_상태인_유저를_찾아올_수_없다() {
        // given
        String email = "test@naver.com";
        // when
        // then
        assertThatThrownBy(() -> {
            userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

}
