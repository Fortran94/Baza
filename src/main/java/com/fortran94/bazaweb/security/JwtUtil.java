//package com.fortran94.bazaweb.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//public class JwtUtil {
//
//    private static final String SECRET = "m4nQ8k9v0sZlPqXcY2tR3w==";
//    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));  //Секретный ключ
//    private final long TIME = 1000 * 60 * 60; //Хранится 1 час
//
//    /*Создает токен*/
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("roles",
//                        userDetails.getAuthorities()
//                                .stream()
//                                .map(a -> a.getAuthority())
//                                .toList()
//                )                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + TIME))
//                .signWith(key)
//                .compact();
//    }
//
//    /*Получает узера по имени из переданного токена*/
//    public String getUsernameFromToken(String token) {
//        return getClaim(token, Claims::getSubject);
//    }
//
//    /*Проверяет совпадение пользователя по имени и срок жизни токена*/
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenLife(token));
//    }
//
//    /*Проверяет срок годности токена*/
//    public boolean isTokenLife(String token) {
//        return getExpiration(token).before(new Date());
//    }
//
//    public Date getExpiration(String token) {
//        return getClaim(token, Claims::getExpiration);
//    }
//
//    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
//        Claims claims = Jwts.parser()
//                .verifyWith(key)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//
//        return claimsResolver.apply(claims);
//    }
//}
