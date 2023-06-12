package com.example.spring_boot_rest_api.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class DemoFilter extends OncePerRequestFilter {

   private static final Logger appLogger = LoggerFactory.getLogger(DemoFilter.class);

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      appLogger.debug("Hello Demo Filter");
      filterChain.doFilter(request, response);
   }
}
