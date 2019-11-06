package br.com.fiap.usuarios.validator;

import static org.mockito.Mockito.when;

import br.com.fiap.usuarios.entity.User;
import br.com.fiap.usuarios.repository.user.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserValidator validator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validateInsert() {
        User entity = User.builder().cpfCnpj("12345678911").email("gabriel.gabes@custodia.com.br").build();
        when(userRepository.existWithCpfCnpjOrEmail(entity.getCpfCnpj(),
                                                    entity.getEmail())).thenReturn(true);
        expectedException.expectMessage("Já existe outro usuário cadastrado com o mesmo cpf/cnpj ou email");

        this.validator.validateInsert(entity);

    }
}


