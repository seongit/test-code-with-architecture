package com.example.demo.user.controller.response;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseTest {

    @Test
    void User으로_응답을_생성할_수_있다() {
    	// given
        User user = User.builder()
                .id(1L)
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode(UUID.randomUUID().toString())
                .build();
    	// when
        MyProfileResponse myProfileResponse = MyProfileResponse.from(user);

    	// then
        assertThat(myProfileResponse.getId()).isEqualTo(user.getId());

    }
}
