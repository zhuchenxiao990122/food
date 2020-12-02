package com.xw.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.OthDonationCert;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xw.project.vo.OthDonationCertVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dy
 * @since 2020-03-23
 */
public interface OthDonationCertMapper extends BaseMapper<OthDonationCert> {

    int getMaxCode(String donationDate);

    IPage<OthDonationCertVo> getDonationCertList(Page<OthDonationCert> pageInfo, @Param("searchCriteria") String searchCriteria, @Param("startDate") String startDate, @Param("endDate") String endDate,@Param("buildType") String buildType);

    void batchInsert(List<OthDonationCert> othDonationCerts);
}
