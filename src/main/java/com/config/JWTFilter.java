package com.config;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean{
	 @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        try {
	            Authentication authentication = TokenJwtUtil.getAuthentication((HttpServletRequest) servletRequest);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            filterChain.doFilter(servletRequest, servletResponse);
	        }catch(Exception e){
	            String mess = e.toString();
	            if (mess.matches("(?i)(.*)jwt(.*)")){//Bắn ra HTTP status 401 khi xác thực JWT gặp lỗi
	                ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            }else {
	                throw e;
	            }
	        }
	    }
	 
}
