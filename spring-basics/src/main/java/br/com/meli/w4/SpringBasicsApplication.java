package br.com.meli.w4;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meli.w4.entity.Anuncio;
import br.com.meli.w4.service.AnuncioService;

@SpringBootApplication
public class SpringBasicsApplication implements CommandLineRunner{

	@Autowired
	private AnuncioService anuncioService;
	
	@Override
	public void run(String... args) throws Exception {
		//SpringApplication.run(SpringBasicsApplication.class, args);


		Anuncio anuncio = Anuncio.builder()
		.categoria("ferramentas")
		.codigo("MLB1234543")
		.preco(new BigDecimal(100))
		.titulo("jogo de chave norueguesa").build();
		anuncioService.registrar(anuncio);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);

	}


}
