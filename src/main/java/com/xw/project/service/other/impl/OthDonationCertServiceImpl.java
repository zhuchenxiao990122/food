package com.xw.project.service.other.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.util.FileUtil;
import com.xw.common.util.StringUtil;
import com.xw.common.util.UUIDGenerator;
import com.xw.common.util.WordUtil;
import com.xw.project.entity.MeritList;
import com.xw.project.entity.OthDonationCert;
import com.xw.project.mapper.OthDonationCertMapper;
import com.xw.project.service.other.MeritListConvertService;
import com.xw.project.service.other.OthDonationCertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.util.OthDonationCertExcel;
import com.xw.project.util.SignHelper;
import com.xw.project.util.Word2Pdf;
import com.xw.project.vo.MeritListConvertVo;
import com.xw.project.vo.OthDonationCertVo;
import com.xw.system.entity.SysFileInfo;
import com.xw.system.mapper.SysFileInfoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dy
 * @since 2020-02-23
 */
@Service
public class OthDonationCertServiceImpl extends ServiceImpl<OthDonationCertMapper, OthDonationCert> implements OthDonationCertService {
    @Autowired
    private OthDonationCertMapper othDonationCertMapper;
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;
    @Autowired
    private MeritListConvertService meritListConvertService;

    @Value("${app.constant.mould-save-path}")
    private String mouldSavePath;
    @Value("${app.constant.file-save-path}")
    private String fileSavePath;

    private String sealCode = "33000000027897";
  /*  private String accountId = SignHelper.doAppLogin(sealCode);
    private int sealId = SignHelper.getSealInfo(accountId);*/

    private String accountId = null;
    private int sealId = 0;
    @Override
    public String generateSignature(String id) throws Exception {
        //1.判断数据库中是否存在,存在则直接返回pdf,否则生成
        QueryWrapper<OthDonationCert> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("id", id);
        List<OthDonationCert> list = othDonationCertMapper.selectList(objectQueryWrapper);
        //说明捐赠证书不存在，需生成
        if (list.size() == 0) {
            //为了防止篡改，必须根据id再去请求一次
            MeritListConvertVo meritLists = meritListConvertService.convert(0, 1, "", id);
            if (null != meritLists && meritLists.getList().size() > 0) {
                MeritList meritList = meritLists.getList().get(0);
                OthDonationCert othDonationCert = new OthDonationCert();
                othDonationCert.setId(id);
                //返回数据金额可能带有，分割
                String donationAmount = meritList.getJe().replaceAll(",", "").trim();
                othDonationCert.setDonationAmount(new BigDecimal(donationAmount));
                //将（XX）数据做处理
                othDonationCert.setDonationProject(StringUtils.substringBeforeLast(meritList.getXm(), "（").trim());
                //微信单号或者账号前面有`
                othDonationCert.setDonor(meritList.getJzr().replaceAll("`", "").trim());
                othDonationCert.setDonationDate(meritList.getJzsj());
                //根据获取到的信息生成证书
                String path = generateCert(othDonationCert, "0", "0");
                return path;
            } else {
                //从功德榜上获取不到信息
                return null;
            }
        } else {
            QueryWrapper<SysFileInfo> sysFileInfoQueryWrapper = new QueryWrapper<>();
            sysFileInfoQueryWrapper.eq("ref_id", id);
            sysFileInfoQueryWrapper.eq("file_type", ".jpg");
            List<SysFileInfo> sysFileInfos = sysFileInfoMapper.selectList(sysFileInfoQueryWrapper);
            return sysFileInfos.get(0).getFilePath();
        }
    }

