package com.meli.jwt.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meli.jwt.model.Usuario;

@Service
public class AuthenticationService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new Usuario("kenyo", "$2a$10$FnSsqc9hnfZ.HLR0HDZ0gOGbNnd1yit.sZitZVibdCgle1E6cwL4a");
	}

}
