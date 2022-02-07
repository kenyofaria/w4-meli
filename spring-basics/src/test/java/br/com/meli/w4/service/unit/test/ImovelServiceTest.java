package br.com.meli.w4.service.unit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.meli.w4.entity.Bairro;
import br.com.meli.w4.entity.Comodo;
import br.com.meli.w4.entity.Imovel;
import br.com.meli.w4.repository.ImovelRepository;
import br.com.meli.w4.service.ImovelService;

public class ImovelServiceTest {

	private Imovel imovel;
	
	@Test
	public void naoDeveRegistrarUmImovelQuandoImovelJaCadastrado() {
		ImovelRepository repositoryMockado = Mockito.mock(ImovelRepository.class);
		ImovelService service = new ImovelService(repositoryMockado);
		Mockito.when(repositoryMockado.findById(Mockito.anyInt())).thenReturn(Optional.of(imovel));
	    RuntimeException ex = assertThrows(
	    		RuntimeException.class,
	    		() -> service.registra(imovel)
	    );
        assertTrue(ex.getMessage().contains("imovel já cadastrado"));
	}

	@BeforeEach
	public void initImovel() {
		Bairro bairro = new Bairro("condominio do lago", "goiania");
		List<Comodo> comodos = Arrays.asList(new Comodo(1,"sala", 25), new Comodo(2, "cozinha", 10));
		imovel = new Imovel(100, bairro, comodos);
	}
	
	@AfterEach
	public void depoisDeCada() {
		System.out.println("depois de cada");
	}
	
	@Test
	public void deveRegistrarUmImovelQuandoImovelNaoCadastrado() throws IOException {
		ImovelRepository repositoryMockado = Mockito.mock(ImovelRepository.class);
		ImovelService service = new ImovelService(repositoryMockado);
		Mockito.when(repositoryMockado.save(Mockito.any(Imovel.class))).thenReturn(imovel);
		Imovel imovelRegistrado = service.registra(imovel);
		//Mockito.doNothing().when(repositoryMockado).fazAlgo();
		//Mockito.verify(repositoryMockado, Mockito.times(1)).fazAlgo();
        assertEquals(imovel.getNumero(), imovelRegistrado.getNumero());
	}
	@Test
	public void testea() {
		System.out.println("teste a");
		assertTrue(true);
	}
	@Test
	public void testeb() {
		System.out.println("teste b");
		assertTrue(true);
	}
	@Test
	public void teste1() {
		System.out.println("teste c");
		assertTrue(true);
	}
	
}
