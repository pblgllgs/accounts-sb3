package com.pblgllgs.accounts.service.client;
/*
 *
 * @author pblgl
 * Created on 10-01-2024
 *
 */

import com.pblgllgs.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/v1/loans", consumes = "application/json")
    ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestHeader("correlation-id") String correlationId,
            @RequestParam String mobileNumber);
}
