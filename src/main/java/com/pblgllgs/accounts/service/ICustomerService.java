package com.pblgllgs.accounts.service;

import com.pblgllgs.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber
     * @return customer details, cards and loans
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
