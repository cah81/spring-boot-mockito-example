package com.knf.dev.mockito.repository;

import com.knf.dev.mockito.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByName(String name);
}
