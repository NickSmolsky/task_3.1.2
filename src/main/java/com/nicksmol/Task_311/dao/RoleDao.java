package com.nicksmol.Task_311.dao;

import com.nicksmol.Task_311.model.Role;

import java.util.List;

public interface RoleDao {
    Role getByName(String roleName);
    List<Role> getAllRole();
}
