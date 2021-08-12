package com.pigeon.entity;


import java.io.Serializable;
import java.util.List;

/**
 * @author ghost
 */
public class UserDetailsImpl implements Serializable {

	private Long id;

	private String phone;

	private String username;

	private Integer sex;

	private Long enterpriseId;

	private List<MySimpleGrantedAuthority> authorities;

	public List<MySimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<MySimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
