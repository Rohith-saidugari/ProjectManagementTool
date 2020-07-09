package com.rohith.ppmtool.config.security;

import com.rohith.ppmtool.model.User;
import com.rohith.ppmtool.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.rohith.ppmtool.config.security.SecurityConstants.HEADER;
import static com.rohith.ppmtool.config.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwtToken = extractTokenFromRequest(httpServletRequest);
            if(StringUtils.hasText(jwtToken) && tokenProvider.validateToken(jwtToken)){
                Long userId = tokenProvider.getUserIdFromJWT(jwtToken);
                User userDetails = (User) userDetailService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                  userDetails,null, Collections.emptyList()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(Exception exe){
            logger.error("Cannot set user authentication in security context" +exe.getMessage());
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String extractTokenFromRequest(HttpServletRequest httpServletRequest){
        String bearerToken =  httpServletRequest.getHeader(HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
