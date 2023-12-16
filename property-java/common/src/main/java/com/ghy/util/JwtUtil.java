package com.ghy.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ghy.exceptionHandler.MyException;
import com.ghy.exceptionHandler.RCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 *
 * @author GHY
 * @date 2023/11/21
 */
@Component
public class JwtUtil {

    private static long expirationTime=7200000;

    private static String secretKey = "exam";

    public static String createJWT() {
        // 创建JWT的构建器
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime * 1000)) // 过期时间
                .sign(algorithm); // 使用指定的算法进行签名

        // 输出生成的JWT令牌
        return token;
    }

    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    /**
     * 从token中获取JWT中的负载
     */
    public static boolean checkToken(String token) throws MyException {
        // 创建算法实例
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            // 解析令牌
            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (Exception e) {
            // 如果令牌无法解析，处理异常
            throw new MyException(RCode.INVALID_TOKEN);
        }
        return true;
    }
}
