package com.nicksmol.Task_311.dao;

import com.nicksmol.Task_311.model.Role;

public interface RoleDao {
    Role getByName(String roleName);
}
