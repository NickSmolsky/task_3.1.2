package com.nicksmol.Task_311.service;

import com.nicksmol.Task_311.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void save(User user);

    User selectById(long id);

    void update(User user, long id);

    void delete(long id);

    User findByUsername(String username);
}
