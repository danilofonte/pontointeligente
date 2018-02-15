package com.ponto.inteligente.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ponto.inteligente.api.entities.Employee;
import com.ponto.inteligente.api.enums.RoleEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * 
	 * 
	 * @param employee
	 * @return JwtUser
	 */
	public static JwtUser create(Employee employee) {
		return new JwtUser(employee.getId(), employee.getEmail(), employee.getPassword(),
				mapToGrantedAuthorities(employee.getRole()));
	}

	/**
	 * 
	 * 
	 * @param roleEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(RoleEnum roleEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roleEnum.toString()));
		return authorities;
	}

}
