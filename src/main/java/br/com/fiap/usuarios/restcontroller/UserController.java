package br.com.fiap.usuarios.restcontroller;

import br.com.fiap.usuarios.entity.Account;
import br.com.fiap.usuarios.entity.User;
import br.com.fiap.usuarios.entity.vo.AccountVo;
import br.com.fiap.usuarios.entity.vo.UserVo;
import br.com.fiap.usuarios.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("User")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping( value="/create", consumes = "application/json")
  public ResponseEntity<Long> createUser(@RequestBody UserVo userVo) {
    User user = service.createUser(userVo);
    return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
  }

  @PostMapping(value = "/addAccount", consumes = "application/json")
  public ResponseEntity<Long> addAccountToUser(@RequestBody AccountVo accountVo) {
    Account account = service.addAccountToUser(accountVo);
    return ResponseEntity.status(HttpStatus.CREATED).body(account.getId());
  }

  @GetMapping(value = "byCpfCnpj", produces = "application/json")
  public ResponseEntity<UserVo> findByCpfCnpj(@RequestParam String cpfCnpj) {
    UserVo userVo = service.findUserbyCpfCnpj(cpfCnpj);
    return ResponseEntity.status(HttpStatus.OK).body(userVo);
  }
}
