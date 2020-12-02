package com.xw.project.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.SysActivityDto;
import com.xw.project.entity.SysActivity;
import com.xw.project.vo.SysActivityVo;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

public interface SysActivityService extends IService<SysActivity>{
    RestResponse<IPage<SysActivityVo>> listActivity(SysActivityDto sysActivityDto);
    RestResponse<String> addSysActivity(@Valid @RequestBody SysActivityVo sysActivityVo);
    RestResponse<SysActivityVo> getSysActivity(String id);
    RestResponse<String> updateSysActivity(@Valid @RequestBody SysActivityVo sysActivityVo);
    RestResponse<String> deleteSysActivity(String id);
}
