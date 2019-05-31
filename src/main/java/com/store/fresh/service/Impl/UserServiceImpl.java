package com.store.fresh.service.Impl;

import com.store.fresh.entity.*;
import com.store.fresh.mapper.*;
import com.store.fresh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<String> findRoles(String username) {
        return userMapper.findRoles(username);
    }

    @Override
    public List<String> findPermissions(String username) {
        return userMapper.findPermissions(username);
    }

    @Override
    public User selectByUid(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size()==1){
            return userList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public String getIdByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        User user = null;
        if(userList.size()>0){
            user = userList.get(0);
            return user.getUserId();
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public int assignDefaultUserRolePermission(User user) {
        int success1 = userMapper.insert(user);
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setUserId(user.getUserId());
        userRoleKey.setRoleId(2);
        int success2 = userRoleMapper.insert(userRoleKey);
        return success1 + success2;
    }

    @Override
    public List<User> selectWithRoleByExample(UserExample example) {
        List<User> userList = userMapper.selectByExample(example);
        for(int i=0;i<userList.size();i++){
            List<String> roleList = findRoles(userList.get(i).getUserName());
            userList.get(i).setRoleList(roleList);
        }
        return userList;
    }

    @Transactional
    @Override
    public int saveUser(User user) {
        userMapper.insertSelective(user);
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setUserId(user.getUserId());
        userRoleKey.setRoleId(1);
        return userRoleMapper.insertSelective(userRoleKey);
    }

    @Override
    public String getUserIdFromSecurity() {
//从SecurityUtils中获得用户主体
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        User user = userMapper.selectByExample(userExample).get(0);
        return user.getUserId();
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
