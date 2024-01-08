package com.pblgllgs.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/*
 *
 * @author pblgl
 * Created on 08-01-2024
 *
 */
@Data
public class CustomerDto{
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The name of the customer should be between 5 and 30")
    private String name;
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email address should be valid value")
    private String email;
    @NotEmpty(message = "Email can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
