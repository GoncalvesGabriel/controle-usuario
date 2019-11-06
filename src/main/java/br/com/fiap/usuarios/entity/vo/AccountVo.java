package br.com.fiap.usuarios.entity.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class AccountVo {

    private String number;

    private String digit;

    private String agencyNumber;

    private String agencyDigit;

    private String bankNumber;

    private Long userId;

    @Builder
    private AccountVo(String number,
                      String digit,
                      String agencyNumber,
                      String agencyDigit,
                      String bankNumber,
                      Long userId) {
        this.number = number;
        this.digit = digit;
        this.agencyNumber = agencyNumber;
        this.agencyDigit = agencyDigit;
        this.bankNumber = bankNumber;
        this.userId = userId;
    }
}
