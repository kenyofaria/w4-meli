package br.com.meli.w4.service;

import java.io.IOException;

import br.com.meli.w4.entity.Imovel;
import br.com.meli.w4.repository.ImovelRepository;

public class ImovelService {

	
	private ImovelRepository imovelRepository;

	public ImovelService(ImovelRepository imovelRepository) {
		this.imovelRepository = imovelRepository;
	}
	
	public Imovel registra(Imovel imovel) {
		Imovel imovelExistente = this.imovelRepository.get(imovel.getNumero());
		if(imovelExistente != null)
			throw new RuntimeException("imovel já cadastrado");
		try {
			return this.imovelRepository.salva(imovel);
		} catch (IOException e) {
			throw new RuntimeException("houve um erro durante a persistencia do imovel.");
		}
	} 
}
