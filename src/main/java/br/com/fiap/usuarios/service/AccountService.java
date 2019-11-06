package br.com.fiap.usuarios.service;

import br.com.fiap.usuarios.entity.Account;
import br.com.fiap.usuarios.entity.Bank;
import br.com.fiap.usuarios.entity.vo.AccountVo;
import br.com.fiap.usuarios.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  private AccountRepository repository;

  @Autowired
  private BankService bankService;

  public Account createAccount(AccountVo accountVo) {
    Bank bank = bankService.findBankByNumber(accountVo.getBankNumber());
    Account account = new Account();
    account.setNumber(accountVo.getNumber());
    account.setDigit(accountVo.getDigit());
    account.setAgencyNumber(accountVo.getAgencyNumber());
    account.setAgencyDigit(accountVo.getAgencyDigit());
    account.setBank(bank);
    return account;
  }

}
