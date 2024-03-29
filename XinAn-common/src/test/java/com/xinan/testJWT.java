package com.xinan;

import com.xinan.properties.JwtProperties;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = testJWT.class)
public class testJWT {
    @Test
    public void genJWT()
    {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setUserSecretKey("xinan0322");
        jwtProperties.setUserTtl(86400000);
        jwtProperties.setUserTokenName("token");
        //用map表示参数对
        Map<String,Object> claims = new HashMap<>();
        claims.put("1","小明");
        claims.put("2","小红");

        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getUserSecretKey())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getUserTtl()))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void parseJWT()
    {
        JwtParser parser = Jwts.parser().setSigningKey("xinan0322");
        Jwt parse = parser.parse("eyJhbGciOiJIUzI1NiJ9.eyIxIjoi5bCP5piOIiwiMiI6IuWwj-e6oiIsImV4cCI6MTcxMTIwMTA3M30.ebFT4fHFcnVHMtMx6F6V9ucQsUk-CKRw1xxIwTNRaVY");
        Object body = parse.getBody();
        System.out.println(body);
    }
}
