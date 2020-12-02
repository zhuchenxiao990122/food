package com.xw.project.mapper;

import com.xw.project.entity.NewsRelease;
import com.xw.project.vo.NewsReleaseVo;
import com.xw.system.entity.SysFileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  新闻发布-Mapper 接口
 * </p>
 *
 * @author weiLiang
 * @since 2020-07-1
 */
@Component
public interface NewsReleaseMapper {
    Integer insert(@Param("sysfileInfo") SysFileInfo sysfileInfo);

    Integer addNews(@Param("id") String id, @Param("newsRelease") NewsRelease newsRelease, @Param("ids") String ids);

    NewsReleaseVo selectNewsById(@Param("id") String id);

    List<NewsReleaseVo> listFiles();
}
