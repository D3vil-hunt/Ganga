package com.ganga.gateway.security_filter;

import com.ganga.gateway.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, javax.servlet.FilterChain filterChain) throws ServletException, IOException {

        log.info("JWT filter Start");
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        log.info("JWT token resolved");

        try{
            if(!Objects.isNull(token) && jwtTokenProvider.validateToken(token)){
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                if(!Objects.isNull(auth)){
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);

        }catch (Exception e){
            log.error(e.getMessage());
            log.error(e.getStackTrace().toString());
        }
        log.info("JWT JwtTokenFilter exiting");
    }
}
