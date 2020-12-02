package com.xw.project.dto;

import com.xw.project.util.PageHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrgPirmaryDto extends PageHelper {
    String parentId;
    String orgCoding;
    String fullName;
}
