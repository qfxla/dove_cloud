package com.pigeon.filter;

import com.pigeon.entity.ConstantValue;
import com.pigeon.entity.Result;
import com.pigeon.entity.UserDetailsImpl;
import com.pigeon.util.ApplicationContextUtil;
import com.pigeon.util.SecurityContextUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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

	//获取到自定义的redisTemplate模板
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

			String userId = request.getHeader(ConstantValue.REQUEST_USER_ID);

			//在redis中拿到用户标识id
			UserDetailsImpl userDetails = (UserDetailsImpl) redisTemplate.opsForValue()
																	.get(ConstantValue.REDIS_USER_KEY + '_' + userId);

			if (userDetails == null) {
				response.setHeader("Content-Type","text/ plain;charset=utf-8");

				writeMessage(response.getWriter(), Result.error("用户未登录"));
				return;
			}
			SecurityContextUtil.setSecurityContext(userDetails);
		}
		chain.doFilter(request, response);
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