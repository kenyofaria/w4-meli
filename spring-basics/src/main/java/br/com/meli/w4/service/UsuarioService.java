package br.com.meli.w4.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import br.com.meli.w4.entity.Usuario;

@Service
public class UsuarioService {

	
	public void salvar(Usuario usuario) {
		if(maiorIdade(usuario)) {
			System.out.println("persistindo usuario");
		}else {
			throw new RuntimeException("usuario deve ser maior de idade");
		}
	}
	
	private boolean maiorIdade(Usuario usuario) {
		LocalDate dataAtual = LocalDate.now();
		int years = Period.between(usuario.getDataNascimento(), dataAtual).getYears();
		return years >= 18;
	}
}
