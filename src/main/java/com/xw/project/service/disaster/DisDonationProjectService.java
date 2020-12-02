package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisDonationProjectDto;
import com.xw.project.entity.DisDonationProject;
import com.xw.project.vo.DisDonationProjectVo;

import java.util.List;
import java.util.Map;

public interface DisDonationProjectService {

    IPage<DisDonationProjectVo> page(DisDonationProjectDto disDonationProjectDto);

    RestResponse add(DisDonationProject disDonationProject);

    void update(DisDonationProject disDonationProject);

    RestResponse<String> delete(String id);

    RestResponse<String> applyStart(String disDonationProject);

    IPage<DisDonationProjectVo> pageApprovalTask(int current, int size);

    RestResponse approval(String id, String state, String comment);

    RestResponse<String> projectPublish(String id);

    RestResponse<IPage<DisDonationProject>> cancelProject(String id);

    IPage<DisDonationProjectVo> pagePublish(int current, int size);

    IPage<DisDonationProjectVo> pageApply(DisDonationProjectDto disDonationProjectDto);

    List<Map<String,String>> fundList(String orgId);

    List<Map<String,String>> materialList(String orgId);
}