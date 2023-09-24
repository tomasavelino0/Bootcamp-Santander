package com.DIOSantander.services;

import com.DIOSantander.Dtos.UserDTO;
import com.DIOSantander.domain.user.User;
import com.DIOSantander.domain.user.UserType;
import com.DIOSantander.repositories.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public void validateTransaction(User sender, BigDecimal amount) throws Exception {
    if (sender.getUserType() == UserType.MERCHANT) {
      throw new Exception("Usuario logista nao pode realizar transacao");
    }

    if (sender.getBalance().compareTo(amount) < 0) {
      throw new Exception("Sem saldo");
    }
  }

  public User findUserById(Long id) throws Exception {
    return this.repository.findUserById(id)
        .orElseThrow(() -> new Exception("Usuario nao encontrado"));
  }

  public void saveUser(User user) {
    this.repository.save(user);
  }

  public User createUser(UserDTO userDTO) {
    User newUser = new User(userDTO);
    this.saveUser(newUser);
    return newUser;
  }

  public List<User> getAllUser() {
    return this.repository.findAll();
  }

}
