package br.com.fiap.usuarios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ACCOUNT")
@NoArgsConstructor
public @Data class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ACCOUNT_ID")
  private Long id;

  @Column(name = "NUMBER")
  private String number;

  @Column(name = "DIGIT")
  private String digit;

  @Column(name = "AGENCY_NUMBER")
  private String agencyNumber;

  @Column(name = "AGENCY_DIGIT")
  private String agencyDigit;

  @ManyToOne
  @JoinColumn(name = "BANK_ID")
  private Bank bank;

  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  @Builder
  private Account(Long id,
                  String number,
                  String digit,
                  String agencyNumber,
                  String agencyDigit,
                  Bank bank,
                  User user) {
    this.id = id;
    this.number = number;
    this.digit = digit;
    this.agencyNumber = agencyNumber;
    this.agencyDigit = agencyDigit;
    this.bank = bank;
    this.user = user;
  }

}
