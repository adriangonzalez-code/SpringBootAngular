package com.driagon.springboot.datajpa.app.auth.filter;

import com.driagon.springboot.datajpa.app.auth.service.JWTService;
import com.driagon.springboot.datajpa.app.auth.service.JWTServiceImpl;
import com.driagon.springboot.datajpa.app.models.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));

        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);

        if (username != null && password != null) {
            logger.info("Username desde request parameter (form-data): " + username);
            logger.info("Password desde request parameter (form-data): " + password);
        } else {
            Usuario user = null;
            try {
                 user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
                 username = user.getUsername();
                 password = user.getPassword();

                logger.info("Username desde request InputStream (raw): " + username);
                logger.info("Password desde request InputStream (raw): " + password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = this.jwtService.create(authResult);

        response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX.concat(token));

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("user", ((User)authResult.getPrincipal()));
        body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito!", ((User) authResult.getPrincipal()).getUsername()));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error de autenticación: username or password incorrecto!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
    }
}