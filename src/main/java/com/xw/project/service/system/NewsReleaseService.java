package com.xw.project.service.system;

import com.xw.project.dto.FileBase64Dto;
import com.xw.project.entity.NewsRelease;
import com.xw.project.vo.NewsReleaseVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 新闻发布-保存本地 服务类
 * </p>
 *
 * @author weiLiang
 * @since 2020-6-30
 */
public interface NewsReleaseService{
    List<String> saveFiles(MultipartFile[] fileList);

    List<String> saveBase64Files(List<FileBase64Dto> file);

    Integer addNews(NewsRelease newsRelease);

    List<NewsReleaseVo> listFiles();

    Object take(String id, HttpServletResponse response);

}
