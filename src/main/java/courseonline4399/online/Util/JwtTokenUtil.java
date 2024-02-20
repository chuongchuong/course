package courseonline4399.online.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class JwtTokenUtil {
    private static final String secretKey = "yourSecretKey";

    public static String generateToken() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusMinutes(10); // Token expiration time is 10 minutes from the current time

        // Generate a random token using UUID
        String randomToken = UUID.randomUUID().toString();

        return Jwts.builder()
                .setSubject(randomToken) // Set the token as the subject
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public static boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();

            // Get the subject which is the token generated
            String tokenSubject = claims.getSubject();

            // Add your validation logic here if needed

            return true; // For demonstration, assuming all tokens are valid without additional validation
        } catch (Exception e) {
            // Token is invalid or expired
            return false;
        }
    }
}
