package com.store.fresh.service;

import com.store.fresh.entity.User;
import com.store.fresh.entity.UserExample;

import java.util.List;

public interface OrderService {

    /*//根据用户id查看所有的角色信息
    public List<String> findRoles(String username);

    //根据用户id查看所有的权限信息
    public List<String> findPermissions(String username);*/

    public User selectByUid(String id);

    public User selectByPrimaryKey(String id);

    public User selectByUsername(String username);

    public String getIdByUsername(String username);

    public int assignDefaultUserRolePermission(User user);

    public List<User> selectWithRoleByExample(UserExample example);

    public int saveUser(User user);

}
