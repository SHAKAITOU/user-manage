package cn.caam.gs.service.impl;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.common.enums.ImportUserUpdateType;
import cn.caam.gs.common.enums.SexType;
import cn.caam.gs.common.util.CommonUtil;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T105MUserCard;
import cn.caam.gs.service.BaseService;

public class ImportUserService extends BaseService {
	
	@Autowired
	UserService userService;

	public void importUserFromExcel(List<UserInfo> userInfoList, ImportUserUpdateType updateType) {
		
	}
	
	public void checkUserInfo(List<UserInfo> userInfoList, ImportUserUpdateType updateType) {
		StringBuilder sb = new StringBuilder();
		for(UserInfo userInfo:userInfoList) {
			String msg = "";
			//会员登记号
			ColumnInfoForm clmForm = T105MUserCard.getColumnInfo(T105MUserCard.COL_USER_CODE);
			 if(!Strings.isBlank(userInfo.getUserCode()) && !CommonUtil.checkUserCode(userInfo.getUserCode())) {
				 msg += getContext("userImport.valideCheck.msg", clmForm.getLabelName()) + "|";
			 }else if (updateType == ImportUserUpdateType.INSERT_ONLY && 
					 userService.isUserCodeExist(userInfo.getUserCode())) {
				 msg += getContext("userImport.existedCheck.msg", clmForm.getLabelName()) + "|";
			 }
			//姓名
			 if (updateType == ImportUserUpdateType.INSERT_ONLY && Strings.isBlank(userInfo.getUser().getName())) {
				 clmForm = T100MUser.getColumnInfo(T100MUser.COL_NAME);
				 msg += getContext("userImport.requiredCheck.msg", clmForm.getLabelName()) + "|";
			 }
			 
			//性别
			 if ("男".equals(userInfo.getSexName())) {
				 userInfo.getUser().setSex(SexType.MAN.getKey());
			 }else if ("女".equals(userInfo.getSexName())) {
				 userInfo.getUser().setSex(SexType.WOMEN.getKey());
			 }
			//证件号码（身份证）
			//政治面貌
			 userInfo.getUser().setPolitical(CommonUtil.getPoliticalValue(userInfo.getPoliticalName()));
			//职称
			//学历
			 userInfo.getUser().setEduDegree(CommonUtil.getEduDegreeValue(userInfo.getEduDegreeName()));
			 //学位
			 userInfo.getUser().setBachelor(CommonUtil.getBachelorValue(userInfo.getEduDegreeName()));
			//出生年
			//出生月
			//出生日
			 if(!Strings.isBlank(userInfo.getUser().getBirth())) {
				 String[] date = userInfo.getUser().getBirth().split("-");
				 date[0] = date[0].replaceAll("[^0-9]", "");
				 date[1] = StringUtility.padLeft(date[1].replaceAll("[^0-9]", ""), 2, "0");
				 date[2] = StringUtility.padLeft(date[2].replaceAll("[^0-9]", ""), 2, "0");
				 if (Strings.isBlank(date[0]) || date[0].length() != 4) {
					 userInfo.getUser().setBirth(null);
				 }else {
					 userInfo.getUser().setBirth(date[0]+"-"+date[1]+"-"+date[2]);
				 }
			 }
			//专业
			//通信地址（工作单位、详细联系地址、邮编）"
			//通信地址
			//邮编
			//﹡电子信箱
			//﹡联系电话（手机）
			 if (!Strings.isBlank(userInfo.getUser().getPhone()) && userService.isPhoneNumberExist(userInfo.getUser().getPhone())) {
				 clmForm = T100MUser.getColumnInfo(T100MUser.COL_PHONE);
				 msg += getContext("userImport.existedCheck.msg", clmForm.getLabelName()) + "|";
			 }
			 
			//审批日期（YYYY-MM-DD） 2013|2019/7/2|2022-03-01|2015.8.7|2014.12
			 userInfo.getUser().setRegistDate(null);
			//续费时间（YYYY-MM-DD） 2023|2020/8/7总会续费|2017年省学会表格要求更改|2023/9/17神志病
			 userInfo.getUser().setValidStartDate(null);
			 
			if (!Strings.isBlank(msg)) {
				sb.append(getContext("userImport.line.Check.msg", String.valueOf(userInfo.getIndex()), msg)+"\n");
			}
		}
	}
}
