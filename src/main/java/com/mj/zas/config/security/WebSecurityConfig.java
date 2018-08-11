package com.mj.zas.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mj.util.MD5Util;
import com.mj.util.ResultUtil;
import com.mj.util.UserUtil;
import com.mj.zas.service.UserService;

/**
 * Created by sang on 2017/12/28.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private RedisConnectionFactory redisConnection;

	@Autowired
	UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	@Autowired
	UrlAccessDecisionManager urlAccessDecisionManager;
	@Autowired
	AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
	@Autowired
	AjaxSessionInformationExpiredStrategy ajaxSessionInformationExpiredStrategy;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/login", "/js/**", "/jquery/**", "/topic/**", "/hello/**", "/page/**",
				"/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
				o.setAccessDecisionManager(urlAccessDecisionManager);
				return o;
			}
		}).and().formLogin().loginPage("/login").loginProcessingUrl("/admin/login").usernameParameter("username")
				.passwordParameter("password").permitAll().failureHandler(authenticationFailureHandler())
				.successHandler(authenticationSuccessHandler()).and().logout().permitAll().and().csrf().disable()
				.exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler).and().sessionManagement()
				.maximumSessions(1).expiredSessionStrategy(ajaxSessionInformationExpiredStrategy);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnection);
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	// 校验用户名密码
	private PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(MD5Util.encode((String) rawPassword));
			}
		};
	}

	// 登录成功
	private AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
					HttpServletResponse httpServletResponse, Authentication authentication)
					throws IOException, ServletException {
				httpServletResponse.setContentType("application/json;charset=utf-8");
				PrintWriter out = httpServletResponse.getWriter();
				ObjectMapper objectMapper = new ObjectMapper();
				out.write(ResultUtil.success(objectMapper.writeValueAsString(UserUtil.getCurrentHr())).toString());
				out.flush();
				out.close();
			}
		};
	}

	// 登录失败
	private AuthenticationFailureHandler authenticationFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
					HttpServletResponse httpServletResponse, AuthenticationException e)
					throws IOException, ServletException {
				httpServletResponse.setContentType("application/json;charset=utf-8");
				PrintWriter out = httpServletResponse.getWriter();
				if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
					out.write(ResultUtil.fail("用户名或密码输入错误，登录失败!").toString());
				} else if (e instanceof DisabledException) {
					out.write(ResultUtil.fail("账户被禁用，登录失败，请联系管理员!").toString());
				} else {
					out.write(ResultUtil.fail("登录失败!").toString());
				}
				out.flush();
				out.close();
			}
		};
	}
}