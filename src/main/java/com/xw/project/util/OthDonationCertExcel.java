package com.xw.project.util;

import com.xw.common.util.ExcelUtil;
import com.xw.common.util.StringUtil;
import com.xw.project.configure.OthDonationCertExcelConfigure;
import com.xw.project.entity.OthDonationCert;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

;

@Controller
public class OthDonationCertExcel {


    private static final Logger log = Logger.getLogger(OthDonationCertExcel.class);
    private Workbook workbook = null;
    private Sheet sheet = null;
    private Row row = null;
    private Cell cell = null;
    private int physicalNumberOfCells = 0;
    private int currentRow = -1;
    private int currentCell = -1;
    private int maxCurrentRow = 0;

    public List<OthDonationCert> checkExcel(InputStream inputStream, String buildType) throws Exception {
        String cellvalue = "";
        workbook = WorkbookFactory.create(inputStream);
        // 第一个sheet
        sheet = workbook.getSheetAt(0);
        // 得到sheet页里的条数，-1去掉标题行，便于循环，否则遇到错误会退出
        maxCurrentRow = sheet.getPhysicalNumberOfRows() - 1;
        // 定位标题行，在开始3行3列内必须存在标题行
        loop:
        for (int i = 0; i < 3; i++) {
            row = sheet.getRow(i);
            if (StringUtil.isEmpty(row)) {
                continue;
            }
            for (int j = 0; j < 3; j++) {
                cell = row.getCell(j);
                if (StringUtil.isEmpty(cell)) {
                    continue;
                }
                cellvalue = cell.getStringCellValue();
                // 唯一标示存在
                if (cellvalue.compareTo("捐赠时间") == 0) {
                    currentRow = i;
                    currentCell = j;
                    break loop;
                }
            }
        }
        if (currentRow == -1) {
            // Excel的在第一行写入检查结果
            return null;
        } else {
            physicalNumberOfCells = row.getPhysicalNumberOfCells();
            // 下移到数据行
            currentRow++;
            //检查数据
            String checkResult = checkData();
            if (null != checkResult) {
                //插入数据
                List<OthDonationCert> othDonationCerts = insertData(buildType);
                return othDonationCerts;
            } else {
                return null;
            }
        }
    }

    //检查数据（主要是捐赠时间）
    public String checkData() {
        for (int i = currentRow; i <= maxCurrentRow; i++) {
            Row checkRow = sheet.getRow(i);
            if (null != checkRow) {
                Cell checkCell = checkRow.getCell(currentCell);
                if (null != checkCell) {
                    if (0 == checkCell.getCellType()) {
                        return null;
                    }
                }
            }
        }
        return "检查合格";
    }


    //插入数据
    public List<OthDonationCert> insertData(String buildType) {
        List<OthDonationCert> othDonationCerts = new ArrayList<>();
        String[] args = OthDonationCertExcelConfigure.materialCert;
        if ("1".equals(buildType)) {
            args = OthDonationCertExcelConfigure.moneyCert;
        }
        while (currentRow <= maxCurrentRow) {
            ExcelUtil<OthDonationCert> excelUtil = new ExcelUtil();
            OthDonationCert othDonationCert = excelUtil.excelReflect(sheet, currentRow, currentCell, maxCurrentRow, new OthDonationCert(), args);
            if (StringUtil.isNotEmpty(othDonationCert.getDonationDate())) {
                othDonationCerts.add(othDonationCert);
            }
            currentRow++;
        }
        return othDonationCerts;
    }

}
