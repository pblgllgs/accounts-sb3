package com.pblgllgs.accounts.dto;
/*
 *
 * @author pblgl
 * Created on 08-01-2024
 *
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseDto {

    private String statusCode;
    private String statusMsg;

}
