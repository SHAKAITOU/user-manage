package cn.caam.gs.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.app.admin.usertypesettings.form.UserTypeSettingsForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.base.entity.MUserTypeSettings;
import cn.caam.gs.domain.db.base.mapper.MUserTypeSettingsMapper;
import cn.caam.gs.domain.db.custom.entity.UserTypeSettingsInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserTypeSettingsInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserTypeSettingsService extends BaseService {
	
	@Autowired
	OptionalUserTypeSettingsInfoMapper optionalUserTypeSettingsInfoMapper;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MUserTypeSettingsMapper mUserTypeSettingsMapper;
	
	public List<UserTypeSettingsInfo> getUserTypeSettingList() {
	    return optionalUserTypeSettingsInfoMapper.getUserTypeSettingsList();
	}
	
	public MUserTypeSettings getMUserTypeSettings(String userType) {
		return mUserTypeSettingsMapper.selectByPrimaryKey(userType);
	}
	
	@Transactional
	public boolean update(UserTypeSettingsForm pageForm) {
		if (pageForm.getUserTypeSettingsInfoList() != null) {
			for(int i=0; i<pageForm.getUserTypeSettingsInfoList().size(); i++) {
				UserTypeSettingsInfo userTypeSettingsInfo = pageForm.getUserTypeSettingsInfoList().get(i);
				MUserTypeSettings mUserTypeSettings = mUserTypeSettingsMapper.selectByPrimaryKey(userTypeSettingsInfo.getUserTypeSettings().getUserType());
				boolean isInsert = false;
				if (mUserTypeSettings == null) {
					isInsert = true;
					mUserTypeSettings = new MUserTypeSettings();
					mUserTypeSettings.setCreatedBy(LoginInfoHelper.getLoginId(request));
					mUserTypeSettings.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
				}else {
					mUserTypeSettings.setUpdatedBy(LoginInfoHelper.getLoginId(request));
					mUserTypeSettings.setUpdatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
				}
				mUserTypeSettings.setUserType(userTypeSettingsInfo.getUserTypeSettings().getUserType());
				mUserTypeSettings.setFeeAmount(userTypeSettingsInfo.getUserTypeSettings().getFeeAmount());
				mUserTypeSettings.setEffectiveYear(userTypeSettingsInfo.getUserTypeSettings().getEffectiveYear());
				if (isInsert) {
					mUserTypeSettingsMapper.insert(mUserTypeSettings);
				}else {
					mUserTypeSettingsMapper.updateByPrimaryKeySelective(mUserTypeSettings);
				}
			}
		}
		return true;
	}
}
