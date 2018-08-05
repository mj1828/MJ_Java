package com.mj.zas.config.security;


import org.springframework.security.core.GrantedAuthority;

/**
 * Created by yangyibo on 17/2/15.
 */
public class UrlGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private String permissionUrl;
	private String method;

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public UrlGrantedAuthority(String permissionUrl, String method) {
		this.permissionUrl = permissionUrl;
		this.method = method;
	}

	@Override
	public String getAuthority() {
		return this.permissionUrl + ";" + this.method;
	}
}

