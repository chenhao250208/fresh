package com.store.fresh.security;

import com.store.fresh.entity.Permission;
import com.store.fresh.entity.Role;
import com.store.fresh.entity.User;
import com.store.fresh.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * shiro的权限信息配置
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        //此处把当前subject对应的所有角色信息交给shiro，调用hasRole的时候就根据这些role信息判断
        String username = (String) principals.getPrimaryPrincipal();
        List<String> roles = userService.findRoles(username);
        Set<String> roleNames = new HashSet<>(roles);
        authorizationInfo.setRoles(roleNames);

        //此处把当前subject对应的权限信息交给shiro，当调用hasPermission的时候就会根据这些信息判断
        List<String> permissions = userService.findPermissions(username);
        Set<String> permissionNames = new HashSet<>(permissions);
        authorizationInfo.setStringPermissions(permissionNames);

        return authorizationInfo;
    }

    /**
     * 根据token获取认证信息authenticationInfo
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**这里为什么是String类型呢？其实要根据Subject.login(token)时候的token来的，你token定义成的pricipal是啥，这里get的时候就是啥。比如我
         Subject subject = SecurityUtils.getSubject();
         UsernamePasswordToken idEmail = new UsernamePasswordToken(String.valueOf(user.getId()), user.getEmail());
         try {
         idEmail.setRememberMe(true);
         subject.login(idEmail);
         }
         **/
        /**
         * 将Object强制转换为String
         */
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = (String) token.getPrincipal();
        String password = new String(token.getPassword());
        User user = userService.selectByUsername(username);
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        String passwordEncoded = new SimpleHash("md5",password,salt,2).toString();

        if(null==user || !passwordEncoded.equals(passwordInDB))
            throw new AuthenticationException();
        //SimpleAuthenticationInfo还有其他构造方法，比如密码加密算法等
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,                      //表示凭证，可以随便设，跟token里的一致就行
                password,   //表示密钥如密码，你可以自己随便设，跟token里的一致就行
                getName()
        );
        //authenticationInfo信息交个shiro，调用login的时候会自动比较这里的token和authenticationInfo
        return authenticationInfo;
    }
}
