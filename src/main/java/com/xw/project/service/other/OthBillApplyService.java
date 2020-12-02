package com.xw.project.service.other;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.project.entity.OthBillApply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.OthBillApplySearchVo;
import com.xw.project.vo.OthBillApplyVo;

import java.util.List;

public interface OthBillApplyService extends IService<OthBillApply> {

    //IPage<OthBillApplyVo> listBillApply(List<String> applyDate, String phoneMumber, String donationBillTitle, String donationEvidence, Integer current, Integer size);

    int addBillApply(OthBillApply othBillApply);

    void billApplyExport(List<String> applyDate) throws Exception;

    IPage<OthBillApplyVo> listBillApply(List<String> applyDate, OthBillApplySearchVo othBillApplySearchVo);
}
