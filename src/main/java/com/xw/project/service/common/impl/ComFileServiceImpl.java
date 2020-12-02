package com.xw.project.service.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.core.constant.DocConstant;
import com.xw.project.dto.ComFileDto;
import com.xw.project.mapper.ComFileMapper;
import com.xw.project.service.common.ComFileService;
import com.xw.project.entity.ComFile;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ComFile服务类
 * </p>
 *
 * @author yuli
 * @since 2020-07-10
 */
@Service
public class ComFileServiceImpl extends ServiceImpl<ComFileMapper, ComFile> implements ComFileService {

    @Override
    public List<String> search(String id) {
        Map map = new HashMap();
        map.put("ref_id", id);
        map.put("del_flag", "0");
        List<ComFile> comFiles = baseMapper.selectByMap(map);
        List<String> fileIds = new ArrayList<>();
        if (null == comFiles) {
            for (ComFile comFile : comFiles) {
                fileIds.add(comFile.getFileId());
            }
            return fileIds;
        }
        return null;
    }

    @Override
    public RestResponse<String> save(ComFileDto comFileDto) {
        //先删除所有数据库已存的数据，防止重复保存
        Map map = new HashMap();
        map.put("ref_id", comFileDto.getRefId());
        baseMapper.deleteByMap(map);
        //循环插入到数据库表
        for (String fileId : comFileDto.getFileIds()) {
            try {
                ComFile comFile = new ComFile();
                comFile.setFileId(fileId);
                comFile.setRefId(comFileDto.getRefId());
                baseMapper.insert(comFile);
            } catch (Exception e) {
                return ResultGenerator.genFailResult(DocConstant.add_error);
            }
        }
        return ResultGenerator.genSuccessResult(DocConstant.add_success);
    }


    @Override
    public RestResponse<String> delete(String id) {
        try {
            baseMapper.deleteById(id);
        } catch (Exception e) {
            return ResultGenerator.genFailResult(DocConstant.delete_error);
        }
        return ResultGenerator.genSuccessResult(DocConstant.delete_success);
    }


}