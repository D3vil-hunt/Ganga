package com.ganga.gateway.util;

import com.ganga.gateway.dto.NetworkResponseDTO;
import com.ganga.gateway.security_filter.CustomUserDetailsService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class JwtTokenProvider {

    private final NetworkUtils networkUtils;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtTokenProvider(NetworkUtils networkUtils, CustomUserDetailsService userDetailsService) {
        this.networkUtils = networkUtils;
        this.customUserDetailsService = userDetailsService;
    }

    private String secretKey;

    @Value("${jwt.secretKey}")
    private String JWT_SECRET_KEY;
    @Value("${jwt.key}")
    private String JWT_KEY;
    @Value("${jwt.validityInMilliseconds}")
    private Integer EXPIRYY_IN_MILLISECONDS;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getBytes());

    }

    public String createToken(String username, HttpServletRequest request){
        NetworkResponseDTO networkResponseDTO = networkUtils.getDeviceAddresses.apply(request);

        return Jwts.builder()
                .setSubject(username)
                .claim("mac", networkResponseDTO.getMacAddress())
                .claim("ip", networkResponseDTO.getIpAddress())
                .setIssuer(JWT_KEY)
                .setExpiration(calculateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Date calculateExpirationDate() {
        Date now = new Date();
        return new Date(now.getTime() + EXPIRYY_IN_MILLISECONDS);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        return (!Objects.isNull(bearerToken) && bearerToken.startsWith("Bearer")) ?
                bearerToken.substring(7, bearerToken.length()) : null;
    }

    public boolean validateToken(String token) throws Exception {

        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return (!claims.getBody().getExpiration().before(new Date()));
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Expired or invalid JWT token");
            throw new Exception("Invalid Token");
        }
    }
    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
