package com.xw.project.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuli
 * @since 2020-07-06
 */
public class ComFileDto {

    @NotNull
    private List<String> fileIds;
    @NotNull
    private String refId;

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}