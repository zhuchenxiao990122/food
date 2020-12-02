package com.xw.project.service.common;

import com.xw.common.result.RestResponse;
import com.xw.project.dto.ComFileDto;

import java.util.List;

public interface ComFileService {
    /**
     * 查询已存储文件
     *
     * @param id
     * @return
     */
    List<String> search(String id);

    /**
     * 新增、修改（上传文件id集合以及关联id）
     *
     * @param comFileDto
     * @return
     */

    RestResponse<String> save(ComFileDto comFileDto);

    /**
     * 删除文件
     *
     * @param id
     * @return
     */

    RestResponse<String> delete(String id);

}