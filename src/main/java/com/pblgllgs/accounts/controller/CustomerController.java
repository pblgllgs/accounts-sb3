package com.pblgllgs.accounts.controller;
/*
 *
 * @author pblgl
 * Created on 10-01-2024
 *
 */

import com.pblgllgs.accounts.dto.CustomerDetailsDto;
import com.pblgllgs.accounts.dto.ErrorResponseDto;
import com.pblgllgs.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Customer Controller",
        description = "CRUD RESTFUL API for Customer client resources"
)
public class CustomerController {

    private final ICustomerService iCustomerService;

    @Operation(
            summary = "Fetch Customer details REST API",
            description = "REST API to fetch a Customer details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
            String mobileNumber
    ){
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
