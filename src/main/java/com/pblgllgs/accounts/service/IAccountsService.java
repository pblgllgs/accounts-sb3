package com.pblgllgs.accounts.service;

import com.pblgllgs.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto CustomerDto
     */
    void createAccount(CustomerDto customerDto);
}
