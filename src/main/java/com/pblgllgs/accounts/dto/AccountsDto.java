package com.pblgllgs.accounts.dto;
/*
 *
 * @author pblgl
 * Created on 08-01-2024
 *
 */


import lombok.Data;

@Data
public class AccountsDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
