package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entities.Actions;
import com.entities.Role;
import com.entities.Users;
import com.repository.RoleRepository;
import com.repository.UserRepository;

import java.lang.annotation.Annotation;
import java.util.*;
@Service("appAuthorizer")
public class AppAuthorizerImpl implements AppAuthorizer{
	private final Logger logger = LoggerFactory.getLogger(AppAuthorizerImpl.class);
	private RoleRepository _roleRepository;
	private UserRepository _userRepository;
	public AppAuthorizerImpl() {
		_roleRepository=new RoleRepository(Role.class);
		_userRepository=new UserRepository(Users.class);
	}
	@Override
	public boolean authorize(Authentication authentication, String action, Object callerObj) {
		 String securedPath = extractSecuredPath(callerObj);
	        if (securedPath==null || "".equals(securedPath.trim())) {//login, logout
	            return true;
	        }
	        String menuCode = securedPath.substring(1);//Bỏ dấu "/" ở đầu Path
	        boolean isAllow = false;
	        try {
	            UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) authentication;
	            if (user==null){
	                return isAllow;
	            }
	            int userId = Integer.parseInt(user.getPrincipal().toString());
//	            if (userId==null || "".equals(userId.trim())) {
//	                return isAllow;
//	            }
	            if (userId==0) {
	                return isAllow;
	            }
	           if(_userRepository.checkActionByUserId(userId, action))
	            {
	                isAllow = true;
	            }
	        } catch (Exception e) {
	            logger.error(e.toString(), e);
	            throw e;
	        }
	        return isAllow;
	}
	  // Lay ra securedPath duoc Annotate RequestMapping trong Controller
    private String extractSecuredPath(Object callerObj) {
        Class<?> clazz = ResolvableType.forClass(callerObj.getClass()).getRawClass();
        Optional<Annotation> annotation = Arrays.asList(clazz.getAnnotations()).stream().filter((ann) -> {
            return ann instanceof RequestMapping;
        }).findFirst();
        logger.debug("FOUND CALLER CLASS: {}", ResolvableType.forClass(callerObj.getClass()).getType().getTypeName());
        if (annotation.isPresent()) {
            return ((RequestMapping) annotation.get()).value()[0];
        }
        return null;
    }
}
