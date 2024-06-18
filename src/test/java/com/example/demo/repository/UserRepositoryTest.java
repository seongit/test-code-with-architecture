package com.example.demo.repository;

import com.example.demo.model.UserStatus;
import org.h2.engine.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = true)
@Sql
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void UserRepository가_제대로_연결되었다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("deveun@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("test2");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("test");

        // when
        UserEntity result = userRepository.save(userEntity);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    void findByIdAndStatus로_유저_데이터를_찾아올_수_있다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("deveun@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("test2");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("test");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.ACTIVE);
        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByIdAndStatus는_데이터가_없으면_Optional_empty를_내려준다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("deveun@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("test2");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("test");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1, UserStatus.PENDING);
        // then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void findByEmailAndStatus_로_유저_데이터를_찾아올_수_있다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("deveun@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("test2");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("test");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("deveun@naver.com", UserStatus.ACTIVE);
        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByEmailAndStatus_는_데이터가_없으면_Optional_empty를_내려준다() {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("deveun@naver.com");
        userEntity.setAddress("Seoul");
        userEntity.setNickname("test2");
        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setCertificationCode("test");

        // when
        userRepository.save(userEntity);
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("deveun@naver.com", UserStatus.PENDING);
        // then
        assertThat(result.isEmpty()).isTrue();
    }

}
