package com.pblgllgs.accounts.service.client;
/*
 *
 * @author pblgl
 * Created on 10-01-2024
 *
 */

import com.pblgllgs.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "cards"
)
public interface CardsFeignClient {

    @GetMapping(value = "/api/v1/cards",consumes = "application/json")
    ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);
}
