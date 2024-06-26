package com.example.demo.user.infrastructure;

import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<com.example.demo.user.domain.User> findById(long id) {
        return userJpaRepository.findById(id).map(User::toModel);
    }

    @Override
    public Optional<com.example.demo.user.domain.User> findByEmailAndStatus(String email, UserStatus userStatus) {
        return userJpaRepository.findByEmailAndStatus(email,userStatus).map(User::toModel);
    }

    @Override
    public Optional<com.example.demo.user.domain.User> findByIdAndStatus(long id, UserStatus userStatus) {
        return userJpaRepository.findByIdAndStatus(id,userStatus).map(User::toModel);
    }

    @Override
    public com.example.demo.user.domain.User save(com.example.demo.user.domain.User user) {
        return userJpaRepository.save(User.fromModel(user)).toModel();
    }

}
