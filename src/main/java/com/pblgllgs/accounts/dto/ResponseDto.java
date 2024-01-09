package com.pblgllgs.accounts.dto;
/*
 *
 * @author pblgl
 * Created on 08-01-2024
 *
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDto {

    @Schema(description = "Status code")
    private String statusCode;
    @Schema(description = "Description of status message")
    private String statusMsg;

}
