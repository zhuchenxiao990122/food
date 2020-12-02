package com.xw.project.service.other.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xw.common.util.StringUtil;
import com.xw.common.util.UUIDGenerator;
import com.xw.project.entity.OthBillApply;
import com.xw.project.mapper.OthBillApplyMapper;
import com.xw.project.mapper.SysExcelDefMapper;
import com.xw.project.service.other.OthBillApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xw.project.util.OutputExcelMould;
import com.xw.project.vo.OthBillApplySearchVo;
import com.xw.project.vo.OthBillApplyVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dy
 * @since 2020-02-19
 */
@Service
public class OthBillApplyServiceImpl extends ServiceImpl<OthBillApplyMapper, OthBillApply> implements OthBillApplyService {
    @Autowired
    private OthBillApplyMapper othBillApplyMapper;
    @Autowired
    private SysExcelDefMapper sysExcelDefMapper;
    @Resource
    protected HttpServletResponse response;
    @Resource
    protected HttpServletRequest request;

    @Override
    public IPage<OthBillApplyVo> listBillApply(List<String> applyDate, OthBillApplySearchVo othBillApplySearchVo) {
        Page<OthBillApplyVo> pageInfo = new Page((long) othBillApplySearchVo.getCurrent(), (long) othBillApplySearchVo.getSize());
        String startDate = null;
        String endDate = null;
        if (null != applyDate && applyDate.size() > 0) {
            startDate = applyDate.get(0);
            endDate = applyDate.get(1);
        }
        return this.othBillApplyMapper.getListBillApply(pageInfo, startDate, endDate, othBillApplySearchVo);

    }

    @Override
    public int addBillApply(OthBillApply othBillApply) {
        othBillApply.setApplyId(UUIDGenerator.getUUID());
        othBillApply.setApplyTime(new Date());
        return othBillApplyMapper.insert(othBillApply);
    }

    @Override
    public void billApplyExport(List<String> applyDate) throws Exception {
        String startDate = applyDate.get(0);
        String endDate = applyDate.get(1);
        //读取模板
        ClassPathResource classPathResource = new ClassPathResource("mould/billApply.xls");
        InputStream inputStream = classPathResource.getInputStream();
        // 读取主题里面的数据
        List<Map<String, String>> themeList = sysExcelDefMapper.selectByFactorList("bill_apply", "0");
        List<Map<String, String>> columnList = sysExcelDefMapper.selectByFactorList("bill_apply", "1");
        List<Map<String, String>> startList = sysExcelDefMapper.selectByFactorList("bill_apply", "2");
        Map<String, String> dataStartList = startList.get(0);
        List<Map<String, String>> othBillApplys = othBillApplyMapper.getAllBillApplyByDate(startDate, endDate);
        //填充序号
        int startIndex = 1;
        for (Map<String, String> map : othBillApplys) {
            map.put("index", String.format("%04d", startIndex));
            startIndex++;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        startDate = sdf.format(format.parse(startDate));
        endDate = sdf.format(format.parse(endDate));
        //多天用至，一天就用当天日期
        String formatFinish = null;
        if (startDate.equals(endDate)) {
            formatFinish = startDate;
        } else {
            formatFinish = startDate + "至" + endDate;
        }
        //插入标题
        Map<String, String> map = new HashMap<>();
        String title = "浙江省红十字会--电子捐赠票据申领信息汇总表(" + formatFinish + ")";
        map.put("title", title);
        OutputExcelMould instance = OutputExcelMould.getInstance();
        instance.exportTheme(inputStream, map, themeList);
        instance.exportData(columnList, othBillApplys, dataStartList);
        Workbook wb = instance.getWorkbook();

        String xlsTitle = formatFinish + "--电子票据申领信息汇总表";
        // 下载excel
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(xlsTitle + ".xls", "UTF-8"));
        response.setContentType("application/octet-stream; charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        wb.write(out);
    }
}
