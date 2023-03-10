package com.week.zumgnmarket.repository;

import com.week.zumgnmarket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
    boolean existsByNickName(String nickName);
    User findByNickName(String nickName);
}
