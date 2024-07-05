package com.example.demo.medium;

import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.infrastructure.UserEntity;
import com.example.demo.user.infrastructure.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(showSql = true)
@Sql("/sql/user-repository-test-data.sql")
public class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void findByIdAndStatus로_유저_데이터를_찾아올_수_있다() {
        // when
        Optional<UserEntity> result = userJpaRepository.findByIdAndStatus(1, UserStatus.ACTIVE);
        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByIdAndStatus는_데이터가_없으면_Optional_empty를_내려준다() {
        Optional<UserEntity> result = userJpaRepository.findByIdAndStatus(1, UserStatus.PENDING);
        // then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void findByEmailAndStatus_로_유저_데이터를_찾아올_수_있다() {
        Optional<UserEntity> result = userJpaRepository.findByEmailAndStatus("kok202@naver.com", UserStatus.ACTIVE);
        // then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    void findByEmailAndStatus_는_데이터가_없으면_Optional_empty를_내려준다() {
        Optional<UserEntity> result = userJpaRepository.findByEmailAndStatus("deveun@naver.com", UserStatus.PENDING);
        // then
        assertThat(result.isEmpty()).isTrue();
    }

}
