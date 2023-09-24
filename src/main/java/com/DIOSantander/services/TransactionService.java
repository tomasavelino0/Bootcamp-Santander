package com.DIOSantander.services;

import com.DIOSantander.Dtos.TransactionDTO;
import com.DIOSantander.domain.transaction.Transaction;
import com.DIOSantander.domain.user.User;
import com.DIOSantander.repositories.TransactionRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  private UserService userService;

  @Autowired
  private TransactionRepository reposity;

  @Autowired
  private NotificationService notificationService;

  public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
    User sender = this.userService.findUserById(transactionDTO.senderId());
    User receiver = this.userService.findUserById(transactionDTO.receiverId());

    userService.validateTransaction(sender, transactionDTO.value());

    Transaction transaction = new Transaction();
    transaction.setAmount(transactionDTO.value());
    transaction.setSender(sender);
    transaction.setReceiver(receiver);
    transaction.setTimestamp(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
    receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

    reposity.save(transaction);
    userService.saveUser(sender);
    userService.saveUser(receiver);

    this.notificationService.sendNotification(sender, "Transacao concluida com sucesso");
    this.notificationService.sendNotification(receiver, "Transancao recebida");

    return transaction;
  }
}
