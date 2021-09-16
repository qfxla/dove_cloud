package com.dove.filter;

import com.dove.entity.ConstantValue;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import com.dove.entity.UserDetailsImpl;
import com.dove.util.ApplicationContextUtil;
import com.dove.util.SecurityContextUtil;
import com.dove.util.TokenManage;
import io.jsonwebtoken.Claims;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author ghost
 */
public class InjectSecurityContextFilter extends BasicAuthenticationFilter {

	private RedisTemplate redisTemplate;

	public InjectSecurityContextFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		injectBeans();
	}

	public void injectBeans() {
		redisTemplate = ApplicationContextUtil.getApplicationContext().getBean("redisTemplate", RedisTemplate.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!request.getRequestURI().contains("/swagger") &&
				!request.getRequestURI().contains("/test") &&
				!request.getRequestURI().contains("/login") &&
				!request.getRequestURI().contains("/register") &&
				!request.getRequestURI().contains("/retrieve") &&
				!request.getRequestURI().contains("/captcha") &&
				!request.getRequestURI().contains("/invoke") &&
				!request.getRequestURI().contains("/trace")) {

		Claims claims = TokenManage.parse(request.getHeader("token"));
		if (claims != null && !claims.equals(null)) {
			String userId = claims.getId();
//			String userId = "1367409141675012099";
			UserDetailsImpl userDetails = (UserDetailsImpl) redisTemplate.opsForValue()
					.get(ConstantValue.REDIS_USER_KEY + '_' + userId);

			if (userDetails == null) {
				response.setHeader("Content-Type", "text/ plain;charset=utf-8");

				writeMessage(response.getWriter(), Result.error("用户未登录"));
				return;
			}
			SecurityContextUtil.setSecurityContext(userDetails);
		}else {
			response.setHeader("Content-Type", "text/ plain;charset=utf-8");
			writeMessage(response.getWriter(), Result.error("用户未登录"));
			return;
		}

	  }
		chain.doFilter(request, response);
//			String userId = request.getHeader(ConstantValue.REQUEST_USER_ID);
//
//			UserDetailsImpl userDetails = (UserDetailsImpl) redisTemplate.opsForValue()
//					.get(ConstantValue.REDIS_USER_KEY + '_' + userId);
//
//			if (userDetails == null) {
//				response.setHeader("Content-Type","text/ plain;charset=utf-8");
//
//				writeMessage(response.getWriter(), Result.error("用户未登录"));
//				return;
//			}
//			SecurityContextUtil.setSecurityContext(userDetails);
//		}
//		chain.doFilter(request, response);
	}

	public static void writeMessage(Writer writer, Result result){
		StringBuilder sb = new StringBuilder();
		sb.append("{\n")
				.append("\"flag\": ")
				.append(result.isFlag())
				.append("\n\"statusCode\": ")
				.append(result.getStatusCode())
				.append("\n\"message\": ")
				.append('"')
				.append(result.getMessage())
				.append('"')
				.append("\n\"data\": {}");
		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
