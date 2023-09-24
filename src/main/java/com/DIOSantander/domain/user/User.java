package com.DIOSantander.domain.user;

import com.DIOSantander.Dtos.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String document;
  @Column(unique = true)
  private String email;
  private String password;
  private BigDecimal balance;
  @Enumerated(EnumType.STRING)
  private UserType userType;


  public User(UserDTO userDTO) {
    this.firstName = userDTO.firstName();
    this.lastName = userDTO.lastName();
    this.email = userDTO.email();
    this.balance = userDTO.balance();
    this.document = userDTO.document();
    this.password = userDTO.password();
    this.userType = userDTO.userType();
  }
}
