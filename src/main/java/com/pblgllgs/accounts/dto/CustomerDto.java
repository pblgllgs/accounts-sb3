package com.pblgllgs.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/*
 *
 * @author pblgl
 * Created on 08-01-2024
 *
 */
@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto{
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The name of the customer should be between 5 and 30")
    @Schema(
            description = "Name of the customer", example = "Pbl gllgs"
    )
    private String name;
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email address should be valid value")
    @Schema(
            description = "Email address of the customer", example = "pbl.gllgs@gmail.com"
    )
    private String email;
    @NotEmpty(message = "Email can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    private String mobileNumber;
    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;
}
