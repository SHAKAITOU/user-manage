package cn.caam.gs.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReaderUtil{
	public static void readExcel() throws Exception{
		File file = new File("C:\\work_gs\\document\\甘肃省针灸学会汇总表.xlsx");
	    InputStream is = null;
	    XSSFWorkbook workbook = null;
	    try {
	        // 1. 创建excel工作簿（workbook）
//	        is = file.getInputStream();
	        workbook = new XSSFWorkbook(file);

	        // 2. 获取要解析的工作表（sheet）
	        Sheet sheet = workbook.getSheetAt(0); // 获取第一个sheet

//	        // 3. 获取表格中的每一行，排除表头，从第二行开始
//	        User user;
//	        List<User> list = new ArrayList<>();
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i); // 获取第i行
	            System.out.println(row.getCell(1).getStringCellValue());
//	            // 4. 获取每一行的每一列,并为user对象的属性赋值，添加到list集合中
//	            user = new User();
//	            user.setUsername(row.getCell(1).getStringCellValue());
//	            user.setSex(row.getCell(2).getStringCellValue());
//	            user.setPhone(row.getCell(3).getStringCellValue());
//	            user.setCity(row.getCell(4).getStringCellValue());
//	            user.setPosition(row.getCell(5).getStringCellValue());
//	            user.setSalary(new BigDecimal(row.getCell(6).getNumericCellValue()));
//	            list.add(user);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("批量导入失败");
	    } finally {
	        try {
	            if (is != null) {
	                is.close();
	            }
	            if (workbook != null) {
	                workbook.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("批量导入失败");
	        }
	    }
	}
	
	public static void main(String[] args) throws Exception{
		try {
			ExcelReaderUtil.readExcel();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
