package br.com.meli.w4.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.meli.w4.dto.UsuarioDTO;
import br.com.meli.w4.entity.Usuario;
import br.com.meli.w4.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@Profile(value = {"test","prod"})
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/ping")
	public String meuMetodo() {
		return "pong";
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> cadastra(@Valid @RequestBody UsuarioDTO dto, UriComponentsBuilder uriBuilder){
		Usuario usuario = UsuarioDTO.converte(dto);
		usuarioService.salvar(usuario);
		URI uri = uriBuilder
				.path("/usuarios/{id}")
				.buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(UsuarioDTO.converte(usuario));
	}
	
	@GetMapping("/obter/{id}")
	public ResponseEntity<UsuarioDTO> obter(@PathVariable Long id) {
		return ResponseEntity.ok(UsuarioDTO.converte(usuarioService.obter(id)));
	}
	
	@GetMapping("/list/{ano}/{s}")
	public ResponseEntity<List<UsuarioDTO>> obter(@PathVariable Integer ano, @PathVariable(name = "s") char sexo) {
		return ResponseEntity.ok(UsuarioDTO.converte(this.usuarioService.lista(ano, sexo)));
	}

	
	@GetMapping("/list")
	public ResponseEntity<List<UsuarioDTO>> obter() {
		return ResponseEntity.ok(UsuarioDTO.converte(this.usuarioService.lista()));
	}
	

	@GetMapping("/list/{ano}")
	private ResponseEntity<List<UsuarioDTO>> getList(@PathVariable Integer ano) {
		return ResponseEntity.ok(UsuarioDTO.converte(usuarioService.lista(ano)));
	}
	
//	Usuario usuarioEncontrado = null;
//	for(Usuario u: usuarios) {
//		if(u.getDataNascimento().getYear() == ano && u.getSexo() == sexo) {
//			usuarioEncontrado = u;
//		}
//	}
//	return usuarioEncontrado;
	
}