    @Override
    public Page<OthDonationCert> getDonationInfoList(String donor, int page, int size) throws Exception {
        //开始查询的起始数据
        int frist = (page - 1) * size;
        List<OthDonationCert> othDonationCerts = new ArrayList<>();
        //进行数据搜索
        MeritListConvertVo meritLists = meritListConvertService.convert(frist, size, donor, "");
        //将返回数据封装为前端实际所需数据
        Page<OthDonationCert> othDonationCertPages = new Page<OthDonationCert>();
        if (null != meritLists) {
            Iterator<MeritList> iterator = meritLists.getList().iterator();
            while (iterator.hasNext()) {
                MeritList meritList = iterator.next();
                OthDonationCert othDonationCert = new OthDonationCert();
                othDonationCert.setId(meritList.getId());
                //返回数据金额可能带有，分割
                String donationAmount = meritList.getJe().replaceAll(",", "").trim();
                othDonationCert.setDonationAmount(new BigDecimal(donationAmount));
                //将（XX）数据做处理
                othDonationCert.setDonationProject(StringUtils.substringBeforeLast(meritList.getXm(), "（").trim());
                //微信单号或者账号前面有`
                othDonationCert.setDonor(meritList.getJzr().replaceAll("`", "").trim());
                othDonationCert.setDonationDate(meritList.getJzsj());
                othDonationCerts.add(othDonationCert);
            }
            //返回数据代入总数量
            othDonationCertPages.setTotal(meritLists.getTotal());
            othDonationCertPages.setRecords(othDonationCerts);
            othDonationCertPages.setSize(size);
            return othDonationCertPages;
        } else {
            othDonationCertPages.setRecords(null);
            return othDonationCertPages;
        }
    }

    @Override
    public Map<String, String> downLoadPdf(String id) {
        //从文件里面找
        QueryWrapper<SysFileInfo> sysFileInfoQueryWrapper = new QueryWrapper<>();
        sysFileInfoQueryWrapper.eq("ref_id", id);
        sysFileInfoQueryWrapper.eq("file_type", ".pdf");
        List<SysFileInfo> sysFileInfos = sysFileInfoMapper.selectList(sysFileInfoQueryWrapper);
        if (sysFileInfos.size() > 0) {
            String refId = sysFileInfos.get(0).getRefId();
            QueryWrapper<OthDonationCert> othDonationCertQueryWrapper = new QueryWrapper<>();
            othDonationCertQueryWrapper.eq("id", refId);
            List<OthDonationCert> othDonationCerts = othDonationCertMapper.selectList(othDonationCertQueryWrapper);

            Map<String, String> map = new HashMap<>();
            map.put("path", sysFileInfos.get(0).getFilePath());
            map.put("fileName", othDonationCerts.get(0).getDonor() + "_" + othDonationCerts.get(0).getCertCode());
            return map;
        } else {
            return null;
        }
    }

    @Override
    public IPage<OthDonationCertVo> getDonationCertList(String searchCriteria, List<String> applyDates, Integer current, Integer size, String buildType) {
        Page<OthDonationCert> pageInfo = new Page((long) current, (long) size);
        //收缩条件
        if (StringUtil.isNotEmpty(searchCriteria)) {
            searchCriteria = searchCriteria.trim();
        }
        String startDate = null;
        String endDate = null;
        if (null != applyDates && applyDates.size() > 0) {
            startDate = applyDates.get(0);
            endDate = applyDates.get(1);
        }
        IPage<OthDonationCertVo> othDonationCertPages = othDonationCertMapper.getDonationCertList(pageInfo, searchCriteria, startDate, endDate, buildType);
        return othDonationCertPages;
    }

    @Override

