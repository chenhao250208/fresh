package com.store.fresh.test;

import com.store.fresh.FreshApplication;
import com.store.fresh.entity.User;
import com.store.fresh.entity.UserRoleKey;
import com.store.fresh.mapper.UserMapper;
import com.store.fresh.mapper.UserRoleMapper;
import com.store.fresh.util.DataUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreshApplication.class)
public class AddUserTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Test
    public void test(){
        for(int i=0;i<1000;i++){
            User user = new User();
            String userId = DataUtil.getRandomNo(15);
            user.setUserId(userId);
            user.setUsername("user"+i);
            user.setSalt(""+i);
            String passwordEncoded = new SimpleHash("md5","123456",""+i,2).toString();
            user.setPassword(passwordEncoded);
            user.setTel("18934567879");
            user.setEmail("user"+i+"@xxx.com");
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);

            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserId(userId);
            userRoleKey.setRoleId(2);
            userRoleMapper.insertSelective(userRoleKey);
        }
    }
}
