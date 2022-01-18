package br.com.meli.w4;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
		//persisteAnuncio();
		//persisteUsuarios();
		//persisteVendedor();
		//adicionaEnderecoAoVendedor();
		//adicionaCaracteristicas();
		//buscaUsuariosPorSexo();
		//listaAnunciosPorVendedor();
		listaDeCaracteristicasPorAnuncio();
		
	}

	
	private void listaDeCaracteristicasPorAnuncio() {
		List<Caracteristica> listaCaracteristicas = this.anuncioService.listaCaracteristicas(18);
		listaCaracteristicas.forEach(c -> System.out.println(c.getNome() + "    " + c.getValor()));
	}


	private void listaAnunciosPorVendedor() {
		List<Anuncio> listagem = this.anuncioService.listagem(4);
		listagem.forEach(item->System.out.println(item.getId()));
		
	}


	private void buscaUsuariosPorSexo() {
		System.out.println("apenas mulheres ------------------ \n\n");
		List<Usuario> mulheres = this.usuarioService.getMulheres();
		mulheres.forEach(m -> System.out.println(m.getNome()));
		
		System.out.println("\n\n apenas homens ------------------ \n\n");
		List<Usuario> homens = this.usuarioService.getHomens();
		homens.forEach(m -> System.out.println(m.getNome()));
		
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
	
	private void persisteUsuarios() {
		Usuario mauri = Usuario.builder()
							.dataNascimento(LocalDate.of(1981, Month.APRIL, 30))
							.nome("Mauri Klein")
							.senha("123")
							.sexo('M')
							.build();
		
		Usuario michele = Usuario.builder()
				.dataNascimento(LocalDate.of(1987, Month.AUGUST, 27))
				.nome("Michele")
				.senha("123")
				.sexo('F')
				.build();
		
		usuarioService.salvar(mauri);
		usuarioService.salvar(michele);
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
		Anuncio anuncio = Anuncio.builder()
			.categoria("ferramentas")
			.codigo("MLB1233423")
			.preco(new BigDecimal(500))
			.titulo("armario de servico")
			.vendedor(vendedor)
		.build();
		anuncio.adicionaCaracteristica(Caracteristica.builder().id(1).build(), "valor 1");
		anuncio.adicionaCaracteristica(Caracteristica.builder().id(3).build(), "valor 3");
		anuncio.adicionaCaracteristica(Caracteristica.builder().id(4).build(), "valor 4");
		anuncioService.registrar(anuncio);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);

	}


}
