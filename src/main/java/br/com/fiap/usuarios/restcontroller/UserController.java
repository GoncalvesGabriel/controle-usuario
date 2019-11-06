package br.com.fiap.usuarios.restcontroller;

import br.com.fiap.usuarios.entity.Account;
import br.com.fiap.usuarios.entity.User;
import br.com.fiap.usuarios.entity.vo.AccountVo;
import br.com.fiap.usuarios.entity.vo.UserVo;
import br.com.fiap.usuarios.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("API para controle de usuário")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Criação de um novo usuário", consumes = "application/json", httpMethod = "POST")
    @ApiResponse(code = 201, message = "Operação realizada com sucesso")
    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<Long> createUser(
        @RequestBody
        @ApiParam(name = "user", value = "Dados do usuário") UserVo userVo) {
        User user = service.createUser(userVo);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    @ApiOperation(value = "Adição de uma nova conta para o usuário", consumes = "application/json", httpMethod = "POST")
    @ApiResponse(code = 201, message = "Operação realizada com sucesso")
    @PostMapping(value = "/addAccount", consumes = "application/json")
    public ResponseEntity<Long> addAccountToUser(
        @RequestBody
        @ApiParam(name = "account", value = "Dados da conta do usuário") AccountVo accountVo) {
        Account account = service.addAccountToUser(accountVo);
        return ResponseEntity.status(HttpStatus.CREATED).body(account.getId());
    }

    @ApiOperation(value = "Encontra um usuário pelo seu CPF ou CNPJ", consumes = "application/json", httpMethod = "GET")
    @ApiResponse(code = 200, message = "Busca realizada com sucesso")
    @GetMapping(value = "byCpfCnpj", produces = "application/json")
    public ResponseEntity<UserVo> findByCpfCnpj(
        @RequestParam
        @ApiParam(value = "CPF ou CNPJ referência para a busca")
            String cpfCnpj) {
        UserVo userVo = service.findUserbyCpfCnpj(cpfCnpj);
        return ResponseEntity.status(HttpStatus.OK).body(userVo);
    }
}
