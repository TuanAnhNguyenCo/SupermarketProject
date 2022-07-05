package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.reponsitory.IUserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private IUserReponsitory userReponsitory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userReponsitory.FindUserByName((username));
        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(account.getRoleOfUser().getName()));
        return new User(account.getUsername(),account.getPassword(), authority);
    }
}
