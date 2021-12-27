package br.com.meli.w4.controller.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.meli.w4.entity.Usuario;

@Component
public class UsuarioRepository {

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	
	public void salva(Usuario usuario) {
		usuario.setId((long) usuarios.size()+1);
		usuarios.add(usuario);
	}
	
	public List<Usuario> listagem(){
		return usuarios;
	}
	
	public Usuario get(Long id) {
		Optional<Usuario> optional = usuarios.stream().filter(u -> u.getId().equals(id)).findAny();
		return optional.orElse(new Usuario());
	}
}
