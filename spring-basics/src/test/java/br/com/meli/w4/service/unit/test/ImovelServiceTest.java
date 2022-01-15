package br.com.meli.w4.service.unit.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.meli.w4.entity.Bairro;
import br.com.meli.w4.entity.Comodo;
import br.com.meli.w4.entity.Imovel;
import br.com.meli.w4.repository.ImovelRepository;
import br.com.meli.w4.service.ImovelService;

public class ImovelServiceTest {

	@Test
	public void naoDeveRegistrarUmImovelQuandoImovelJaCadastrado() {
	
		Bairro bairro = new Bairro("condominio do lago", "goiania");
		List<Comodo> comodos = Arrays.asList(new Comodo("sala", 25), new Comodo("cozinha", 10));
		Imovel imovel = new Imovel(100, bairro, comodos);
		
		//ImovelRepository repository = new ImovelRepository();
		
		ImovelRepository repositoryMockado = Mockito.mock(ImovelRepository.class);
		ImovelService service = new ImovelService(repositoryMockado);
		
		Mockito.when(repositoryMockado.get(Mockito.anyInt())).thenReturn(imovel);
		
	    RuntimeException ex = assertThrows(
	    		RuntimeException.class,
	    		() -> service.registra(imovel)
	    );
        assertTrue(ex.getMessage().contains("imovel já cadastrado"));
		
	}
	
}
