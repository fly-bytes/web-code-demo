package com.example.demo.config.util;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String secret = "ZAZXAASSSKFAKOFSAKFPASKPASKPCMAPCPAMCSLAMPWKPKWQPMCLAMSLAMSLMS....123456";
    private static final Key KEY = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());

    // 加密
    public static String encryption(String payload) {
        Map<String, Object> stringObjectMap = new HashMap();
        stringObjectMap.put("type", "jwt");
        String token = Jwts.builder().setHeader(stringObjectMap)
                .setPayload(payload).signWith(SignatureAlgorithm.HS512, KEY).compact();
        return token;
    }

    // 解密
    public static Claims Decrypt(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims;
    }

}
