package br.com.meli.w4.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.meli.w4.entity.Imovel;
import br.com.meli.w4.repository.ImovelRepository;

@Service
public class ImovelService {

	private ImovelRepository imovelRepository;

	public ImovelService(ImovelRepository imovelRepository) {
		this.imovelRepository = imovelRepository;
 	}
	
	
	private boolean imovelValido(Imovel imovel) {
		if(imovel.getNumero() == 0) {
			return false;
		}
		return true;
	}
	
	public Imovel registra(Imovel imovel) {
		if(imovelValido(imovel)) {
			Optional<Imovel> imovelExistente = this.imovelRepository.findById((imovel.getNumero()));
			if(imovelExistente.isPresent())
				throw new RuntimeException("imovel já cadastrado");
			return this.imovelRepository.save(imovel);			
		}else {
			return null;
		}
	} 
}
