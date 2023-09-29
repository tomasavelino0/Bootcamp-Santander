package com.DIOSantander.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.DIOSantander.Dtos.UserDTO;
import com.DIOSantander.domain.user.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class userControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("Testa meu endpoint GET /users")
  public void deveRetornarStatusHttp200() throws Exception {

    mockMvc.perform(get("/users"))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("deve criar um novo user pelo endpoint POST /users")
  public void deveCriarUmNovoUsuario() throws Exception {
    UserDTO userDTO = new UserDTO("tomas", "avelino", "12334512311",
        new BigDecimal("150.0"), "test@test.com", "123456", UserType.COMMOM);

    mockMvc.perform(post("/users")
            .contentType("application/json").content(objectMapper.writeValueAsString(userDTO)))
        .andExpect(status().is2xxSuccessful());
  }

}
