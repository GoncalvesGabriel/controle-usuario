package br.com.fiap.usuarios.entity.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class UserVo {

  private Long id;

  private String name;

  private String cpfCnpj;

  private String email;

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
