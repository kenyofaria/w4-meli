package br.com.meli.w4.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.meli.w4.entity.Usuario;
import br.com.meli.w4.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	private Logger logger = null;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.logger = LoggerFactory.getLogger(UsuarioService.class);	
	}
	
	public void salvar(Usuario usuario) {
		if(maiorIdade(usuario)) {
			this.usuarioRepository.save(usuario);
		}else {
			throw new RuntimeException("usuario deve ser maior de idade");
		}
	}
	
	public List<Usuario> lista(){
		return this.usuarioRepository.findAll();
	}
	
	public List<Usuario> lista(Integer ano, char sexo){
		return lista().stream()
				.filter(u -> u.getDataNascimento().getYear() == ano)
				.filter(u -> u.getSexo() == sexo).collect(Collectors.toList());
	}
	
	public List<Usuario> lista(Integer ano){
		return lista().stream()
				.filter(u -> u.getDataNascimento().getYear() == ano)
				.collect(Collectors.toList());
	}
	
	public Usuario obter(Long id) {
		 Optional<Usuario> op = this.usuarioRepository.findById(id);
		 return op.orElse(new Usuario());
	}
	
	private boolean maiorIdade(Usuario usuario) {
		LocalDate dataAtual = LocalDate.now();
		int years = Period.between(usuario.getDataNascimento(), dataAtual).getYears();
		return years >= 18;
	}
	
	public List<Usuario> getMulheres(){
		return this.usuarioRepository.findBySexo('F');
	}
	
	public List<Usuario> getHomens(){
		return this.usuarioRepository.findBySexo('M');
	}
}
