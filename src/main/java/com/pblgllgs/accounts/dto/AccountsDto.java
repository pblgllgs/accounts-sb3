package com.pblgllgs.accounts.dto;
/*
 *
 * @author pblgl
 * Created on 08-01-2024
 *
 */


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {
    @NotEmpty(message = "Email can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    @Schema(
            description = "Account Number account", example = "3454433243"
    )
    private Long accountNumber;
    @NotEmpty(message = "Account type can not be null or empty")
    @Schema(
            description = "Account type account", example = "Savings"
    )
    private String accountType;
    @NotEmpty(message = "Branch address can not be null or empty")
    @Schema(
            description = "Branch address", example = "123 NewYork"
    )
    private String branchAddress;
}
