package com.xw.project.mapper;

import com.xw.project.entity.SysExcelDef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface SysExcelDefMapper extends BaseMapper<SysExcelDef> {

    List<Map<String, String>> selectByFactorList(@Param("fileTemplate") String fileTemplate, @Param("fieldType") String fieldType);
}
