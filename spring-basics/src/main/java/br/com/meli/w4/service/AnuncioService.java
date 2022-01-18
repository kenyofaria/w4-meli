package br.com.meli.w4.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.meli.w4.entity.Anuncio;
import br.com.meli.w4.entity.AnuncioCaracteristica;
import br.com.meli.w4.entity.Caracteristica;
import br.com.meli.w4.repository.AnuncioCaracteristicaRepository;
import br.com.meli.w4.repository.AnuncioRepository;
import br.com.meli.w4.repository.AnuncioRepository.CaracteristicaTmp;
import br.com.meli.w4.repository.CaracteristicaRepository;
import exception.BusinessException;

@Service
public class AnuncioService {

	private AnuncioRepository anuncioRepository;
	@Autowired
	private CaracteristicaRepository caracteristicaRepository;
	@Autowired
	private AnuncioCaracteristicaRepository anuncioCaracteristicaRepository;
	
	public AnuncioService(AnuncioRepository anuncioRepository) {
		this.anuncioRepository = anuncioRepository;
	}
	
	@SuppressWarnings("deprecation")
	public Anuncio registrar(Anuncio anuncio) {
		if(anuncio.getPreco().compareTo(new BigDecimal(0)) <= 0)
			throw new BusinessException("Nao eh permitido registro de anuncio com valor zero");
		else {
			Anuncio a = this.anuncioRepository.save(anuncio);
			a.getCaracteristicas().forEach(c -> {
				Optional<Caracteristica> x = this.caracteristicaRepository.findById(c.getId());
				AnuncioCaracteristica ac = AnuncioCaracteristica.builder().anuncio(a).caracteristica(x.get()).valor(c.getValor()).build();
				this.anuncioCaracteristicaRepository.save(ac);
				
			});
			return a;
		}
	}
	

	public List<Anuncio> listagem() {
		return this.anuncioRepository.findAll();
	}
	
	public List<Anuncio> listagem(Integer id){
		Pageable paginacao = PageRequest.of(1, 3);
		Page<Anuncio> paginas = this.anuncioRepository.findByVendedor_Id(id, paginacao);
		System.out.println("total de paginas: " + paginas.getTotalPages());
		
		return paginas.getContent();
	}
	
	public List<Caracteristica> listaCaracteristicas(Integer id){
		List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
		List<CaracteristicaTmp> list = this.anuncioRepository.listaDeCaracteristicas(id);
		
		for (CaracteristicaTmp item : list) {
			caracteristicas.add(Caracteristica.builder()
					.nome(item.getNome())
					.valor(item.getValor()).build());
		}
		return caracteristicas;
	}
}
