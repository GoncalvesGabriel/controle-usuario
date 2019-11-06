package br.com.fiap.usuarios.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApiModel("Account")
public @Data class AccountVo {

    @ApiModelProperty(value = "Número da conta", example = "\"12345\"")
    private String number;

    @ApiModelProperty(value = "Dígito do número da conta", example = "\"1\"")
    private String digit;

    @ApiModelProperty(value = "Número da agência", example = "\"1234\"")
    private String agencyNumber;

    @ApiModelProperty(value = "Dígito do número da agência", example = "\"1\"")
    private String agencyDigit;

    @ApiModelProperty(value = "Número do banco", example = "\"204\"")
    private String bankNumber;

    @ApiModelProperty(value = "ID do usuário em que a conta será inserida")
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
