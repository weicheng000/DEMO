package com.example.demo.service.impl;

import com.example.demo.mapper.LoginMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.service.LoginService;
import com.example.demo.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    @Override
    public String Login(Admin admin) {
        if (admin.getUsername() == null ||
                admin.getPassword() == null ||
                admin.getUsername().isEmpty() ||
                admin.getPassword().isEmpty()
        ){
            throw new RuntimeException("Empty or Null Data.");
        }

        Admin dbData = loginMapper.login(admin);
        log.info("dbData: {}", dbData);
        if (!admin.getPassword().equals(dbData.getPassword().trim())){
            throw new RuntimeException("Data not Match.");
        }

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", admin.getUsername());

        return JwtUtils.generateJwt(claims);
    }
}
