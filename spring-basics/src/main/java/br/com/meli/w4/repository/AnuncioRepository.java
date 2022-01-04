package br.com.meli.w4.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.meli.w4.entity.Anuncio;

@Component
public class AnuncioRepository implements OurRepository<Anuncio, Integer>{

	private List<Anuncio> anuncios = new ArrayList<Anuncio>();
	
	@Override
	public Anuncio salva(Anuncio anuncio) throws IOException {
		anuncio.setId(anuncios.size()+1);
		anuncios.add(anuncio);
		return anuncio;
	}

	@Override
	public List<Anuncio> listagem() throws IOException {
		return anuncios;
	}

	@Override
	public Anuncio get(Integer id) {
		Optional<Anuncio> op = anuncios.stream().filter(a -> a.getId() == id).findFirst();
		return op.orElse(new Anuncio());
	}

}
