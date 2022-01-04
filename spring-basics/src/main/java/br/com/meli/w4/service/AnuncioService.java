package br.com.meli.w4.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.meli.w4.entity.Anuncio;
import br.com.meli.w4.repository.AnuncioRepository;
import exception.BusinessException;

@Service
public class AnuncioService {

	private AnuncioRepository anuncioRepository;
	
	public AnuncioService(AnuncioRepository anuncioRepository) {
		this.anuncioRepository = anuncioRepository;
	}
	
	public Anuncio registrar(Anuncio anuncio) {
		if(anuncio.getPreco().compareTo(new BigDecimal(0)) <= 0)
			throw new BusinessException("Nao eh permitido registro de anuncio com valor zero");
		try {
			this.anuncioRepository.salva(anuncio);
		} catch (IOException e) {
			throw new BusinessException("Erro ao persistir o anuncio");
		}
		return anuncio;
	}

	public List<Anuncio> listagem() {
		try {
			return this.anuncioRepository.listagem();
		} catch (IOException e) {
			throw new BusinessException("Erro ao recuperar os anuncios");
		}
	}
}
