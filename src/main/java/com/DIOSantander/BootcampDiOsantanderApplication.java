package com.DIOSantander;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(servers = {
    @Server(url = "/", description = "default server URL")}, info = @Info(title =
    "API Desenvolvida como projeto final para o bootcamp SantanderDIO, "
        + "você poderá criar usuários e simular uma transação financeira.", version = "2.0", description = "transacao entre usuarios"))
public class BootcampDiOsantanderApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootcampDiOsantanderApplication.class, args);
  }

}
