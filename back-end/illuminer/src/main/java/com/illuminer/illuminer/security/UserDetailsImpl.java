package com.illuminer.illuminer.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.illuminer.illuminer.model.Usuario;

public class UserDetailsImpl implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String senha;
	
	public UserDetailsImpl (Usuario usuario) {
		this.username = usuario.getUsuario();
		this.senha = usuario.getSenha();
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
