package com.xw.project.dto;

import com.xw.project.util.PageHelper;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RedCrossMemberDto extends PageHelper {

    private String memberName;
    private String orgId;

    private String memberCode;
    @Pattern(regexp = "^[1,2,3]$")
    private String memberType;
    @NotEmpty
    @Pattern(regexp = "^[0,1]{2}$")
    private String applyType;


}
