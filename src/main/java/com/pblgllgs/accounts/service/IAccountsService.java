package com.pblgllgs.accounts.service;

import com.pblgllgs.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto CustomerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return customerDto details
     */
    CustomerDto fetchAccountDetail(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return boolean, update is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return boolean, delete is successful or not
     */
    boolean deleteAccount(String mobileNumber);

    boolean updateCommunicationStatus(Long accountNumber);
}