    public Map<String, Object> certByexcelInput(MultipartFile multipartFile, String buildType) throws Exception {

        OthDonationCertExcel othDonationCertExcel = new OthDonationCertExcel();
        //检查excel并插入数据
        List<OthDonationCert> othDonationCerts = othDonationCertExcel.checkExcel(multipartFile.getInputStream(), buildType);
        String uuid = UUIDGenerator.getUUID();
        String zipPath = fileSavePath + "zip/";
        String result = zipPath + uuid;
        if (!new File(result + "/").exists()) {
            new File(result + "/").mkdirs();//创建目录
        }
        if (null != othDonationCerts) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        certByexcel(othDonationCerts, zipPath, uuid, buildType);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            Map<String, Object> map = new HashMap<>();
            map.put("size", othDonationCerts.size());
            map.put("path", result);
            return map;
        } else {
            return null;
        }
    }

    @Override
    public String updateSignature(OthDonationCert othDonationCert) {
        String path = null;
        try {
            if (null != othDonationCert.getDonationAmount()) {
                path = generateCert(othDonationCert, "0", "0");
            } else {
                path = generateCert(othDonationCert, "0", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;

    }

    @Override
    public String downLoadFileByCheckBox(List<String> list) {
        QueryWrapper<SysFileInfo> sysFileInfoQueryWrapper = new QueryWrapper<>();
        sysFileInfoQueryWrapper.in("ref_id", list);
        sysFileInfoQueryWrapper.eq("file_type", ".pdf");
        List<SysFileInfo> sysFileInfos = sysFileInfoMapper.selectList(sysFileInfoQueryWrapper);
        Iterator<SysFileInfo> iterator = sysFileInfos.iterator();
        String uuid = UUIDGenerator.getUUID();
        String zip = fileSavePath + "zip/";
        String zipPath = zip + uuid + "/";
        if (!new File(zipPath).exists()) {
            new File(zipPath).mkdirs();//创建目录
        }
        QueryWrapper<OthDonationCert> othDonationCertQueryWrapper = new QueryWrapper<>();
        othDonationCertQueryWrapper.in("id", list);
        List<OthDonationCert> othDonationCerts = othDonationCertMapper.selectList(othDonationCertQueryWrapper);
        //主要是换掉证书名称，以便用户查找
        while (iterator.hasNext()) {
            SysFileInfo next = iterator.next();
            String filePath = next.getFilePath();
            String refId = next.getRefId();
            for (OthDonationCert othDonationCert : othDonationCerts) {
                String pdfName = othDonationCert.getDonor() + "_" + othDonationCert.getCertCode() + ".pdf";
                if (othDonationCert.getId().equals(refId)) {
                    try {
                        Files.copy(new File(filePath).toPath(), new File(zipPath + pdfName).toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //文件打包
        FileUtil.fileToZip(zipPath, zip, uuid);
        return zip + uuid + ".zip";
    }

    /*
     *type表示手动还是自动（0自动生成，返回图片地址1导入生成，返回pdf），buildType表示捐款还是捐物（0捐款，1捐物）
     */
    public String generateCert(OthDonationCert othDonationCert, String type, String buildType) throws Exception {
        String certMould = "donationCert.ftl";
        othDonationCert.setBuildType("0");
        if ("1".equals(buildType)) {
            certMould = "donationMaterialCert.ftl";
            othDonationCert.setBuildType("1");
        }
        //填充word
       /* ClassPathResource resource = new ClassPathResource("mould/" + certMould);
        String filePath = resource.getFile().getPath();
        String path1 = StringUtils.substringBefore(filePath, certMould);*/
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> beanMap = BeanMap.create(othDonationCert);
        map.putAll(beanMap);
        //插入年月日
        String donationDate = othDonationCert.getDonationDate();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日");
        map.put("donationDate", dateformat.format(new SimpleDateFormat("yyyy-MM-dd").parse(donationDate)));

        //插入编号
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String certCode = null;
        if (StringUtil.isEmpty(map.get("certCode"))) {
            //根据捐献日期从数据库中查出当前最大编号
            int count = othDonationCertMapper.getMaxCode(donationDate);
            certCode = formatter.format(new SimpleDateFormat("yyyy-MM-dd").parse(donationDate)) + String.format("%04d", count + 1);
            map.put("certCode", certCode);
        }
        String uuid = UUIDGenerator.getUUID();

        if ("1".equals(type)) {
            uuid = othDonationCert.getDonor() + "_" + certCode;
        }
        //word转pdf
        WordUtil exportMyWord = new WordUtil();

        String savePath = fileSavePath + "word/" + uuid + ".docx";
        exportMyWord.createWord(map, savePath, mouldSavePath, certMould);
        String pdfPath = fileSavePath + "pdf/";
        if (!new File(pdfPath).exists()) {
            new File(pdfPath).mkdirs();//创建目录
        }
        pdfPath = pdfPath + uuid + ".pdf";
        Word2Pdf.word2pdf(savePath, pdfPath);

        // 签署后文件
        String signedPdfPath = fileSavePath + "signPdf/";
        if (!new File(signedPdfPath).exists()) {
            new File(signedPdfPath).mkdirs();//创建目录
        }
        signedPdfPath = signedPdfPath + uuid + ".pdf";
        // 签署
        int errCode = SignHelper.doSign(pdfPath, signedPdfPath, accountId, sealId);
        if (0 == errCode) {
            //图片存储路径
            String imgPath = fileSavePath + "jpg/";
            if (!new File(imgPath).exists()) {
                new File(imgPath).mkdirs();//创建目录
            }
            String imgPathFile = imgPath + uuid + ".jpg";
            FileUtil.pdfToImg(signedPdfPath, imgPath + uuid + ".jpg");
            //插入数据库
            if (StringUtil.isNotEmpty(othDonationCert.getCertCode())) {
                othDonationCertMapper.updateById(othDonationCert);
                QueryWrapper<SysFileInfo> sysFileInfoQueryWrapper = new QueryWrapper<>();
                sysFileInfoQueryWrapper.eq("ref_id", othDonationCert.getId());
                List<SysFileInfo> sysFileInfos = sysFileInfoMapper.selectList(sysFileInfoQueryWrapper);
                Iterator<SysFileInfo> iterator = sysFileInfos.iterator();
                while (iterator.hasNext()) {
                    SysFileInfo next = iterator.next();
                    if (".jpg".equals(next.getFileType())) {
                        next.setFilePath(imgPathFile);
                        sysFileInfoMapper.updateById(next);

                    } else {
                        next.setFilePath(signedPdfPath);
                        sysFileInfoMapper.updateById(next);
                    }
                }
            } else {
                othDonationCert.setApplyTime(new Date());
                othDonationCert.setCertCode(certCode);
                othDonationCertMapper.insert(othDonationCert);
                //插入pdf
                SysFileInfo sysFileInfo = new SysFileInfo();
                sysFileInfo.setId(uuid);
                sysFileInfo.setFilePath(signedPdfPath);
                sysFileInfo.setFileType(".pdf");
                sysFileInfo.setRefId(othDonationCert.getId());
                //插入img
                sysFileInfoMapper.insert(sysFileInfo);
                SysFileInfo sysFileInfoImg = new SysFileInfo();
                sysFileInfoImg.setId(UUIDGenerator.getUUID());
                sysFileInfoImg.setFilePath(imgPathFile);
                sysFileInfoImg.setFileType(".jpg");
                sysFileInfoImg.setRefId(othDonationCert.getId());
                sysFileInfoMapper.insert(sysFileInfoImg);
            }
            if ("0".equals(type)) {
                return imgPathFile;
            } else {
                return signedPdfPath;
            }
        } else {
            return null;
        }
    }

    public void certByexcel(List<OthDonationCert> othDonationCerts, String zipPath, String uuid, String buildType) throws Exception {
        String finalZipPath = zipPath + uuid + "/";
        Iterator<OthDonationCert> iterator = othDonationCerts.iterator();
        while (iterator.hasNext()) {
            OthDonationCert othDonationCert = iterator.next();
            othDonationCert.setId(UUIDGenerator.getUUID());
            String path = generateCert(othDonationCert, "1", buildType);
            String pdfName = StringUtils.substringAfterLast(path, "/");
            Files.copy(new File(path).toPath(), new File(finalZipPath + pdfName).toPath());
        }
        FileUtil.fileToZip(finalZipPath, zipPath, uuid);
    }
}
