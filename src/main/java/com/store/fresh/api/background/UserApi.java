package com.store.fresh.api.background;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.fresh.entity.User;
import com.store.fresh.entity.UserExample;
import com.store.fresh.service.UserService;
import com.store.fresh.util.Base;
import com.store.fresh.util.DataUtil;
import com.store.fresh.util.ResponseEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity login(String userName, String password, ServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        //登录成功之后跳转到原url
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = "/";
        if(savedRequest != null){
            /*url = savedRequest.getRequestUrl();*/
            url = "/foreground/index";
        }else {
            url = "/foreground/index";
        }
        try {
            subject.login(token);
            Session session = subject.getSession();
            session.setAttribute("subject", subject);
            if(subject.hasRole("admin")){
                return ResponseEntity.ok().put("msg","登陆成功").put("url","/background/index");
            }else{
                System.out.println("url="+url);
                return ResponseEntity.ok().put("msg","登陆成功").put("url",url);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.error(410,"密码或用户名错误");
        }
    }

    @RequiresRoles("admin")
    @GetMapping("/getList")
    public @ResponseBody
    ResponseEntity getList(Integer pageNum,Integer pageSize,String criteria){
        UserExample userExample = new UserExample();
        if(Base.notEmpty(criteria)){
            userExample.setOrderByClause("create_time desc");
            userExample.or().andUserNameLike("%"+criteria+"%");
            userExample.or().andTelLike("%"+criteria+"%");
            userExample.or().andEmailLike("%"+criteria+"%");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userService.selectWithRoleByExample(userExample);
        PageInfo pageInfo=new PageInfo(userList);
        return ResponseEntity.ok("获取成功").put("pageInfo",pageInfo);
    }

    @PostMapping("/save")
    public @ResponseBody
    ResponseEntity save(User user){
        String userId = DataUtil.getRandomNo(15);
        String password = user.getPassword();
        String passwordEncoded = new SimpleHash("md5",password,user.getUserName(),2).toString();

        user.setSalt(user.getUserName());
        user.setPassword(passwordEncoded);
        user.setUserId(userId);

        //保存用户默认角色：管理员
        if(userService.saveUser(user)==1){
            return ResponseEntity.ok("添加成功");
        }else{
            return ResponseEntity.error(410,"添加失败");
        }
    }
}
