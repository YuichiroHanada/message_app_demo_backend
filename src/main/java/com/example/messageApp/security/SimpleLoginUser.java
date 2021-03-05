package com.example.messageApp.security;

import com.example.messageApp.domain.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class SimpleLoginUser extends User {

    private UserDO user;

    public UserDO getUser() {
        return user;
    }

    public SimpleLoginUser(UserDO user) {
        super(user.getAccount(), user.getPassword(), determineRoles(user.getAdmin()));
        this.user = user;
    }

    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static List<GrantedAuthority> determineRoles(boolean isAdmin) {
        return isAdmin ? ADMIN_ROLES : USER_ROLES;
    }
}
