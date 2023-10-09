package com.DIOSantander.dev;

import com.DIOSantander.Dtos.UserDTO;
import com.DIOSantander.domain.user.User;
import com.DIOSantander.domain.user.UserType;
import com.DIOSantander.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    private void loadInitialData() {
        UserDTO userDTO = new UserDTO("test", "teste", "12312312354",
                new BigDecimal(200), "test@gmail.com", "123456", UserType.COMMOM);

        User userTest = new User(userDTO);
        userRepository.save(userTest);
    }
}
