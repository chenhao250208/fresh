package com.store.fresh.service;

import com.store.fresh.entity.Permission;
import com.store.fresh.entity.Role;
import com.store.fresh.entity.User;

import java.util.List;

public interface UserService {
    public User selectById(Integer id);
    public List<Role> findRoles(Integer id);
    public List<Permission> findPermissions(Integer id);
}
