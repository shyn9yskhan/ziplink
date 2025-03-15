package kz.ziplink.qr_code_generation_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    public static final String SECRET = "resx586748652712865drcftvgbh3456kmujnhjuyhgtrferf9685282756828562ybgvfcdsx789rgtfvrgttgrrewerfy5865752725865257517";

    public static String extractUsername(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(SECRET).build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); // "sub" field in the JWT
        } catch (Exception e) {
            return null; // Token is invalid
        }
    }
}