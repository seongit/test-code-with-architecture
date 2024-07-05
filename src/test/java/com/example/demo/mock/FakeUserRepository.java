package com.example.demo.mock;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.service.port.UserRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {

    private final Long id = 0L;

    private final ArrayList<User> data = new ArrayList<>();


    @Override
    public Optional<User> findById(long id) {
        return data.stream().filter(item -> item.getId().equals(id)).findAny();
    }

    @Override
    public Optional<User> findByEmailAndStatus(String email, UserStatus userStatus) {
        return data.stream().filter(item -> item.getEmail().equals(email) && item.getStatus() == userStatus).findAny();
    }

    @Override
    public Optional<User> findByIdAndStatus(long id, UserStatus userStatus) {
        return data.stream().filter(item -> item.getId().equals(id) && item.getStatus() == userStatus).findAny();
    }

    @Override
    public User save(User user) {
        // id가 null이나 0이면 Insert 아니면 Update
        if (user.getId() == null || user.getId() == 0) {
            User newUser = User.builder()
                    .id(id)
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .address(user.getAddress())
                    .status(user.getStatus())
                    .certificationCode(user.getCertificationCode())
                    .lastLoginAt(user.getLastLoginAt())
                    .build();
            data.add(newUser);
            return newUser;
        }else{
            // 기존 데이터 삭제 후 새로 들어온 데이터 저장
            data.removeIf(item -> Objects.equals(item.getId(), user.getId()));
            data.add(user);
            return user;
        }

    }

}
