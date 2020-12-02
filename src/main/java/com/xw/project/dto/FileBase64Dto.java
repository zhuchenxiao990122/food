package com.xw.project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 新闻发布-参数类
 * @author weiLiang
 * @since 2020-06-30
 */
@Data
public class FileBase64Dto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileName;
    private String base64;
}
