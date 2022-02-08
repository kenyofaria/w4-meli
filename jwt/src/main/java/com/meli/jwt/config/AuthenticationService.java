package com.meli.jwt.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meli.jwt.model.Representante;
import com.meli.jwt.model.Usuario;
import com.meli.jwt.model.Vendedor;
import com.meli.jwt.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	private UsuarioRepository usuarioRepository;
	
	public AuthenticationService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByLogin(login);
		if(usuario instanceof Representante) {
			return new Representante(usuario.getId(), usuario.getLogin(), usuario.getSenha());
		}
		return new Vendedor(usuario.getId(), usuario.getLogin(), usuario.getSenha());
		
		//return new Usuario("kenyo", "$2a$10$FnSsqc9hnfZ.HLR0HDZ0gOGbNnd1yit.sZitZVibdCgle1E6cwL4a");
	}

}
