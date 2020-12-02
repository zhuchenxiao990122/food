package com.xw.project.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ApproveDto {
    @NotEmpty
    private String id;
    @NotEmpty
    private String memberCode;
    @Pattern(regexp = "^[0,1]$")
    private String approve;
}
