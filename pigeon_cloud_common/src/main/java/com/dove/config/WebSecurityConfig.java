package com.dove.config;

import com.dove.filter.InjectSecurityContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 安全配置类
 * @author ghost
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 将authenticationManager放入容器
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/swagger-resources/**"
															, "/webjars/**"
															, "/v2/**"
															, "/swagger-ui.html/**");
	}

	/**
	 * 配置security的控制逻辑
	 *
	 * @Param http 请求
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//不用认证
				.antMatchers("/authority/user/login"
						, "/authority/user/register"
						, "/authority/user/retrieve"
						, "/authority/captcha"
						, "/authority/trace/enterprise/{id}/{uuid}"
						, "/authority/invoke").permitAll()
				//其他的需要认证后才能访问
				.anyRequest().authenticated()
				.and()
				// 修改为自定义登陆
				.httpBasic().disable()
				// 禁用表单登陆
				.formLogin().disable()
				.logout().disable()
				// 开启跨域
				.cors()
				.and()
				// 取消跨站请求伪造防护
				.csrf().disable();
		// 基于Token不需要session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// 禁用缓存
		http.headers().cacheControl();
		// 添加JWT过滤器
		http.addFilter(new InjectSecurityContextFilter(authenticationManager()));
	}
}
