package br.com.meli.w4.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.meli.w4.entity.Usuario;

@Component
public class UsuarioRepository implements OurRepository<Usuario, Long>{

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	private final String PATH = "usuarios.json";
	
	public Usuario salva(Usuario usuario) throws IOException {
		usuario.setId((long) usuarios.size()+1);
		usuarios.add(usuario);
		objectMapper.writeValue(new File(PATH), usuarios);
		return usuario;
	}
	
	public List<Usuario> listagem() throws IOException{
		File file = new File(PATH);
		FileInputStream is = new FileInputStream(file);
		usuarios = Arrays.asList(objectMapper.readValue(is, Usuario[].class));
		return usuarios;
	}
	
	public Usuario get(Long id) {
		Optional<Usuario> optional = usuarios.stream().filter(u -> u.getId().equals(id)).findAny();
		return optional.orElse(new Usuario());
	}
}
