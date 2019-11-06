package br.com.fiap.usuarios.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApiModel("User")
public @Data class UserVo {

  @ApiModelProperty("ID do usuário")
  private Long id;

  @ApiModelProperty(value = "Nome do usuário", example = "Gabriel")
  private String name;

  @ApiModelProperty(value = "CPF ou CNPJ do usuário", example = "\"12345678911\"")
  private String cpfCnpj;

  @ApiModelProperty(value = "E-mail para cadastro", example = "gabriel.goncalves@gmail.com")
  private String email;

  @ApiModelProperty("Saldo total do usuário")
  private Double balance;

  @Builder
  private UserVo(Long id,
                 String name,
                 String cpfCnpj,
                 String email,
                 Double balance) {
    this.id = id;
    this.name = name;
    this.cpfCnpj = cpfCnpj;
    this.email = email;
    this.balance = balance;
  }

}
