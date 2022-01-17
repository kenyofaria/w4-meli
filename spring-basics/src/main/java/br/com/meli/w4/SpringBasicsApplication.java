package br.com.meli.w4;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meli.w4.entity.Anuncio;
import br.com.meli.w4.entity.Endereco;
import br.com.meli.w4.entity.Usuario;
import br.com.meli.w4.entity.Vendedor;
import br.com.meli.w4.service.AnuncioService;
import br.com.meli.w4.service.UsuarioService;
import br.com.meli.w4.service.VendedorService;

@SpringBootApplication
public class SpringBasicsApplication implements CommandLineRunner{

	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VendedorService vendedorService;
	
	
	@Override
	public void run(String... args) throws Exception {
		//SpringApplication.run(SpringBasicsApplication.class, args);
		//persisteAnuncio();
		//persisteUsuario();
		persisteVendedor();
		
	}

	private void persisteUsuario() {
		Usuario usuario = Usuario.builder()
							.dataNascimento(LocalDate.of(1995, Month.DECEMBER, 30))
							.nome("joice aurino")
							.senha("123")
							.sexo('F')
							.build();
		usuarioService.salvar(usuario);
	}

	private void persisteVendedor() {
		
		Usuario usuario = this.usuarioService.obter(1L);
		
		Endereco[] end = new Endereco[2]; 
		end[0] = Endereco.builder()
				
				.bairro("morumbi")
				.cidade("sao paulo")
				.logradouro("logradouro do vendedor")
				
				.numero(358)
				.uf("SP").build();
		end[1] = Endereco.builder()
				
				.bairro("centro")
				.cidade("goiania")
				.logradouro("logradouro de goiania")
				
				.numero(10)
				.uf("GO").build();
		Vendedor vendedor = Vendedor.builder()
		.endereco(end)
		.nome("joice")
		.usuario(usuario)
		.build();
		this.vendedorService.salva(vendedor);
	}
	
	private void persisteAnuncio() {
		
		Vendedor vendedor = this.vendedorService.get(1);
		
			
		Anuncio anuncio = Anuncio.builder()
		.categoria("ferramentas")
		.codigo("MLB123")
		.preco(new BigDecimal(300))
		.titulo("jogo de chave inglesa")
		.vendedor(vendedor)
		.build();
		anuncioService.registrar(anuncio);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);

	}


}
