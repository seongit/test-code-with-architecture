package com.example.demo.user.domain;

import com.example.demo.common.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import com.example.demo.user.domain.dto.UserCreate;
import com.example.demo.user.domain.dto.UserUpdate;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UserTest {

    @Test
    void User는_UserCreate_객체로_생성할_수_있다() {
    	// given
        UserCreate userCreate = UserCreate.builder()
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .build();
    	// when
        User user = User.from(userCreate,new TestUuidHolder("test"));

    	// then
        assertThat(user.getNickname()).isEqualTo("seong");
        assertThat(user.getEmail()).isEqualTo("se.kim@naver.com");
        assertThat(user.getAddress()).isEqualTo("Seoul");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("test");
    }

    @Test
    void User는_UserUpdate_객체로_데이터를_업데이트_할_수_있다() {
        // given
        // 변경의 대상
        User user = User.builder()
                .id(1L)
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .build();;

        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("seong-2")
                .address("Jeju")
                .build();

        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getNickname()).isEqualTo("seong-2");
        assertThat(user.getAddress()).isEqualTo("Jeju");

    }
    @Test
    void User는_로그인을_할_수_있고_로그인시_마지막_로그인_시간이_변경된다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .lastLoginAt(100L)
                .build();;
        // when
        user = user.login(new TestClockHolder(200L));
        // then
        assertThat(user.getLastLoginAt()).isEqualTo(200L);
    }

    @Test
    void User는_인증_코드로_계정을_활성화_할_수_있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.PENDING)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .lastLoginAt(100L)
                .build();;
        // when
        user = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    void User는_잘못된_인증_코드로_계정을_활성화_하려고하면_에러를_뱉는다() {
        User user = User.builder()
                .id(1L)
                .email("se.kim@naver.com")
                .nickname("seong")
                .address("Seoul")
                .status(UserStatus.PENDING)
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .lastLoginAt(100L)
                .build();
        // when
        // then
        assertThatThrownBy(() -> user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"))
                .isInstanceOf(CertificationCodeNotMatchedException.class);
    }

}
