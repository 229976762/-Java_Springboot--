package com.ghy.server.config;

import com.ghy.exceptionHandler.MyException;
import com.ghy.exceptionHandler.RCode;
import com.ghy.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * Token拦截器
 * @Author GHY
 * @Date 2023/11/22
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws MyException {
        String token = request.getHeader("Authorization");
        log.info("Token验证: {}", token);
        // 如果是静态资源路径，直接放行
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/static/")) {
            return true;
        }
        if (token == null || !JwtUtil.checkToken(token)) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new MyException(RCode.INVALID_TOKEN);
        }

        return true;
    }
}
