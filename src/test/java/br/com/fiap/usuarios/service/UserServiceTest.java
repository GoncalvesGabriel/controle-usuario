package br.com.fiap.usuarios.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.fiap.usuarios.entity.Account;
import br.com.fiap.usuarios.entity.User;
import br.com.fiap.usuarios.entity.vo.AccountVo;
import br.com.fiap.usuarios.entity.vo.UserVo;
import br.com.fiap.usuarios.repository.user.UserRepository;
import br.com.fiap.usuarios.validator.UserValidator;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserValidator validator;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private UserService service;

    @Test
    public void createUser() {
        UserVo expected = UserVo.builder()
            .name("Gabes")
            .cpfCnpj("33322211144")
            .email("gabes.programante@guiabolso.com.br")
            .build();

        when(repository.save(any())).thenAnswer((Answer<User>) invocation -> {
            Object[] args = invocation.getArguments();
            return (User) args[0];
        });
        User actual = this.service.createUser(expected);
        verify(validator).validateInsert(actual);
        verify(repository).save(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCpfCnpj(), actual.getCpfCnpj());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    @Test
    public void findUserbyCpfCnpj() {
        String cpfCnpj = "12345678900";
        this.service.findUserbyCpfCnpj(cpfCnpj);
        verify(repository).findByCpfCnpj(cpfCnpj);
    }

    @Test
    public void addAccountToUser() {
        AccountVo accountVo = AccountVo.builder().userId(1L).build();
        Optional<User> optionalUser = Optional.of(User.builder().id(1L).build());
        when(repository.findById(accountVo.getUserId())).thenReturn(optionalUser);
        when(accountService.createAccount(accountVo)).thenReturn(Account.builder().id(2L).build());

        Account expectedAccount = this.service.addAccountToUser(accountVo);
        User user = optionalUser.get();
        verify(repository).save(user);
        assertTrue(user.getAccounts().contains(expectedAccount));

    }

    @Test
    public void updateBalance() {
        String cpfCnpj = "12345678911";
        Double balance = 12345d;
        User user = User.builder().cpfCnpj(cpfCnpj).build();
        when(this.repository.cpfCnpj(cpfCnpj)).thenReturn(user);
        this.service.updateBalance(cpfCnpj, balance);
        verify(repository).save(user);
        assertEquals(user.getBalance(), balance);
    }

}
