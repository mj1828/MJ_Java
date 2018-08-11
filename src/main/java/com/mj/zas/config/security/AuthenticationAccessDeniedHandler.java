package com.mj.zas.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.mj.util.ResultUtil;

/**
 * Created by sang on 2017/12/29.
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e)
			throws IOException, ServletException {
		resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(ResultUtil.fail("权限不足，请联系管理员!").toString());
		out.flush();
		out.close();
	}
}
