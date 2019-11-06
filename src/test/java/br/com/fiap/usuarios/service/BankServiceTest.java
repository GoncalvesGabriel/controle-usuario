package br.com.fiap.usuarios.service;

import br.com.fiap.usuarios.repository.bank.BankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

    @Mock
    private BankRepository repository;

    @InjectMocks
    private BankService service;

    @Test
    public void findBankByNumber() {
        this.service.findBankByNumber("201");
        Mockito.verify(repository).findByNumber("201");
    }

}
