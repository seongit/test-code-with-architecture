package com.example.demo.user.controller;

import com.example.demo.mock.TestContainer;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.dto.UserCreate;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UserCreateControllerTest {


    @Test
    void 사용자는_회원가입을_할_수있고_회원가입된_사용자는_PENDING_상태이다(){
        // given
        TestContainer testContainer = TestContainer.builder()
                .uuidHolder(() -> "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
                .build();

        UserCreate userCreate = UserCreate.builder()
                .email("kok202@kakao.com")
                .nickname("kok202")
                .address("Pangyo")
                .build();

        // when
        ResponseEntity<UserResponse> result = testContainer.userCreateController.createUser(userCreate);

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(result.getBody().getEmail()).isEqualTo("kok202@kakao.com");
        assertThat(testContainer.userRepository.getById(1).getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
    }
}
