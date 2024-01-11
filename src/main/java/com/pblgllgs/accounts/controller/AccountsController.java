package com.pblgllgs.accounts.controller;
/*
 *
 * @author pblgl
 * Created on 08-01-2024
 *
 */

import com.pblgllgs.accounts.constants.AccountsConstants;
import com.pblgllgs.accounts.dto.CustomerDto;
import com.pblgllgs.accounts.dto.ErrorResponseDto;
import com.pblgllgs.accounts.dto.ResponseDto;
import com.pblgllgs.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Accounts Controller",
        description = "CRUD RESTFUL API for Accounts resources"
)
public class AccountsController {

    private final IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account REST API",
            description = "RESET API to create a new Customer & Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account REST API",
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
    @GetMapping
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
            String mobileNumber
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(iAccountsService.fetchAccountDetail(mobileNumber));
    }

    @Operation(
            summary = "Update Account REST API",
            description = "RESET API to update a Customer & Account based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(
                            AccountsConstants.STATUS_200,
                            AccountsConstants.MESSAGE_200
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseDto(
                            AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_UPDATE)
            );
        }
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "RESET API to delete a Customer & Account based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteAccount(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
            String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(
                            AccountsConstants.STATUS_200,
                            AccountsConstants.MESSAGE_200
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new ResponseDto(
                            AccountsConstants.STATUS_417,
                            AccountsConstants.MESSAGE_417_DELETE)
            );
        }
    }
}
