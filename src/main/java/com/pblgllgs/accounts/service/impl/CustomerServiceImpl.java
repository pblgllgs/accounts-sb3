package com.pblgllgs.accounts.service.impl;
/*
 *
 * @author pblgl
 * Created on 10-01-2024
 *
 */

import com.pblgllgs.accounts.dto.*;
import com.pblgllgs.accounts.entity.Accounts;
import com.pblgllgs.accounts.entity.Customer;
import com.pblgllgs.accounts.exception.ResourceNotFoundException;
import com.pblgllgs.accounts.mapper.AccountsMapper;
import com.pblgllgs.accounts.mapper.CustomerMapper;
import com.pblgllgs.accounts.repository.AccountsRepository;
import com.pblgllgs.accounts.repository.CustomerRepository;
import com.pblgllgs.accounts.service.ICustomerService;
import com.pblgllgs.accounts.service.client.CardsFeignClient;
import com.pblgllgs.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param mobileNumber
     * @return customer details, cards and loans
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Customer",
                        "mobileNumber",
                        mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId",customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
