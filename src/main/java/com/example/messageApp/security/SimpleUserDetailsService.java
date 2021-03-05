package com.example.messageApp.security;


import com.example.messageApp.dao.UserDAO;
import com.example.messageApp.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        // emailでデータベースからユーザーエンティティを検索する
        UserDO user = userDAO.getByAccount(email);

        if (user == null) {
            throw new UsernameNotFoundException(email + "is not found");
        }
        return new SimpleLoginUser(user);
    }
}
