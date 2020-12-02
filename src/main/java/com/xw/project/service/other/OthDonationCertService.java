package com.xw.project.service.other;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.project.entity.OthDonationCert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xw.project.vo.OthDonationCertVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dy
 * @since 2020-02-23
 */
public interface OthDonationCertService extends IService<OthDonationCert> {

    String generateSignature(String id) throws  Exception;

    Page<OthDonationCert> getDonationInfoList(String donor, int page, int size)throws Exception;

    Map<String, String> downLoadPdf(String id);

    IPage<OthDonationCertVo> getDonationCertList(String searchCriteria, List<String> applyDates, Integer current, Integer size, String buildType);

    Map<String,Object> certByexcelInput(MultipartFile multipartFile, String buildType) throws Exception;

    String updateSignature(OthDonationCert othDonationCert);

    String downLoadFileByCheckBox(List<String> list);
}
