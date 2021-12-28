package br.com.meli.w4.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.meli.w4.controller.repository.UsuarioRepository;
import br.com.meli.w4.entity.Usuario;
import exception.RepositoryException;

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
			try {
				usuarioRepository.salva(usuario);
				logger.debug("usuario salvo");
			}catch(IOException e) {
				logger.error(e.getMessage());
				logger.debug("passando no catch");
				throw new RepositoryException("MSG Customizada: Erro ao gravar o usuario");
			}
		}else {
			throw new RuntimeException("usuario deve ser maior de idade");
		}
	}
	
	public List<Usuario> lista(){
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioRepository.listagem();
		} catch (IOException e) {
			throw new RepositoryException("erro ao recuperar os usuarios");
		}
		return usuarios;
	}
	
	public List<Usuario> lista(Integer ano, char sexo){
		try {
			return usuarioRepository.listagem().stream()
					.filter(u -> u.getDataNascimento().getYear() == ano)
					.filter(u -> u.getSexo() == sexo).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Usuario> lista(Integer ano){
		try {
			return usuarioRepository.listagem().stream()
					.filter(u -> u.getDataNascimento().getYear() == ano)
					.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
