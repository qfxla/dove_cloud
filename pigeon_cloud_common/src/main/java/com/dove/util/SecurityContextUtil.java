package com.dove.util;

import com.dove.entity.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ghost
 */
public class SecurityContextUtil {

	public static SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}

	public static void setSecurityContext(UserDetailsImpl userDetails) {
		getSecurityContext().
				setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,
						userDetails.getId(), userDetails.getAuthorities()));
	}

	public static UserDetailsImpl getUserDetails() {
		//返回用户id
		return (UserDetailsImpl) getSecurityContext().getAuthentication().getPrincipal();
	}
}
