package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.SysActivityDto;
import com.xw.project.entity.SysActivity;
import com.xw.project.vo.SysActivityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface SysActivityMapper extends BaseMapper<SysActivity>{
       IPage<SysActivityVo> search( @Param("page")Page<SysActivityVo> page,
                                   @Param("sysActivityDto") SysActivityDto sysActivityDto);
       List<SysActivity> list(@Param("activityName")String activityName);
}
