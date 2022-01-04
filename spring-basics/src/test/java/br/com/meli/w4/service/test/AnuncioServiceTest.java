package br.com.meli.w4.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import br.com.meli.w4.entity.Anuncio;
import br.com.meli.w4.repository.AnuncioRepository;
import br.com.meli.w4.service.AnuncioService;
import exception.BusinessException;

public class AnuncioServiceTest {

	@Test
	public void deveRegistrarUmAnuncio() throws IOException {
		//arrange
		Anuncio anuncio = Anuncio.builder()
				.codigo("MLB0988")
				.titulo("chave inglesa")
				.categoria("ferramentas")
				.preco(new BigDecimal(15.00)).build();
				
		AnuncioRepository mock = Mockito.mock(AnuncioRepository.class);
		Mockito.when(mock.salva(anuncio)).thenReturn(anuncio);
		AnuncioService anuncioService = new AnuncioService(mock);
		
		//act
		Anuncio anuncioRegistro = anuncioService.registrar(anuncio);
		
		//assertion
		assertEquals(anuncio.getId(), anuncioRegistro.getId());
	}
	
	@Test
	public void naoDeveRegistrarAnuncioQuandoPrecoForZero() {
		//arrange
		Anuncio anuncio = Anuncio.builder()
				.codigo("MLB0988")
				.titulo("chave inglesa")
				.categoria("ferramentas")
				.preco(new BigDecimal(0)).build();
		
		AnuncioRepository mock = Mockito.mock(AnuncioRepository.class);
		AnuncioService anuncioService = new AnuncioService(mock);
				
	    BusinessException excecaoEsperada = assertThrows(
	    		BusinessException.class,
	            () -> anuncioService.registrar(anuncio) //act
	     );

	    //assertion
	    assertTrue(excecaoEsperada.getMessage().contains("Nao eh permitido registro de anuncio com valor zero"));
	}
}
