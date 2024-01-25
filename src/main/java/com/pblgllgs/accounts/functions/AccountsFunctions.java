package com.pblgllgs.accounts.functions;

import com.pblgllgs.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/*
 *
 * @author pblgl
 * Created on 25-01-2024
 *
 */
@Configuration
public class AccountsFunctions {

    public static final Logger log = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService iAccountsService) {
        return accountNumber -> {
            log.info("Updating communication status for the account number: " + accountNumber.toString());
            iAccountsService.updateCommunicationStatus(accountNumber);
        };
    }
}
