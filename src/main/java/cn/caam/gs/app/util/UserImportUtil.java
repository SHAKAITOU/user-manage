package cn.caam.gs.app.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.util.CommonUtil;
import cn.caam.gs.common.util.MessageSourceUtil;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserCard;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.MySqlType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserImportUtil{
    protected MessageSourceUtil messageSourceUtil;
	
	/*会员登记号
	姓名
	性别
	证件号码（身份证）
	政治面貌
	职称
	学历
	出生年
	出生月
	出生日
	专业
	通信地址（工作单位、详细联系地址、邮编）"
	通信地址
	邮编
	﹡电子信箱
	﹡联系电话（手机）
	审批日期（YYYY-MM-DD）
	续费时间（YYYY-MM-DD）
	*/
	public static String COLUMN_USER_CODE = "会员登记号";
	public static String COLUMN_NAME = "姓名";
	public static String COLUMN_SEX = "性别";
	public static String COLUMN_CERTIFICATE_CODE = "证件号码（身份证）";
	public static String COLUMN_POLITICAL = "政治面貌";
	public static String COLUMN_JOB_TITLE = "职称";
	public static String COLUMN_EDU_DEGREE = "学历";
	public static String COLUMN_BIRTH_YEAR = "出生年";
	public static String COLUMN_BIRTH_MONTH = "出生月";
	public static String COLUMN_BIRTH_DAY = "出生日";
	public static String COLUMN_MAJOR = "专业";
	public static String COLUMN_ADDRESS = "通信地址（工作单位、详细联系地址、邮编）";
	public static String COLUMN_ADDRESS2 = "通信地址";
	public static String COLUMN_POSTAL_CODE = "邮编";
	public static String COLUMN_MAIL = "﹡电子信箱";
	public static String COLUMN_PHONE = "﹡联系电话（手机）";
	public static String COLUMN_CHECK_DATE = "审批日期（YYYY-MM-DD）";
	public static String COLUMN_RECHARGE_DATE = "续费时间（YYYY-MM-DD）";//有效结束日期=续费时间+5年
	
	public UserImportUtil(MessageSourceUtil messageSourceUtil){
		this.messageSourceUtil = messageSourceUtil;
	}
	
	public List<UserInfo> readExcel(File importFile) throws Exception{
		Map<String, ColumnBean> mapColumn = new LinkedHashMap<>();
		mapColumn.put(COLUMN_USER_CODE, new ColumnBean(-1, COLUMN_USER_CODE, 				true,  MySqlType.CHARACTER_VARYING,  GlobalConstants.USER_CODE_MAX_L, true ) );
		mapColumn.put(COLUMN_NAME, new ColumnBean(-1, COLUMN_NAME, 							true,  MySqlType.CHARACTER_VARYING,  GlobalConstants.USER_NAME_MAX_L, true) );
		mapColumn.put(COLUMN_SEX, new ColumnBean(-1, COLUMN_SEX, 							false, MySqlType.CHARACTER_VARYING,  6, false) );
		mapColumn.put(COLUMN_CERTIFICATE_CODE, new ColumnBean(-1, COLUMN_CERTIFICATE_CODE, 	false, MySqlType.CHARACTER_VARYING, GlobalConstants.CERTIFICATE_CODE_MAX_L, false) );
		mapColumn.put(COLUMN_POLITICAL, new ColumnBean(-1, COLUMN_POLITICAL, 				false, MySqlType.CHARACTER_VARYING,  255,false ) );
		mapColumn.put(COLUMN_JOB_TITLE, new ColumnBean(-1, COLUMN_JOB_TITLE, 				false, MySqlType.CHARACTER_VARYING,  255, false ) );
		mapColumn.put(COLUMN_EDU_DEGREE, new ColumnBean(-1, COLUMN_EDU_DEGREE, 				false, MySqlType.CHARACTER_VARYING,  255,false ) );
		mapColumn.put(COLUMN_BIRTH_YEAR, new ColumnBean(-1, COLUMN_BIRTH_YEAR, 				false, MySqlType.INTEGER,  4,false ) );
		mapColumn.put(COLUMN_BIRTH_MONTH, new ColumnBean(-1, COLUMN_BIRTH_MONTH, 			false, MySqlType.INTEGER,  2, false ) );
		mapColumn.put(COLUMN_BIRTH_DAY, new ColumnBean(-1, COLUMN_BIRTH_DAY, 				false, MySqlType.INTEGER, 2, false ) );
		mapColumn.put(COLUMN_MAJOR, new ColumnBean(-1, COLUMN_MAJOR, 						false, MySqlType.CHARACTER_VARYING,  GlobalConstants.MAJOR_MAX_L,false) );
		mapColumn.put(COLUMN_ADDRESS, new ColumnBean(-1, COLUMN_ADDRESS,					false, MySqlType.CHARACTER_VARYING,  GlobalConstants.ADDRESS_MAX_L,false) );
		mapColumn.put(COLUMN_ADDRESS2, new ColumnBean(-1, COLUMN_ADDRESS2, 					false, MySqlType.CHARACTER_VARYING,  GlobalConstants.ADDRESS_MAX_L,false) );
		mapColumn.put(COLUMN_POSTAL_CODE, new ColumnBean(-1, COLUMN_POSTAL_CODE,			false, MySqlType.CHARACTER_VARYING,  GlobalConstants.POST_CODE_MAX_L,false ) );
		mapColumn.put(COLUMN_MAIL, new ColumnBean(-1, COLUMN_MAIL	,						false, MySqlType.CHARACTER_VARYING, GlobalConstants.MAIL_MAX_L, false) );
		mapColumn.put(COLUMN_PHONE, new ColumnBean(-1, COLUMN_PHONE	,						false, MySqlType.CHARACTER_VARYING, GlobalConstants.PHONE_MAX_L, false) );
		mapColumn.put(COLUMN_CHECK_DATE, new ColumnBean(-1, COLUMN_CHECK_DATE	,			false, MySqlType.CHARACTER_VARYING, 255, false) );
		mapColumn.put(COLUMN_RECHARGE_DATE, new ColumnBean(-1, COLUMN_RECHARGE_DATE	,		false, MySqlType.CHARACTER_VARYING, 255, false) );
		log.info("readExcel file="+importFile.getAbsolutePath()+" start");

	    XSSFWorkbook workbook = null;
	    List<UserInfo> userInfoList = new ArrayList<UserInfo>();
	    try {
	        // 1. 打开excel工作簿（workbook）
	        workbook = new XSSFWorkbook(importFile);

	        // 2. 获取要解析的工作表（sheet）
	        Sheet sheet = workbook.getSheetAt(0);
	        Row headerRow = sheet.getRow(0);
	        if (headerRow == null) {
	        	throw new Exception(messageSourceUtil.getContext("userImport.uploadFile.format.fail"));
	        }
	        
	        int column = 0;
	        do {
	        	Cell cell = headerRow.getCell(column);
	        	if (Objects.isNull(cell)) break;
	        	
	        	String cellValue = CommonUtil.replaceBlank(cell.getStringCellValue());
	        	if (Strings.isBlank(cellValue)) break;
	        	
	        	if (mapColumn.containsKey(cellValue)) {
	        		mapColumn.get(cellValue).setIndex(column);
	        		log.info(cellValue+" is existed!");
	        	}else {
	        		log.info(cellValue+" is not existed!");
	        	}
	        	column++;
	        }while(true);
	        
	        if (mapColumn.get(COLUMN_USER_CODE).getIndex() == -1) {
	        	throw new Exception(COLUMN_USER_CODE+messageSourceUtil.getContext("userImport.requiredCheck.msg"));
	        }
	        
	        if (mapColumn.get(COLUMN_NAME).getIndex() == -1) {
	        	throw new Exception(COLUMN_NAME+messageSourceUtil.getContext("userImport.requiredCheck.msg"));
	        }
	        
	        // 3. 获取表格中的每一行，排除表头，从第二行开始
	        boolean isEnd = false;
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            UserInfo userInfo = new UserInfo();
	            userInfo.setUser(new MUser());
	            userInfo.setUserExtend(new MUserExtend());
	            userInfo.setUserCard(new MUserCard());
	            userInfo.setIndex(i+1);
	            
	            Row row = sheet.getRow(i); // 获取第i行
	            for (Iterator<Map.Entry<String,ColumnBean>> iterator = mapColumn.entrySet().iterator(); iterator.hasNext();) {
	    			Map.Entry<String,ColumnBean> entry = (Map.Entry<String,ColumnBean>) iterator.next();
	    			if (entry.getValue().getIndex() == -1) continue;
	    			
	    			Cell cell = row.getCell(entry.getValue().getIndex());
	    			if (COLUMN_USER_CODE.equals(entry.getValue().getName()) && 
	    					(Objects.isNull(cell) || Strings.isBlank(getCellValue(cell)))) {
	    				isEnd = true;
	    				break;
	    			}

	    			String cellValue = Objects.isNull(cell) ? "":getCellValue(cell);
	    			if (Strings.isBlank(cellValue)) continue;
	    			
	    			if (COLUMN_USER_CODE.equals(entry.getValue().getName())) {//会员登记号
	    				userInfo.setUserCode(cellValue);
	    				userInfo.getUserCard().setUserCode(cellValue);
	    				
	    			}else if (COLUMN_NAME.equals(entry.getValue().getName())) {//姓名
	    				userInfo.getUser().setName(cellValue);
	    				
	    			}else if (COLUMN_SEX.equals(entry.getValue().getName())) {//性别
	    				userInfo.setSexName(cellValue);
	    				
	    			}else if (COLUMN_CERTIFICATE_CODE.equals(entry.getValue().getName())) {//证件号码（身份证）
	    				userInfo.getUser().setCertificateCode(cellValue);
	    				
	    			}else if (COLUMN_POLITICAL.equals(entry.getValue().getName())) {//政治面貌
	    				userInfo.setPoliticalName(cellValue);
	    				
	    			}else if (COLUMN_JOB_TITLE.equals(entry.getValue().getName())) {//职称
	    				userInfo.setJobTitleName(cellValue);
	    				
	    			}else if (COLUMN_EDU_DEGREE.equals(entry.getValue().getName())) {//学历
	    				userInfo.setEduDegreeName(cellValue);
	    				
	    			}else if (COLUMN_BIRTH_YEAR.equals(entry.getValue().getName())) {//出生年
	    				userInfo.getUser().setBirth(cellValue+"-");
	    				
	    			}else if (COLUMN_BIRTH_MONTH.equals(entry.getValue().getName())) {//出生月
	    				userInfo.getUser().setBirth(userInfo.getUser().getBirth()+cellValue+"-");
	    				
	    			}else if (COLUMN_BIRTH_DAY.equals(entry.getValue().getName())) {//出生日
	    				userInfo.getUser().setBirth(userInfo.getUser().getBirth()+cellValue);
	    				
	    			}else if (COLUMN_MAJOR.equals(entry.getValue().getName())) {//专业
	    				userInfo.getUserExtend().setMajor(cellValue);
	    				
	    			}else if (COLUMN_ADDRESS.equals(entry.getValue().getName())) {//通信地址（工作单位、详细联系地址、邮编）
	    				userInfo.getUser().setAddress(cellValue);
	    				userInfo.getUser().setEmployer(cellValue);
	    				
	    			}else if (COLUMN_ADDRESS2.equals(entry.getValue().getName())) {//通信地址
	    				if (Strings.isBlank(userInfo.getUser().getAddress())) {
	    					userInfo.getUser().setAddress(cellValue);
	    					userInfo.getUser().setEmployer(cellValue);
	    				}
	    				
	    			}else if (COLUMN_POSTAL_CODE.equals(entry.getValue().getName())) {//邮编
	    				userInfo.getUser().setPostalCode(cellValue);
	    				
	    			}else if (COLUMN_MAIL.equals(entry.getValue().getName())) {//﹡电子信箱
	    				userInfo.getUser().setMail(cellValue);
	    				
	    			}else if (COLUMN_PHONE.equals(entry.getValue().getName())) {//﹡联系电话（手机）
	    				userInfo.getUser().setPhone(cellValue);
	    				
	    			}else if (COLUMN_CHECK_DATE.equals(entry.getValue().getName())) {//审批日期（YYYY-MM-DD）
	    				userInfo.getUser().setRegistDate(cellValue);
	    				
	    			}else if (COLUMN_RECHARGE_DATE.equals(entry.getValue().getName())) {//续费时间（YYYY-MM-DD）
	    				userInfo.getUser().setValidStartDate(cellValue);
	    			}
	    		}
	            
	            userInfoList.add(userInfo);
	            
	            if (isEnd) break;
	        }

	        return userInfoList;
	    } catch (IOException e) {
	        log.error(e.getMessage(), e);
	        throw new Exception(messageSourceUtil.getContext("userImport.success.fail"));
	    } finally {
	        try {
	            if (workbook != null) {
	                workbook.close();
	            }
	        } catch (IOException e) {
	        	log.error(e.getMessage(), e);
	        }
	        log.info("readExcel file="+importFile.getAbsolutePath()+" end");
	    }
	}
	
	public static String getCellValue(Cell cell) {
        String cellValue = "";
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case NUMERIC: // 数字
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            case STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case FORMULA: // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case BLANK: // 空值
                cellValue = "";
                break;
            case ERROR: // 故障
                cellValue = "";//非法字符
                break;
            default:
                cellValue = "";//未知类型
                break;
        }
        return CommonUtil.replaceBlank(cellValue);
    }

	
	
	public static void main(String[] args) throws Exception{
//		try {
//			ExcelReaderUtil.readExcel();
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
	}
	
	static class ColumnBean{
		//ｲﾝﾃﾞｯｸｽ
		private int index;
		//名称
		private String name ;
		//マッチ名称
		private String matchName;
		//必須ﾌﾗｸﾞ
		private boolean isRequired;
		//型
		private MySqlType type;
		//最大長さ
		private int maxLength;
		//ﾁｪｯｸﾌﾗｸﾞ
		private boolean isChecked;
		//ﾏｽﾀﾁｪｯｸﾌﾗｸﾞ
		private boolean isMasterCheck;
		//ﾏｽﾀﾀｲﾌﾟ
		private int masterType;
		//
		private String splitChar = "";

		public ColumnBean(int index, String name,boolean isRequired, MySqlType type, int maxLength, boolean isChecked){
			this.index = index;
			this.name = name;
			this.isRequired = isRequired;
			this.type = type;
			this.maxLength = maxLength;
			this.isChecked = isChecked;
			this.isMasterCheck = false;
			this.masterType = -1;
		}
		public ColumnBean(int index, String name,boolean isRequired, MySqlType type, int maxLength, boolean isChecked,
				boolean isMasterCheck, int masterType){
			this(index, name, isRequired, type, maxLength, isChecked);
			this.isMasterCheck = isMasterCheck;
			this.masterType = masterType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isRequired() {
			return isRequired;
		}
		public void setRequired(boolean isRequired) {
			this.isRequired = isRequired;
		}
		public MySqlType isType() {
			return type;
		}
		public void setType(MySqlType type) {
			this.type = type;
		}
		public int getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int maxLength) {
			this.maxLength = maxLength;
		}
		public boolean isChecked() {
			return isChecked;
		}
		public void setChecked(boolean isChecked) {
			this.isChecked = isChecked;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public MySqlType getType() {
			return type;
		}
		public boolean isMasterCheck() {
			return isMasterCheck;
		}
		public void setMasterCheck(boolean isMasterCheck) {
			this.isMasterCheck = isMasterCheck;
		}
		public int getMasterType() {
			return masterType;
		}
		public void setMasterType(int masterType) {
			this.masterType = masterType;
		}
		public String getMatchName() {
			return matchName;
		}
		public void setMatchName(String matchName) {
			this.matchName = matchName;
		}
		public String getSplitChar() {
			return splitChar;
		}
		public void setSplitChar(String splitChar) {
			this.splitChar = splitChar;
		}
	}
}
