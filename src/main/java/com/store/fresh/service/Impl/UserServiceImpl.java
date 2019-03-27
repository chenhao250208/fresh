package com.store.fresh.service.Impl;

import com.store.fresh.entity.Permission;
import com.store.fresh.entity.Role;
import com.store.fresh.entity.User;
import com.store.fresh.mapper.UserMapper;
import com.store.fresh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> findRoles(Integer id) {
        return null;
    }

    @Override
    public List<Permission> findPermissions(Integer id) {
        return null;
    }
}
