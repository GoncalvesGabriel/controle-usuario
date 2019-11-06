package br.com.fiap.usuarios.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BANK")
@NoArgsConstructor
public @Data class Bank {

  @Id
  @Column(name = "BANK_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "NUMBER")
  private String number;

  @Builder
  private Bank(Long id,
               String name,
               String number) {
    this.id = id;
    this.name = name;
    this.number = number;
  }

}
