package com.xw.project.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;


/**
 * 功能描述:复杂excel的插入 模块: 项目: 版本号: 部门:技术研发部 公司:杭州滨和信息科技有限公司 创建时间:2018年2月23日
 * ************************************
 * ************************************
 */
public class OutputExcelMould {

    private int initRowIndxe; // 初始行
    private int initColIndex; // 初始列
    private int curRowIndex; // 当前行
    private int curColIndex; // 当前列
    private int serColIndex; // 序号行
    private String fieldName = "fieldName"; // 字段名称
    private String excelRow = "row"; // 字段的行位置
    private String excelCell = "col"; // 字段的列位置
    private Workbook workbook = null;
    private Sheet sheet = null;
    private CellStyle cellStyle = null;
    private Row curRow = null; // 当前行
    // 使用单例
    private static OutputExcelMould excel = new OutputExcelMould();

    private OutputExcelMould() {
    }

    // 获取此单例
    public static OutputExcelMould getInstance() {
        return excel;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public int getInitRowIndxe() {
        return initRowIndxe;
    }

    public int getInitColIndex() {
        return initColIndex;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public CellStyle getCellStyle() {
        return cellStyle;
    }

    public int getSerColIndex() {
        return serColIndex;
    }

    public void setSerColIndex(int serColIndex) {
        this.serColIndex = serColIndex;
    }

    /**
     * 读取模板(从指定路径)
     *
     * @return ExcelTemplate
     */
    public OutputExcelMould readTemplatePath(InputStream inputStream) {
        try {

            workbook = WorkbookFactory.create(inputStream);
            insertCellStyle(workbook);
            initTemplate();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("读取模板格式有错!请检查.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取模板文件不存在!请检查.");
        }
        return this;
    }

    /**
     * 创建单元格样式 this is method
     *
     * @Description:TODO
     */
    private void insertCellStyle(Workbook wb) {
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setWrapText(true);
        Font createFont = workbook.createFont();
        createFont.setFontName("宋体");
        createFont.setFontHeightInPoints((short) 12);// 设置字体大小
        cellStyle.setFont(createFont);

    }

    /*
     * 插入序号
     */
    public void insertSer() {
        int index = 1;
        Row row = null;
        Cell cell = null;
        for (int i = initRowIndxe; i < curRowIndex; i++) {
            row = sheet.getRow(i);
            cell = row.createCell(serColIndex);
            cell.setCellValue(index++);
            cell.setCellStyle(cellStyle);
        }
    }

    /*
     * 输出文件
     */
    public void writeToFile(String path) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("找不到文件!请检查.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件输出异常!请检查.");
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 初始化模板
     */
    private void initTemplate() {
        sheet = workbook.getSheetAt(0);
        sheet.setForceFormulaRecalculation(true);
    }

    /**
     * 插入主题信息位置themePostion 主题的数据 themeList 主题的真实行为：excel_row 主题的真实列为：excel_cell
     * 主题的字段为：field_name
     */
    public void exportTheme(InputStream inputStream, Map<String, String> themeValue, List<Map<String, String>> themePosition) {
        OutputExcelMould excel = OutputExcelMould.getInstance().readTemplatePath(inputStream);
        if (themePosition != null && themePosition.size() > 0) {
            Iterator<Map<String, String>> iterator = themePosition.iterator();
            while (iterator.hasNext()) {
                Map<String, String> map = iterator.next();
                // 此主题的行
                int rowNum = Integer.parseInt(String.valueOf(map.get("row")));
                int cellNum = Integer.parseInt(String.valueOf(map.get("col")));
                String field = map.get("fieldName");
                // 获取此字段对应的真实值
                if (themeValue.get(field) != null) {
                    // 判断此行是否已经被创建
                    Row rowTheme = sheet.getRow(rowNum);
                    if (rowTheme == null) {
                        rowTheme = sheet.createRow(rowNum);
                    }
                    Cell cellTheme = rowTheme.createCell(cellNum);
                    if (cellTheme == null) {
                        cellTheme = rowTheme.createCell(cellNum);
                    }
                    // 给此主题设值
                    cellTheme.setCellValue(themeValue.get(field));
                    Font createFont1 = workbook.createFont();
                    createFont1.setFontHeightInPoints((short) 16);// 设置字体大小
                    CellStyle cellStyle1 = workbook.createCellStyle();
                    cellStyle1.setFont(createFont1);
                    cellStyle1.setWrapText(true);
                    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                    cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
                        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_NONE);
                    cellTheme.setCellStyle(cellStyle1);
                }
            }
        }
    }

    public void exportData(List<Map<String, String>> columnList, List<Map<String, String>> dataList,
                           Map<String, String> startMap) {
        // 初始化开始行
        initRowIndxe = Integer.parseInt(String.valueOf(startMap.get("row")));
        // 初始 开始列信息
        initColIndex = Integer.parseInt(String.valueOf(startMap.get("col")));
        // 当前行
        curRowIndex = initRowIndxe;
        // 当前列
        curColIndex = initColIndex;

        // 遍历说有导出的数据
        Iterator<Map<String, String>> iteData = dataList.iterator();
        while (iteData.hasNext()) {
            // 遍历的到的数据
            Map<String, String> mapTemp = iteData.next();
            // 初始化excel开始行
            curRow = sheet.createRow(curRowIndex);
            // 遍历列对应的字段及相对位置
            Iterator<Map<String, String>> iteColumn = columnList.iterator();
            // 找出字段名称并配对
            Cell currentCell = null;
            while (iteColumn.hasNext()) {
                // 获取具体的列字段及位置
                Map<String, String> mapTemp1 = iteColumn.next();
                String tempValue = mapTemp1.get(fieldName);
                // 得到字段实际列
                int parseInt = Integer.parseInt(String.valueOf(mapTemp1.get(excelCell)));
                int reallyCell = parseInt + initColIndex;
                curColIndex = reallyCell;
                // 插入数据
                if (mapTemp.get(tempValue) != null) {
                    String temp = String.valueOf(mapTemp.get(tempValue));
                    // 插入数据
                    currentCell = curRow.createCell(curColIndex);

                      Font createFont = workbook.createFont();

                     cellStyle.setFont(createFont);
                    currentCell.setCellType(Cell.CELL_TYPE_STRING);
                    currentCell.setCellStyle(cellStyle);
                    currentCell.setCellValue(temp);
                } else {
                    currentCell = curRow.createCell(curColIndex);
                    currentCell.setCellStyle(cellStyle);
                }
            }

            curRowIndex++;
        }

    }
}
