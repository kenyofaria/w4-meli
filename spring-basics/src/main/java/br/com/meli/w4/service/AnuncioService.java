package br.com.meli.w4.service;

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
		return this.anuncioRepository.save(anuncio);
	}
	

	public List<Anuncio> listagem() {
		return this.anuncioRepository.findAll();
	}
}
