package com.DIOSantander.integration;

import com.DIOSantander.Dtos.UserDTO;
import com.DIOSantander.domain.user.User;
import com.DIOSantander.domain.user.UserType;
import com.DIOSantander.services.UserService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

  @Autowired
  UserService userService;

  @Test
  @DisplayName("Deve inserir um novo usuario ao banco de dados com sucesso")
  public void deveCriarUmUserComSucesso() {
    UserDTO userDTO = new UserDTO("tomas", "avelino", "12334512311",
        new BigDecimal("150.0"), "test@test.com", "123456", UserType.COMMOM);

    User newUser = this.userService.createUser(userDTO);
    int size = this.userService.getAllUser().size();
    List<User> users = this.userService.getAllUser();

    Assertions.assertEquals(1, size);
    Assertions.assertEquals(newUser.getDocument(), users.get(0).getDocument());
  }

  @Test
  @DisplayName("Deve lancar uma Exception ao nao encontrar um usuario pelo ID")
  public void throwExceptionAoNaoEncontrarPorId() {
    long userNotExistent = 10;

    Throwable exception = Assertions.assertThrows(Exception.class, () -> {
      this.userService.findUserById(userNotExistent);
    });

    Assertions.assertEquals("Usuario nao encontrado", exception.getMessage());
  }

  @Test
  @DisplayName("Deve lancar uma Exception de que usuarios logistas nao podem fazer uma transacao")
  public void validaQueUmMerchantNaoPodeFazerUmaTransacao() {
    UserDTO userDTO = new UserDTO("tomas", "avelino", "12334512311",
        new BigDecimal("150.0"), "test@test.com", "123456", UserType.MERCHANT);

    User userSender = new User(userDTO);

    Throwable exception = Assertions.assertThrows(Exception.class, () -> {
      this.userService.validateTransaction(userSender, new BigDecimal(15));
    });

    Assertions.assertEquals(
        "Usuario logista nao pode realizar transacao",
        exception.getMessage());
  }

  @Test
  @DisplayName("Deve lancar uma Exception quando um usuario nao tiver saldo")
  public void validaOsaldoDeUmUsuario() {
    UserDTO userDTO = new UserDTO("tomas", "avelino", "12334512311",
        new BigDecimal("0"), "test@test.com", "123456", UserType.COMMOM);

    User userSender = new User(userDTO);

    BigDecimal valorAcimaDoSaldo = new BigDecimal("100");

    Throwable exception = Assertions.assertThrows(Exception.class, () -> {
      this.userService.validateTransaction(userSender, valorAcimaDoSaldo);
    });

    Assertions.assertEquals("Sem saldo", exception.getMessage());
  }
}
