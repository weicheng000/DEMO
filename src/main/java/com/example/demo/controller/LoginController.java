package com.example.demo.controller;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Result;
import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("")
    public Result login(@RequestBody Admin admin){
        try{
            String jwt = loginService.Login(admin);
            return Result.success(jwt);
        }catch (RuntimeException e){
            return Result.error(e.toString());
        }
    }
}
