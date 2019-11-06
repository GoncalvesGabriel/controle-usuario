package br.com.fiap.usuarios.validator;

import br.com.fiap.usuarios.entity.User;
import br.com.fiap.usuarios.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User> {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void validateInsert(User entity) {
    if (userRepository.existWithCpfCnpjOrEmail(entity.getCpfCnpj(), entity.getEmail())) {
      throw new RuntimeException(String.format("Já existe outro usuário cadastrado com o mesmo cpf/cnpj ou email"));
    }
  }
}
