package br.com.meli.w4.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.meli.w4.controller.repository.UsuarioRepository;
import br.com.meli.w4.entity.Usuario;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public void salvar(Usuario usuario) {
		if(maiorIdade(usuario)) {
			usuarioRepository.salva(usuario);
		}else {
			throw new RuntimeException("usuario deve ser maior de idade");
		}
	}
	
	public List<Usuario> lista(){
		return usuarioRepository.listagem();
	}
	
	public List<Usuario> lista(Integer ano, char sexo){
		return usuarioRepository.listagem().stream()
				.filter(u -> u.getDataNascimento().getYear() == ano)
				.filter(u -> u.getSexo() == sexo).collect(Collectors.toList());
	}
	
	public List<Usuario> lista(Integer ano){
		return usuarioRepository.listagem().stream()
				.filter(u -> u.getDataNascimento().getYear() == ano)
				.collect(Collectors.toList());
	}
	
	public Usuario obter(Long id) {
		return usuarioRepository.get(id);
	}
	
	private boolean maiorIdade(Usuario usuario) {
		LocalDate dataAtual = LocalDate.now();
		int years = Period.between(usuario.getDataNascimento(), dataAtual).getYears();
		return years >= 18;
	}
}
