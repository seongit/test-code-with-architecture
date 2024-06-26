package com.example.demo.post.infrastructure;

import com.example.demo.post.domain.dto.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

}