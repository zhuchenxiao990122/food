package com.xw.project.controller.other;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xw.common.result.RestResponse;
import com.xw.common.result.ResultGenerator;
import com.xw.common.util.StringUtil;
import com.xw.project.entity.OthDonationCert;
import com.xw.project.service.other.OthDonationCertService;
import com.xw.project.vo.OthDonationCertVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.ZipFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dy
 * @since 2020-02-23
 */
@RestController
@Api(value = "捐赠证书")
@RequestMapping("/othDonationCert")
public class OthDonationCertController {
    @Autowired
    private OthDonationCertService othDonationCertService;

    @GetMapping(value = {"/generateSignature"})
    @ApiOperation("生成电子捐赠证书调用接口")
    public void generateSignature(String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            String path = othDonationCertService.generateSignature(id);
            if (StringUtil.isNotEmpty(path)) {
                response.setCharacterEncoding("UTF-8");
                request.setCharacterEncoding("UTF-8");
                // 获取文件的路径
                File image = new File(path);
                InputStream input = FileUtils.openInputStream(image);
                ServletOutputStream out = response.getOutputStream();
                int b = 0;
                byte[] buffer = new byte[1024];
                while ((b = input.read(buffer)) != -1) {
                    out.write(buffer, 0, b);
                }
                input.close();
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = {"/getDonationInfoList"})
    @ApiOperation("获取功德榜信息")
    public RestResponse<Page<OthDonationCert>> getDonationInfoList(String donor, int page, int size) {
        try {
            return ResultGenerator.genSuccessResult(othDonationCertService.getDonationInfoList(donor, page, size));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = {"/getDonationCertList"})
    @ApiOperation("获取已生成捐赠证书信息")
    public RestResponse<IPage<OthDonationCertVo>> getDonationCertList(String searchCriteria, @RequestParam(value = "applyDates", required = false) List<String> applyDates, Integer current, Integer size, String buildType) {
        try {
            return ResultGenerator.genSuccessResult(othDonationCertService.getDonationCertList(searchCriteria, applyDates, current, size, buildType));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/downLoadPdf")
    @ApiOperation("下载电子捐赠证书pdf文件")
    public void downLoadPdf(String id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //根据id下载文件
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = othDonationCertService.downLoadPdf(id);
        if (null != map) {
            File filePath = new File(map.get("path"));
            InputStream input = FileUtils.openInputStream(filePath);
            byte[] data = IOUtils.toByteArray(input);
            response.reset();
            response.setHeader("content-disposition",
                    "attachment;fileName=" + URLEncoder.encode(map.get("fileName") + ".pdf", "UTF-8")); // 设置响应的报头信息(中文问题解决办法)
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/pdf; charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
            IOUtils.closeQuietly(input);
            input.close();
        }

    }

    @RequestMapping("/certByexcelInput")
    @ApiOperation("批量手动导入excel生成捐赠证书")
    /*
     *buildType为0则捐款1捐物
     */
    public RestResponse<Map<String, Object>> certByexcelInput(@RequestParam(value = "file") MultipartFile multipartFile, String buildType) throws Exception {
        Map<String, Object> stringObjectMap = othDonationCertService.certByexcelInput(multipartFile, buildType);
        if (null != stringObjectMap) {
            return ResultGenerator.genSuccessResult(stringObjectMap);
        } else {
            return ResultGenerator.genFailResult("excel捐赠时间格式不对");
        }
    }

    @GetMapping(value = {"/updateSignature"})
    @ApiOperation("修改电子捐赠证书调用接口")
    public RestResponse<String> updateSignature(String id, String donor, String certCode, String donationProject, String donationAmount, String donationDate, String amount) {
        OthDonationCert othDonationCert = new OthDonationCert();
        othDonationCert.setDonor(donor);
        othDonationCert.setDonationProject(donationProject);
        othDonationCert.setId(id);
        if (null != donationAmount && "" != donationAmount) {
            othDonationCert.setDonationAmount(new BigDecimal(donationAmount));
        }
        othDonationCert.setDonationDate(donationDate);
        othDonationCert.setCertCode(certCode);
        othDonationCert.setAmount(amount);
        return ResultGenerator.genSuccessResult(othDonationCertService.updateSignature(othDonationCert));

    }

    @GetMapping(value = "/downLoadFileByCheckBox")
    @ResponseBody
    public void downLoadFileByCheckBox(@RequestParam(value = "list") List<String> list, HttpServletResponse response, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        String path = othDonationCertService.downLoadFileByCheckBox(list);
        File filePath = new File(path);
        InputStream input = FileUtils.openInputStream(filePath);
        byte[] data = IOUtils.toByteArray(input);
        response.reset();
        response.setHeader("content-disposition",
                "attachment;fileName=" + URLEncoder.encode("电子捐赠证书.zip", "UTF-8")); // 设置响应的报头信息(中文问题解决办法)
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/zip; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
        IOUtils.closeQuietly(input);
        input.close();
    }


    @GetMapping(value = "/downLoadFileZip")
    @ResponseBody
    public void downLoadFileZip(String path, HttpServletResponse response, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        File filePath = new File(path + ".zip");
        InputStream input = FileUtils.openInputStream(filePath);
        byte[] data = IOUtils.toByteArray(input);
        response.reset();
        response.setHeader("content-disposition",
                "attachment;fileName=" + URLEncoder.encode("电子捐赠证书.zip", "UTF-8")); // 设置响应的报头信息(中文问题解决办法)
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/zip; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
        IOUtils.closeQuietly(input);
        input.close();
    }

    @GetMapping(value = "/getZipCount")
    @ResponseBody
    public RestResponse<Integer> getZipCount(String path) throws Exception {
        return ResultGenerator.genSuccessResult(getFileAndDirectory(new File(path)));
    }

    public int getFileAndDirectory(File file) {
        int countFile = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                if (fileIndex.isDirectory()) {
                    getFileAndDirectory(fileIndex);
                } else {
                    countFile++;
                }
            }
        }
        return countFile;
    }
}
