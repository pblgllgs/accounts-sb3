package com.pblgllgs.accounts.service.client;
/*
 *
 * @author pblgl
 * Created on 12-01-2024
 *
 */

import com.pblgllgs.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
}