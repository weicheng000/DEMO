package com.example.demo.interceptor;

import com.example.demo.pojo.Result;
import com.example.demo.utils.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ServiceCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        String token = request.getHeader("Authorization");
        response.setContentType("application/json;charset = UTF-8");


        Gson gson = new Gson();

        if (token == null || token.isEmpty()){
            log.info("Token Not Exist.");
            Result notLogin = Result.error("Not Login 1");
            response.getWriter().write(gson.toJson(notLogin));
            return false;
        }

        try {
            Claims claims = JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("Header is not correct: " + e);
            Result notLogin = Result.error("Not Login 2");
            response.getWriter().write(gson.toJson(notLogin));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
