package br.com.fiap.usuarios.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_SYSTEM")
@NoArgsConstructor
public @Data
class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "CPF_CNPJ")
  private String cpfCnpj;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "BALANCE")
  private Double balance;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  private Set<Account> accounts = new HashSet<>();

  public void addAccount(Account account) {
    accounts.add(account);
  }

  @Builder
  private User(Long id,
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
