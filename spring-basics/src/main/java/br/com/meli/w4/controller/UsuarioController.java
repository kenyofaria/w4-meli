package br.com.meli.w4.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meli.w4.dto.UsuarioDTO;
import br.com.meli.w4.entity.Usuario;
import br.com.meli.w4.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	private static List<Usuario> usuarios = 
			Arrays.asList(Usuario.builder()
					.nome("rafael")
					.dataNascimento(LocalDate.parse("1999-12-10"))
					.sexo('m')
					.build(),
					Usuario.builder()
					.nome("anderson")
					.dataNascimento(LocalDate.parse("2000-01-30"))
					.sexo('m')
					.build(),
					Usuario.builder()
					.nome("amanda")
					.dataNascimento(LocalDate.parse("2000-02-28"))
					.sexo('f')
					.build(),
					Usuario.builder()
					.nome("helena")
					.dataNascimento(LocalDate.parse("2010-05-27"))
					.sexo('f')
					.build()
					);
	
	
	@GetMapping("/{p}")
	public String meuMetodo(@PathVariable String p) {
		return "voce me passou o parametro " + p;
	}
	
	@GetMapping("/ping")
	public String meuMetodo() {
		return "pong";
	}
	
	@GetMapping("/{ano}/{s}")
	public Usuario meuMetodo(@PathVariable Integer ano, @PathVariable(name = "s") char sexo) {
		return obter(ano, sexo);
	}

	@GetMapping
	public Usuario xyz(@RequestParam Integer ano, @RequestParam(name = "s") char sexo) {
		return obter(ano, sexo);
	}
	
	private Usuario obter(Integer ano, char sexo) {
		Optional<Usuario> op = usuarios.stream()
				.filter(u -> u.getDataNascimento().getYear()==ano)
				.filter(u -> u.getSexo() == sexo)
				.findFirst();
		return op.orElse(new Usuario());
	}
	@GetMapping("/list/{ano}")
	private List<Usuario> getList(@PathVariable Integer ano) {
		List<Usuario> lista = usuarios.stream()
				.filter(u -> u.getDataNascimento().getYear()==ano)
				.collect(Collectors.toList());
		return lista;
	}
	
//	Usuario usuarioEncontrado = null;
//	for(Usuario u: usuarios) {
//		if(u.getDataNascimento().getYear() == ano && u.getSexo() == sexo) {
//			usuarioEncontrado = u;
//		}
//	}
//	return usuarioEncontrado;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastra(@RequestBody UsuarioDTO dto){
		Usuario usuario = UsuarioDTO.converte(dto);
		usuarioService.salvar(usuario);
		return ResponseEntity.ok(UsuarioDTO.converte(usuario));
	}
	
}
