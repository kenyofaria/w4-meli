package br.com.meli.w4.service.integration.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveRetornarListaUsuarios() throws Exception {
		MvcResult result = mockMvc
			.perform(MockMvcRequestBuilders
					.get("/usuarios/list"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void deveCadastrarUmUsuario() throws Exception{
		String payLoad = "{\n"
				+ "	\"nome\":\"fernando\",\n"
				+ "    \"dataNascimento\": \"1980-02-25\",\n"
				+ "    \"sexo\" : \"m\",\n"
				+ "	\"senha\": \"98889\"\n"
				+ "}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payLoad)
				)
		.andExpect(
				MockMvcResultMatchers.status().isCreated()
				);

			//consulta usando algum service
	}
	
}
