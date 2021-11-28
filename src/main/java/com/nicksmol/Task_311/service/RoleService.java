package com.nicksmol.Task_311.service;

import com.nicksmol.Task_311.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<Role> getRoleSet(String[] role);
    List<Role> getAllRoles();

}
