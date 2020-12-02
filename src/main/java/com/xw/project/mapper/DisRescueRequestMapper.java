package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.dto.DisRescueRequestDto;
import com.xw.project.entity.DisRescueRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.DisRescueRequestVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoshuhao
 * @since 2020-06-24
 */
@Component
public interface DisRescueRequestMapper extends BaseMapper<DisRescueRequest> {

    void addDisRescueRequest(@Param(value = "disRescueRequests") List<DisRescueRequest> disRescueRequests);

    void deleteDisRescueRequest(@Param(value = "id") String id);

    IPage<DisRescueRequestVo> search(@Param(value = "page") Page<DisRescueRequestVo> page, @Param(value = "disRescueRequestDto") DisRescueRequestDto disRescueRequestDto);

    List<String> selectPublishCode(@Param(value = "publishOrg") String publishOrg);

    String selectMaxApplyNumber(@Param(value = "publishId") String publishId);
}
