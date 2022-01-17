package br.com.meli.w4;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.meli.w4.entity.Anuncio;
import br.com.meli.w4.entity.Caracteristica;
import br.com.meli.w4.entity.Endereco;
import br.com.meli.w4.entity.Usuario;
import br.com.meli.w4.entity.Vendedor;
import br.com.meli.w4.repository.CaracteristicaRepository;
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
	
	@Autowired
	private CaracteristicaRepository caracteristicaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		//SpringApplication.run(SpringBasicsApplication.class, args);
		persisteAnuncio();
		//persisteUsuario();
		//persisteVendedor();
		//adicionaEnderecoAoVendedor();
		//adicionaCaracteristicas();
		
		
	}

	
	private void adicionaCaracteristicas() {
		Caracteristica volume = Caracteristica.builder().nome("volume").build();
		Caracteristica area = Caracteristica.builder().nome("area").build();
		Caracteristica largura = Caracteristica.builder().nome("largura").build();
		Caracteristica profundidade = Caracteristica.builder().nome("profundidade").build();
		Caracteristica resolucao = Caracteristica.builder().nome("resolucao").build();
		
		this.caracteristicaRepository.save(volume);
		this.caracteristicaRepository.save(area);
		this.caracteristicaRepository.save(largura);
		this.caracteristicaRepository.save(profundidade);
		this.caracteristicaRepository.save(resolucao);
	}
	
	private void adicionaEnderecoAoVendedor() {
		Vendedor vendedor = this.vendedorService.get(4);
		//Endereco goias = Endereco.builder().uf("GO").cidade("gyn").vendedor(vendedor).build();
		//Endereco spo = Endereco.builder().uf("SP").cidade("SPO").vendedor(vendedor).build();
		 
		//List<Endereco> enderecos = new ArrayList<Endereco>();
		//enderecos.add(goias);
		//enderecos.add(spo);
		 
		vendedor.getEnderecos().add(Endereco.builder().uf("RS").cidade("POA").vendedor(vendedor).build());
		
		this.vendedorService.atualiza(vendedor);
		
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
		
		
		
		Vendedor vendedor = Vendedor.builder()
		.nome("joice")
		.usuario(usuario)
		.build();
		
		Endereco endereco1 = Endereco.builder().uf("GO").cidade("gyn").vendedor(vendedor).build();
		 
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco1);
		 
		vendedor.setEnderecos(enderecos);
		
		
//		DadosEndereco dados1 = DadosEndereco.builder()
//			.bairro("morumbi")
//			.cidade("sao paulo")
//			.logradouro("logradouro do vendedor")
//			
//			.numero(358)
//			.uf("SP")
//		.build();
//		
//		
//		DadosEndereco dados2 = DadosEndereco.builder()
//			.bairro("centro")
//			.cidade("goiania")
//			.logradouro("logradouro de goiania")
//			
//			.numero(10)
//			.uf("GO")
//		.build();
//		
//		
//		
//		vendedor.adicionaEndereco(
//					Endereco.builder().dadosEndereco(dados1).build()
//				);
//		
//		
//		vendedor.adicionaEndereco(
//				Endereco.builder().dadosEndereco(dados2).build()
//			);
		
		this.vendedorService.salva(vendedor);
	}
	
	private void persisteAnuncio() {
		
		Vendedor vendedor = this.vendedorService.get(4);
		Caracteristica op1 = this.caracteristicaRepository.getOne(1);
		Caracteristica op3 = this.caracteristicaRepository.getOne(3);
		Caracteristica op4 = this.caracteristicaRepository.getOne(4);
		
		Anuncio anuncio = Anuncio.builder()
			.categoria("ferramentas")
			.codigo("MLB1233423")
			.preco(new BigDecimal(500))
			.titulo("armario de servico")
			.vendedor(vendedor)
		.build();
		anuncio.adicionaCaracteristica(op1);
		anuncio.adicionaCaracteristica(op3);
		anuncio.adicionaCaracteristica(op4);
		anuncioService.registrar(anuncio);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);

	}


}
