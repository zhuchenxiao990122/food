package com.xw.project.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 功能描述：物资类别 Vo
 * @author weiLiang
 */
@Data
public class DisMaterialCategorysVo {

    private String id;

    private String name;

    private String code;

    private String parentId;

    private String parentName;

    private String parentIds;

    private String remarks;

    private List<DisMaterialCategorysVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<DisMaterialCategorysVo> getChildren() {
        return children;
    }

    public void setChildren(List<DisMaterialCategorysVo> children) {
        this.children = children;
    }
}
