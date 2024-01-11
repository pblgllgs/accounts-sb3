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
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param mobileNumber
     * @return customer details, cards and loans
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId) {
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

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
