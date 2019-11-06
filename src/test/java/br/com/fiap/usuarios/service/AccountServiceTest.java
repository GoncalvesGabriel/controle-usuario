package br.com.fiap.usuarios.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import br.com.fiap.usuarios.entity.Account;
import br.com.fiap.usuarios.entity.Bank;
import br.com.fiap.usuarios.entity.vo.AccountVo;
import br.com.fiap.usuarios.repository.account.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository repository;

    @Mock
    private BankService bankService;

    @InjectMocks
    private AccountService service;

    @Test
    public void createAccount() {
        AccountVo expected = AccountVo.builder()
            .number("1234")
            .digit("2")
            .agencyNumber("1452")
            .agencyDigit("5")
            .bankNumber("204")
            .build();
        Bank bank = Bank.builder().name("CAIXA").number("204").build();
        when(bankService.findBankByNumber(expected.getBankNumber())).thenReturn(bank);

        Account actual = this.service.createAccount(expected);

        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getDigit(), actual.getDigit());
        assertEquals(expected.getAgencyNumber(), actual.getAgencyNumber());
        assertEquals(expected.getAgencyDigit(), actual.getAgencyDigit());
        assertEquals(bank, actual.getBank());
    }

}
