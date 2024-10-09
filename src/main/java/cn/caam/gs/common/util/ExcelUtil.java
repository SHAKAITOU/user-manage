package cn.caam.gs.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param workbook HSSFWorkbook对象
     * @author
     * @date 
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String [] title, String [][] values, HSSFWorkbook workbook){
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(workbook == null){
            workbook = new HSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = workbook.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);
        // 声明列对象
        HSSFCell cell = null;
        // 创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        // 创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                // 将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return workbook;
    }

    /**
     * Excel表格导出
     * @param response HttpServletResponse对象
     * @param excelData Excel表格的数据，封装为List<List<String>>，其中包含标题
     * @param sheetName sheet的名字
     * @param fileName 导出Excel的文件名
     * @param columnWidth Excel表格的宽度，建议为15
     * @throws IOException 抛IO异常
     */
    public static void exportExcel(HttpServletResponse response,
                                   List<List<String>> excelData,
                                   String sheetName,
                                   String fileName,
                                   int columnWidth) throws IOException {

        // 声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格，设置表格名称
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置表格列宽度
        sheet.setDefaultColumnWidth(columnWidth);
        // 写入List<List<String>>中的数据
        int rowIndex = 0;
        for(List<String> data : excelData){
            // 创建一个row行，然后自增1
            HSSFRow row = sheet.createRow(rowIndex++);
            // 遍历添加本行数据
            for (int i = 0; i < data.size(); i++) {
                // 创建一个单元格
                HSSFCell cell = row.createCell(i);
                // 创建一个内容对象
                HSSFRichTextString text = new HSSFRichTextString(data.get(i));
                // 将内容对象的文字内容写入到单元格中
                cell.setCellValue(text);
            }
        }

        // 准备将Excel的输出流通过response输出到页面下载
        // 八进制输出流
        //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        response.setContentType("application/octet-stream");
        // 设置导出Excel的名称
        fileName = URLEncoder.encode(fileName+".xls","UTF-8");
    	response.setHeader("Content-Disposition", "attachment; filename="+fileName+";"+"filename*=utf-8''"+fileName);
		
        // 刷新缓冲
//        response.flushBuffer();
        // workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());
//    	FileOutputStream outExcelFile = new FileOutputStream("D:\\test.xls");
//    	workbook.write(outExcelFile);
//    	outExcelFile.flush();
//    	outExcelFile.close();
        // 关闭workbook
        workbook.close();
    }


/**
     * Excel表格导入
     * @param inputStream 输入流
     */
    public static List<Object[]> importExcel(InputStream inputStream) {
        try {
            List<Object[]> list = new ArrayList<>();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            // 获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                // 过滤表头行
                if (i == 0) {
                    continue;
                }
                // 获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType().equals(CellType.NUMERIC)) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }
                    if (cell.getCellType().equals(CellType.STRING)) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType().equals(CellType.BOOLEAN)) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType().equals(CellType.ERROR)) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            System.out.println("导入文件解析成功！");
            return list;
        }catch (Exception e){
            System.out.println("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }
}
