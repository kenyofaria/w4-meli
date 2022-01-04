package br.com.meli.w4.service.integration.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveRetornarListaUsuarios() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders
					.get("/usuarios/list"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deveCadastrarUmUsuario() throws Exception{
		String payLoad = "{\n"
				+ "	\"nome\":\"kenyo\",\n"
				+ "    \"dataNascimento\": \"1980-02-25\",\n"
				+ "    \"sexo\" : \"m\",\n"
				+ "	\"senha\": \"98392982\"\n"
				+ "}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payLoad))
		.andExpect(MockMvcResultMatchers.status().isCreated());
		
		
	}
	
}
