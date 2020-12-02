package com.xw.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;

/**
 * <p>
 *
 * </p>
 *
 * @author yuli
 * @since 2020-07-10
 */
@TableName("com_file")
public class ComFile extends BasePlusEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 上报组织
     */
    private String refId;
    /**
     * 分类
     */
    private String categories;
    /**
     * 上报编号
     */
    private String fileId;


    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public static final String PROJECT_ID = "project_id";

    public static final String FILE_ID = "file_id";

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
