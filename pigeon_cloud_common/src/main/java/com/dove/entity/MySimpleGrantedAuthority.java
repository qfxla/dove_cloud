package com.dove.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;

/**
 * @author ghost
 */
public class MySimpleGrantedAuthority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 521L;

	private String role;

	public MySimpleGrantedAuthority(String role) {
		this.role = role;
	}

	public MySimpleGrantedAuthority() {
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof SimpleGrantedAuthority) {
			return role.equals(((MySimpleGrantedAuthority) obj).role);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.role.hashCode();
	}

	@Override
	public String toString() {
		return this.role;
	}
}
