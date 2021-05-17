package com.ganga.loginservice.util;

import com.ganga.loginservice.dto.NetworkResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final NetworkUtils networkUtils;

    @Autowired
    public JwtTokenProvider(NetworkUtils networkUtils) {
        this.networkUtils = networkUtils;
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

}
