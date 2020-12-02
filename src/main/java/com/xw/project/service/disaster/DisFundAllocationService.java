package com.xw.project.service.disaster;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xw.common.result.RestResponse;
import com.xw.project.dto.DisFundAllocationDto;
import com.xw.project.dto.DisFundAllocationFileDto;
import com.xw.project.entity.DisDonationFund;
import com.xw.project.entity.DisFundAllocation;

import java.math.BigDecimal;
import java.util.List;

public interface DisFundAllocationService {

    IPage<DisFundAllocation> page(DisFundAllocationDto disFundAllocationDto);

    void add(DisFundAllocation disFundAllocation);

    void update(DisFundAllocation disFundAllocation);

    void delete(String id);

    RestResponse<String> save(DisFundAllocationFileDto disFundAllocationFileDto);

    RestResponse<List<DisDonationFund>> listFund(BigDecimal amount, String projectId);
}