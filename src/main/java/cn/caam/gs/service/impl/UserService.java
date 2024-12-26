package cn.caam.gs.service.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.app.admin.adminmanage.form.AdminManageSearchForm;
import cn.caam.gs.app.admin.adminmanage.form.AdminUserDetailForm;
import cn.caam.gs.app.admin.adminmanage.output.AdminUserListOutput;
import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.AdminUserImportResult;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.dbmainten.form.ColumnInfoForm;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.app.util.UserInfoHelper;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.DeleteType;
import cn.caam.gs.common.enums.DownloadFileType;
import cn.caam.gs.common.enums.ExecuteReturnType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.ImportUserUpdateType;
import cn.caam.gs.common.enums.SexType;
import cn.caam.gs.common.enums.SmsConfigType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.enums.ValidType;
import cn.caam.gs.common.util.CertificateCreateUtil;
import cn.caam.gs.common.util.CommonUtil;
import cn.caam.gs.common.util.DateUtility;
import cn.caam.gs.common.util.DateUtility.DateFormat;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.ExcelUtil;
import cn.caam.gs.common.util.ImageUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DatePattern;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.common.util.LocalDateUtility.TimePattern;
import cn.caam.gs.common.util.PasswordGenerator;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserCard;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.base.mapper.MAdminMapper;
import cn.caam.gs.domain.db.base.mapper.MUserCardMapper;
import cn.caam.gs.domain.db.base.mapper.MUserExtendMapper;
import cn.caam.gs.domain.db.base.mapper.MUserMapper;
import cn.caam.gs.domain.db.custom.entity.AdminUserInfo;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalAdminUserInfoMapper;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserInfoMapper;
import cn.caam.gs.domain.tabledef.impl.T100MUser;
import cn.caam.gs.domain.tabledef.impl.T101MUserExtend;
import cn.caam.gs.domain.tabledef.impl.T105MUserCard;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService extends BaseService {
	@Autowired
	OptionalAdminUserInfoMapper optionalAdminUserInfoMapper;
	
	@Autowired
	OptionalUserInfoMapper optionalUserInfoMapper;
	
	@Autowired
	MAdminMapper adminMapper;
	
	@Autowired
	MUserMapper userMapper;
	
	@Autowired
	MUserExtendMapper userExtendMapper;
	
	@Autowired
	MUserCardMapper userCardMapper;
	
	@Autowired
	SmsSendService smsSendService;
	
	public AdminUserInfo getAdminUserInfo(String userId) {
	    return optionalAdminUserInfoMapper.getUserInfo(userId);
	}
	
	public AdminUserListOutput getAdminUserList(AdminManageSearchForm pageForm) {
	    AdminUserListOutput userListOutput = new AdminUserListOutput();
	    userListOutput.setCount(optionalAdminUserInfoMapper.getUserListCount(pageForm));
	    userListOutput.setUserList(optionalAdminUserInfoMapper.getUserList(pageForm));
    	return userListOutput;
	}

	public UserListOutput getUserList(HttpServletRequest request, UserSearchForm pageForm) {
		 @SuppressWarnings("unchecked")
	        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
	                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
		 
	    UserListOutput userListOutput = new UserListOutput();
	    pageForm.setExpiredSoonDate(
	            LocalDateUtility.addDate(LocalDateUtility.getCurrentDateString(), DatePattern.UUUUHMMHDD, 5));
	    pageForm.setVaryExpiredDate(LocalDateUtility.addDate(LocalDateUtility.getCurrentDateString(), DatePattern.UUUUHMMHDD, -60));
	    userListOutput.setCount(optionalUserInfoMapper.getUserListCount(pageForm));
	    userListOutput.setUserList(optionalUserInfoMapper.getUserList(pageForm));
	    if (userListOutput.getUserList() != null && userListOutput.getUserList().size() > 0) {
	    	List<FixValueInfo> focusOnList = fixedValueMap.get(FixedValueType.MEMBERSHIP_PATH);
	    	for(UserInfo userInfo:userListOutput.getUserList()) {
	    		if (!Strings.isBlank(userInfo.getUser().getFocusOn())) {
	    			String focusOnName = "";
	    			String[] focusOns = userInfo.getUser().getFocusOn().split(",");
	    			for(String focusOn:focusOns) {
	    				for (FixValueInfo fValueInfo : focusOnList) {
	    					if (fValueInfo.getValueObj().getValue().equals(focusOn)) {
	    						if (!Strings.isBlank(focusOnName)) focusOnName += ",";
	    						focusOnName += fValueInfo.getValueObj().getName();
	    						break;
	    					}
	    		        }
	    			}
	    			userInfo.setFocusOnName(focusOnName);
	    		}
	    	}
	    }
	    
    	return userListOutput;
	}
	
	public UserInfo getUserInfo(String userId) {
	    return optionalUserInfoMapper.getUserInfo(userId);
	}
	
	public UserInfo getLoginUserInfo(String userCode) {
        return optionalUserInfoMapper.getLoginUserInfo(userCode);
    }
	
	public UserInfo getLoginUserInfoByPhone(String phone) {
        return optionalUserInfoMapper.getLoginUserInfoByPhone(phone);
    }
	
	public UserInfo getBaseUserInfo(String userId) {
        return optionalUserInfoMapper.getBaseUserInfo(userId);
    }
	
	public MAdmin getLoginAdminInfo(String userCode) {
        return adminMapper.selectByPrimaryKey(userCode);
    }
	
	public boolean isPhoneNumberExist(String phoneNumber) {
		 return optionalUserInfoMapper.isPhoneNumberExist(phoneNumber, "");
	}
	
	public boolean isPhoneNumberExist(String phoneNumber, String userId) {
		 return optionalUserInfoMapper.isPhoneNumberExist(phoneNumber, userId);
	}
	
	public boolean isEmailExist(String email) {
		return optionalUserInfoMapper.isEmailExist(email);
	}
	
	public boolean isUserCodeExist(String userCode) {
		return optionalUserInfoMapper.isUserCodeExist(userCode);
	}
	
//	public void downloadAppliactionForm(String userId, OutputStream  outputStream) throws Exception{
//		 UserInfo userInfo = this.getUserInfo(userId);
//		 if (userInfo.getUserExtend().getApplicationForm() != null && userInfo.getUserExtend().getApplicationForm().length > 0){
//			IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getApplicationForm()), outputStream);
//		 }
//	}
	
	private String createDownloadImageFileName(String userCode, String name, String ext)  throws Exception{
		if (Strings.isBlank(ext)) {
			ext = "jpg";
		}
		return URLEncoder.encode(userCode+"_"+name+"."+ext,"UTF-8");
	}
	public void downloadFile(HttpServletRequest request, String userId, DownloadFileType fileType, HttpServletResponse response) throws Exception{
		 UserInfo userInfo = this.getUserInfo(userId);
		 
		 response.setHeader(HttpHeaders.PRAGMA, "No-cache");
		 response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
		 if (fileType == DownloadFileType.PHOTO) {//2寸证件照
			String ext = Strings.isBlank(userInfo.getUserExtend().getPhotoExt()) ? "jpg":userInfo.getUserExtend().getPhotoExt();
			String filename = createDownloadImageFileName(userInfo.getUserCode(), "证件照", ext);
			response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
			response.setContentType("application/"+ext+";charset=UTF-8");
			if (userInfo.getUserExtend().getPhoto() != null && userInfo.getUserExtend().getPhoto().length > 0){
				IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getPhoto()), response.getOutputStream());
			 }

		 }else if (fileType == DownloadFileType.EDUCATIONAL) {//学历证书附件
			 String ext = Strings.isBlank(userInfo.getUserExtend().getEducationalAtExt()) ? "jpg":userInfo.getUserExtend().getEducationalAtExt();
			String filename = createDownloadImageFileName(userInfo.getUserCode(), "学历证书附件", ext);
			response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
			response.setContentType("application/"+ext+";charset=UTF-8");
			if (userInfo.getUserExtend().getEducationalAt() != null && userInfo.getUserExtend().getEducationalAt().length > 0){
				IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getEducationalAt()), response.getOutputStream());
			 }
		 }else if (fileType == DownloadFileType.BACHELOR) {//学位证书附件
			 String ext = Strings.isBlank(userInfo.getUserExtend().getBachelorAtExt()) ? "jpg":userInfo.getUserExtend().getBachelorAtExt();
			String filename = createDownloadImageFileName(userInfo.getUserCode(), "学位证书附件", ext);
			response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
			response.setContentType("application/"+ext+";charset=UTF-8");
			if (userInfo.getUserExtend().getBachelorAt() != null && userInfo.getUserExtend().getBachelorAt().length > 0){
				IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getBachelorAt()), response.getOutputStream());
			 }
		 }else if (fileType == DownloadFileType.VOCATIONAL) {//职业证书附件
			 String ext = Strings.isBlank(userInfo.getUserExtend().getVocationalAtExt()) ? "jpg":userInfo.getUserExtend().getVocationalAtExt();
			String filename = createDownloadImageFileName(userInfo.getUserCode(), "职业证书附件", ext);
			response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
			response.setContentType("application/"+ext+";charset=UTF-8");
			if (userInfo.getUserExtend().getVocationalAt()!= null && userInfo.getUserExtend().getVocationalAt().length > 0){
				IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getVocationalAt()), response.getOutputStream());
			 }
		 }else if (fileType == DownloadFileType.APPLICATION_FORM) {//申请资料pdf
//			 String filename = URLEncoder.encode(GlobalConstants.APPLICATION_FORM_NAME+".pdf","UTF-8");
//			 response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
//			 response.setContentType("application/pdf;charset=UTF-8");
//			 if (userInfo.getUserExtend().getApplicationForm() != null && userInfo.getUserExtend().getApplicationForm().length > 0){
//				IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getApplicationForm()), response.getOutputStream());
//			 }
			 String ext = Strings.isBlank(userInfo.getUserExtend().getApplicationFormExt()) ? "jpg":userInfo.getUserExtend().getApplicationFormExt();
				String filename = createDownloadImageFileName(userInfo.getUser().getName(), GlobalConstants.APPLICATION_FORM_NAME1, ext);
				response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
				response.setContentType("application/"+ext+";charset=UTF-8");
				if (userInfo.getUserExtend().getApplicationForm()!= null && userInfo.getUserExtend().getApplicationForm().length > 0){
					IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getApplicationForm()), response.getOutputStream());
				 }
		 }else if (fileType == DownloadFileType.APPLICATION_FORM2) {//申请资料pdf
			 String ext = Strings.isBlank(userInfo.getUserExtend().getApplicationFormExt2()) ? "jpg":userInfo.getUserExtend().getApplicationFormExt2();
				String filename = createDownloadImageFileName(userInfo.getUser().getName(), GlobalConstants.APPLICATION_FORM_NAME2, ext);
				response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
				response.setContentType("application/"+ext+";charset=UTF-8");
				if (userInfo.getUserExtend().getApplicationForm2()!= null && userInfo.getUserExtend().getApplicationForm2().length > 0){
					IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getApplicationForm2()), response.getOutputStream());
				 }
		 }else if (fileType == DownloadFileType.APPLICATION_FORM_TEMPLATE) {//申请资料模版word
			 response.setHeader(HttpHeaders.PRAGMA, "No-cache");
			response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
			String filename = URLEncoder.encode(GlobalConstants.APPLICATION_FORM_NAME+".docx","UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=UTF-8");
			outputAppliactionForm(userId, response.getOutputStream());
			
		 }else if (fileType == DownloadFileType.USER_CERTIFICATE) {//证明书zip
			response.setHeader(HttpHeaders.PRAGMA, "No-cache");
			response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
			
			String filename = createDownloadImageFileName(userInfo.getUserCode(), "电子会员证", "zip");
			response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
		    response.setContentType("application/octet-stream");
		    
		    List<byte[]> imgList=new ArrayList<>();
		    UserType userType = UserType.keyOf(userInfo.getUser().getUserType());
	        List<DownloadFileType> imageList = UserInfoHelper.getCertficateImageList(userType);
	        for(DownloadFileType downloadFileType:imageList) {
	        	Map<String, CertificateCreateUtil.DrawOject> map = UserInfoHelper.getMapDrawObject(request, userInfo, downloadFileType);
	        	byte[] bytes = UserInfoHelper.drawCertImage(downloadFileType.getFilePath(), map);
	        	imgList.add(bytes);
	        }
	        
	        try(ZipOutputStream zipOutputStream=new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()))){
	        	int index = 1;
	            for(byte[] bytes:imgList){
	                zipOutputStream.putNextEntry(new ZipEntry(index+".png"));
	                zipOutputStream.write(bytes);
	                zipOutputStream.closeEntry();
	                index++;
	            }
	        }catch (Exception e){
	        	log.error(e.getMessage(), e);
	            e.printStackTrace();
	        }
			
		 }
		 
	}
	
	public boolean checkPasswordCorrect(HttpServletRequest request, String password) {
		boolean result = false;
		String encryptPassword = EncryptorUtil.encrypt(password);
		if (LoginInfoHelper.isUserLogin(request)) {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
			userInfo = this.getUserInfo(userInfo.getUser().getId());
			result = encryptPassword.equals(userInfo.getUser().getPassword());
		}else if (LoginInfoHelper.isAdminLogin(request)) {
			MAdmin mAdmin = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
			mAdmin = getLoginAdminInfo(mAdmin.getId());
			result = encryptPassword.equals(mAdmin.getPassword());
		}
		
		return result;
	}
	
	@Transactional
	public void updatePassword(HttpServletRequest request, String password) {
		String encryptPassword = EncryptorUtil.encrypt(password);
		if (LoginInfoHelper.isUserLogin(request)) {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
			MUser mUser = new MUser();
			mUser.setId(userInfo.getId());
			mUser.setPassword(encryptPassword);
			userMapper.updateByPrimaryKeySelective(mUser);
			
		}else if (LoginInfoHelper.isAdminLogin(request)) {
			MAdmin mAdminSession = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
			MAdmin mAdmin = new MAdmin();
			mAdmin.setId(mAdminSession.getId());
			mAdmin.setPassword(encryptPassword);
			adminMapper.updateByPrimaryKeySelective(mAdmin);
		}
	}
	
	@Transactional
	public void updateUserPassword(HttpServletRequest request, String phone, String password) {
		String encryptPassword = EncryptorUtil.encrypt(password);
		
		UserInfo userInfo = getLoginUserInfoByPhone(phone);
		MUser mUser = new MUser();
		mUser.setId(userInfo.getId());
		mUser.setPassword(encryptPassword);
		userMapper.updateByPrimaryKeySelective(mUser);
	}
	
	@Transactional
	public void updateUserPhone(HttpServletRequest request, String id, String phone) {
		MUser mUser = new MUser();
		mUser.setId(id);
		mUser.setPhone(phone);
		userMapper.updateByPrimaryKeySelective(mUser);
	}
	
	@Transactional
	public boolean resetUserPassword(HttpServletRequest request, String id) throws Exception{
		MUser mUser = userMapper.selectByPrimaryKey(id);
		if (mUser == null) {
			return false;
		}
		
		MAdmin adminUserInfo = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		if (adminUserInfo == null) {
			return false;
		}
		
		String password = PasswordGenerator.generatePassword();
		//【甘肃省针灸学会】尊敬的用户，您的密码已经重置为${1}，请尽快登录平台修改您的密码。
		if (!smsSendService.sendPasswordReset(SmsConfigType.USER_PASSWORD_RESET, mUser.getPhone(), password)) {
			return false;
		}

		MUser userInput = new MUser();
		userInput.setId(id);
		userInput.setPassword(EncryptorUtil.encrypt(password));
		
		userMapper.updateByPrimaryKeySelective(userInput);
		
		
		
		return true;
	}
	
	@Transactional
	public boolean resetAdminPassword(HttpServletRequest request, String id) throws Exception{
		MAdmin mAdmin = adminMapper.selectByPrimaryKey(id);
		if (mAdmin == null) {
			return false;
		}
		
		MAdmin adminUserInfo = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		if (adminUserInfo == null) {
			return false;
		}
		
		String password = PasswordGenerator.generatePassword();
		//【甘肃省针灸学会】您的管理员密码已经重置为${1}，请尽快登录平台修改您的密码。
		if (!smsSendService.sendPasswordReset(SmsConfigType.ADMIN_PASSWORD_RESET, mAdmin.getPhone(), password)) {
			return false;
		}

		MAdmin adminInput = new MAdmin();
		adminInput.setId(id);
		adminInput.setPassword(EncryptorUtil.encrypt(password));
		adminInput.setUpdatedBy(adminUserInfo.getId());
		adminInput.setUpdatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		
		adminMapper.updateByPrimaryKeySelective(adminInput);
		
		return true;
	}
	
	/**
	 * 动态生成入会申请表(word文档)
	 * @param userId 会员ID·
	 * @return 文档路径
	 */
	public void outputAppliactionForm(String userId, OutputStream  outputStream) throws Exception{
		 UserInfo userInfo = this.getUserInfo(userId);
		 Map<String, Object> map = new HashMap<>();
		 map.put("name", userInfo.getUser().getName());
		 map.put("sex", userInfo.getSexName());
		 map.put("birth", userInfo.getUser().getBirth());
		 map.put("nationality", userInfo.getNationalityName());
		 map.put("political", userInfo.getPoliticalName());
		 map.put("major", userInfo.getUserExtend().getMajor());
		 map.put("edu_degree", userInfo.getEduDegreeName());
		 map.put("job_title", userInfo.getJobTitleName());
		 map.put("bachelor", userInfo.getBachelorName());
		 map.put("position", userInfo.getPositionName());
		 map.put("certificate_type", userInfo.getCertificateTypeName());
		 map.put("certificate_code", userInfo.getUser().getCertificateCode());
		 map.put("employer", userInfo.getUser().getEmployer());
		 map.put("phone", userInfo.getUser().getPhone());
		 map.put("address", userInfo.getUser().getAddress());
		 map.put("mail", userInfo.getUser().getMail());
		 map.put("postal_code", userInfo.getUser().getPostalCode());
		 map.put("research_dir", userInfo.getUserExtend().getResearchDir());
		 map.put("learn_experience", userInfo.getUserExtend().getLearnExperience());
		 map.put("work_experience", userInfo.getUserExtend().getWorkExperience());
		 map.put("papers", userInfo.getUserExtend().getPapers());
		 map.put("honors", userInfo.getUserExtend().getHonors());
		 map.put("introducer1", userInfo.getUserExtend().getIntroducer1());
		 map.put("introducer2", userInfo.getUserExtend().getIntroducer2());

		 if (userInfo.getUserExtend().getPhoto() != null && userInfo.getUserExtend().getPhoto().length > 0){
			 PictureRenderData pictureRenderData = Pictures.ofStream(new ByteArrayInputStream(userInfo.getUserExtend().getPhoto()), 
					 PictureType.JPEG).size(150, 200).create();
			 map.put("photo", pictureRenderData);
		 }
		
		 ResourceLoader resourceLoader = new DefaultResourceLoader();
		 InputStream inputStream = resourceLoader.getResource(GlobalConstants.APPLICATION_FORM_TEMPLATE_FILE).getInputStream();
		 XWPFTemplate.compile(inputStream).render(map, outputStream);
	}
	
	@Transactional
	public void updateUserInfo(HttpServletRequest request, UserDetailForm userDetailForm) throws IOException{
		UserInfo userInfo = userDetailForm.getUserInfo();
		if (userInfo.getUser() != null) {
			MUser userInput = userInfo.getUser();
			MUser user = userMapper.selectByPrimaryKey(userInput.getId());
			boolean isUserTypeChanged = !Objects.isNull(userInput.getUserType()) && !userInput.getUserType().equals(user.getUserType());
			//会员名称
			user.setName(userInput.getName());
			//性别
			user.setSex(userInput.getSex());
			//出生年月
			user.setBirth(userInput.getBirth());
			//民族
			user.setNationality(userInput.getNationality());
			//政治面貌
			user.setPolitical(userInput.getPolitical());
			//学历
			user.setEduDegree(userInput.getEduDegree());
			//学位
			user.setBachelor(userInput.getBachelor());
			//职务
			user.setPosition(userInput.getPosition());
			//工作单位
			user.setEmployer(userInput.getEmployer());
			//单位性质
			user.setEmployerType(userInput.getEmployerType());
			//职称
			user.setJobTitle(userInput.getJobTitle());
			//证件类型
			user.setCertificateType(userInput.getCertificateType());
			//证件号码
			user.setCertificateCode(userInput.getCertificateCode());
			//所在地区
			user.setArea(userInput.getArea());
			//邮政编码
			user.setPostalCode(userInput.getPostalCode());
			//通讯地址
			user.setAddress(userInput.getAddress());
			//入会途径
			user.setMembershipPath(userInput.getMembershipPath());
			//关注
			user.setFocusOn(userInput.getFocusOn());
			//电子邮箱
			user.setMail(userInput.getMail());
			if (!LoginInfoHelper.isAdminLogin(request)) {
				user.setPhone(null);
			}else {
				//会员类型
				user.setUserType(userInput.getUserType());
				//入会时间
				if(!Strings.isBlank(userInput.getRegistDate())) {
					user.setRegistDate(userInput.getRegistDate()+" 00:00:00");
				}
				//手机号
				user.setPhone(userInput.getPhone());
				//有效日期
				if(!Strings.isBlank(userInput.getValidEndDate())) {
					user.setValidEndDate(userInput.getValidEndDate()+" 00:00:00");
				}
			}
			//入会申请表上传之后，审核返回-》等待审核
			if (userDetailForm.getApplicationFormFile() != null && !StringUtils.isBlank(userDetailForm.getApplicationFormFile().getOriginalFilename())) {
				if (UserCheckStatusType.NEW.getKey().equals(user.getCheckStatus()) || 
						UserCheckStatusType.RETURN.getKey().equals(user.getCheckStatus())) {
					user.setCheckStatus(UserCheckStatusType.WAIT_FOR_REVIEW.getKey());
				}
			}
			
			userMapper.updateByPrimaryKeySelective(user);
			
			//甘肃会员和中国会员之间的切换
			if (LoginInfoHelper.isAdminLogin(request) && isUserTypeChanged) {
				MUserCard oldUserCard = optionalUserInfoMapper.getUserCard(user.getId());
				String oldUserCode = oldUserCard.getUserCode();
				String newUserCode = oldUserCard.getUserCode();
				if ( !Strings.isBlank(oldUserCode)) {
					if (UserType.PERSON_GANSU.getKey().equals(userInput.getUserType()) && oldUserCode.startsWith("M")) {
						newUserCode = oldUserCode.replace("M", "G");
					}else if (UserType.PERSON_CHINA.getKey().equals(userInput.getUserType()) && oldUserCode.startsWith("G")) {
						newUserCode = oldUserCode.replace("G", "M");
					}
					
					if (!oldUserCode.equals(newUserCode)) {
						oldUserCard.setValidStatus(ValidType.INVALID.getKey());
						oldUserCard.setUpdatedBy(LoginInfoHelper.getLoginId(request));
						oldUserCard.setUpdatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
						
						userCardMapper.updateByPrimaryKeySelective(oldUserCard);
						
						MUserCard newUserCard = optionalUserInfoMapper.getUserCardByUserCode(newUserCode);
						if (Objects.isNull(newUserCard)) {
							newUserCard = new MUserCard();
							newUserCard.setId(UUID.randomUUID().toString());
							newUserCard.setUserId(oldUserCard.getUserId());
							newUserCard.setUserCode(newUserCode);
							newUserCard.setValidStatus(ValidType.VALID.getKey());
							newUserCard.setCreatedBy(LoginInfoHelper.getLoginId(request));
							newUserCard.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
							
							userCardMapper.insert(newUserCard);
						}else {
							newUserCard.setValidStatus(ValidType.VALID.getKey());
							newUserCard.setUpdatedBy(LoginInfoHelper.getLoginId(request));
							newUserCard.setUpdatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
							
							userCardMapper.updateByPrimaryKeySelective(newUserCard);
						}
					}
				}
			}
		}
		if (userInfo.getUserExtend() != null) {
			MUserExtend userExtendInput = userInfo.getUserExtend();
			boolean isUpdate = true;
			MUserExtend userExtend = userExtendMapper.selectByPrimaryKey(userInfo.getUser().getId());
			if (userExtend == null) {
				userExtend = new MUserExtend();
				userExtend.setId(userInfo.getUser().getId());
				isUpdate = false;
			}
			//介绍人1
			userExtend.setIntroducer1(userExtendInput.getIntroducer1());
			//介绍人2
			userExtend.setIntroducer2(userExtendInput.getIntroducer2());
			//专业
			userExtend.setMajor(userExtendInput.getMajor());
			//研究方向
			userExtend.setResearchDir(userExtendInput.getResearchDir());
			//主要学习经历
			userExtend.setLearnExperience(userExtendInput.getLearnExperience());
			//主要工作经历
			userExtend.setWorkExperience(userExtendInput.getWorkExperience());
			//代表性论文及著作
			userExtend.setPapers(userExtendInput.getPapers());
			//获得科技奖励及荣誉情况
			userExtend.setHonors(userExtendInput.getHonors());
			//2寸证件照
			if (userDetailForm.getPhotoFile() != null && !StringUtils.isBlank(userDetailForm.getPhotoFile().getOriginalFilename())) {
				userExtend.setPhotoExt(userDetailForm.getPhotoFile().getOriginalFilename().split("\\.")[1]);
				userExtend.setPhoto(userDetailForm.getPhotoFile().getBytes());
			}
			//学历证书附件
			if (userDetailForm.getEducationalAtFile() != null && !StringUtils.isBlank(userDetailForm.getEducationalAtFile().getOriginalFilename())) {
				userExtend.setEducationalAtExt(userDetailForm.getEducationalAtFile().getOriginalFilename().split("\\.")[1]);
				userExtend.setEducationalAt(resizeImage(userDetailForm.getEducationalAtFile().getBytes(), userExtend.getEducationalAtExt()));
			}
			//学位证书附件
			if (userDetailForm.getBachelorAtFile() != null && !StringUtils.isBlank(userDetailForm.getBachelorAtFile().getOriginalFilename())) {
				userExtend.setBachelorAtExt(userDetailForm.getBachelorAtFile().getOriginalFilename().split("\\.")[1]);
				userExtend.setBachelorAt(resizeImage(userDetailForm.getBachelorAtFile().getBytes(), userExtend.getBachelorAtExt()));
			}
			//职业证书附件
			if (userDetailForm.getVocationalAtFile() != null && !StringUtils.isBlank(userDetailForm.getVocationalAtFile().getOriginalFilename())) {
				userExtend.setVocationalAtExt(userDetailForm.getVocationalAtFile().getOriginalFilename().split("\\.")[1]);
				userExtend.setVocationalAt(resizeImage(userDetailForm.getVocationalAtFile().getBytes(), userExtend.getVocationalAtExt()));
			}
			
			//入会申请表
			if (userDetailForm.getApplicationFormFile() != null && !StringUtils.isBlank(userDetailForm.getApplicationFormFile().getOriginalFilename())) {
				userExtend.setApplicationFormExt(userDetailForm.getApplicationFormFile().getOriginalFilename().split("\\.")[1]);
				userExtend.setApplicationForm(resizeImage(userDetailForm.getApplicationFormFile().getBytes(), userExtend.getApplicationFormExt()));
			}
			//入会申请表2
			if (userDetailForm.getApplicationForm2File() != null && !StringUtils.isBlank(userDetailForm.getApplicationForm2File().getOriginalFilename())) {
				userExtend.setApplicationFormExt2(userDetailForm.getApplicationForm2File().getOriginalFilename().split("\\.")[1]);
				userExtend.setApplicationForm2(resizeImage(userDetailForm.getApplicationForm2File().getBytes(), userExtend.getApplicationFormExt2()));
			}
			
			if (isUpdate) {
//				userExtendMapper.updateByPrimaryKeyWithBLOBs(userExtend);
				userExtendMapper.updateByPrimaryKeySelective(userExtend);
			}else {
				userExtendMapper.insert(userExtend);
			}
		}
		
	}
	
	@Transactional
	public String insertUserInfo(HttpServletRequest request, UserDetailForm userDetailForm) throws IOException{
		UserInfo userInfo = userDetailForm.getUserInfo();
		
		MUser userInput = userInfo.getUser();
		//UUID
		userInput.setId(UUID.randomUUID().toString());
		if (Objects.isNull(userInput.getName())) {
			userInput.setName("");
		}
		//审核状态：审核通过
		userInput.setCheckStatus(UserCheckStatusType.PASS.getKey());
		//申请时间
		userInput.setApplicationDate(LocalDateUtility.getCurrentDateTimeString());
		//有效状态
		userInput.setValidStatus(ValidType.VALID.getKey());
		//删除状态
		userInput.setDeleted(DeleteType.UNDELETED.getkey());
		//入会时间
		if(!Strings.isBlank(userInput.getRegistDate())) {
			userInput.setRegistDate(userInput.getRegistDate()+" 00:00:00");
		}
		//有效日期
		if(!Strings.isBlank(userInput.getValidEndDate())) {
			userInput.setValidEndDate(userInput.getValidEndDate()+" 23:59:59");
		}
		userInput.setCreatedBy(LoginInfoHelper.getLoginId(request));
		userInput.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		
		userMapper.insert(userInput);
		
		MUserExtend userExtendInput = Optional.ofNullable(userInfo.getUserExtend()).orElse(new MUserExtend());
		userExtendInput.setId(userInput.getId());
		//2寸证件照
		if (userDetailForm.getPhotoFile() != null && !StringUtils.isBlank(userDetailForm.getPhotoFile().getOriginalFilename())) {
			userExtendInput.setPhotoExt(userDetailForm.getPhotoFile().getOriginalFilename().split("\\.")[1]);
			userExtendInput.setPhoto(userDetailForm.getPhotoFile().getBytes());
		}
		//学历证书附件
		if (userDetailForm.getEducationalAtFile() != null && !StringUtils.isBlank(userDetailForm.getEducationalAtFile().getOriginalFilename())) {
			userExtendInput.setEducationalAtExt(userDetailForm.getEducationalAtFile().getOriginalFilename().split("\\.")[1]);
			userExtendInput.setEducationalAt(resizeImage(userDetailForm.getEducationalAtFile().getBytes(), userExtendInput.getEducationalAtExt()));
		}
		//学位证书附件
		if (userDetailForm.getBachelorAtFile() != null && !StringUtils.isBlank(userDetailForm.getBachelorAtFile().getOriginalFilename())) {
			userExtendInput.setBachelorAtExt(userDetailForm.getBachelorAtFile().getOriginalFilename().split("\\.")[1]);
			userExtendInput.setBachelorAt(resizeImage(userDetailForm.getBachelorAtFile().getBytes(), userExtendInput.getBachelorAtExt()));
		}
		//职业证书附件
		if (userDetailForm.getVocationalAtFile() != null && !StringUtils.isBlank(userDetailForm.getVocationalAtFile().getOriginalFilename())) {
			userExtendInput.setVocationalAtExt(userDetailForm.getVocationalAtFile().getOriginalFilename().split("\\.")[1]);
			userExtendInput.setVocationalAt(resizeImage(userDetailForm.getVocationalAtFile().getBytes(), userExtendInput.getVocationalAtExt()));
		}
		
		//入会申请表
		if (userDetailForm.getApplicationFormFile() != null && !StringUtils.isBlank(userDetailForm.getApplicationFormFile().getOriginalFilename())) {
			userExtendInput.setApplicationFormExt(userDetailForm.getApplicationFormFile().getOriginalFilename().split("\\.")[1]);
			userExtendInput.setApplicationForm(resizeImage(userDetailForm.getApplicationFormFile().getBytes(), userExtendInput.getApplicationFormExt()));
		}
		//入会申请表2
		if (userDetailForm.getApplicationForm2File() != null && !StringUtils.isBlank(userDetailForm.getApplicationForm2File().getOriginalFilename())) {
			userExtendInput.setApplicationFormExt2(userDetailForm.getApplicationForm2File().getOriginalFilename().split("\\.")[1]);
			userExtendInput.setApplicationForm2(resizeImage(userDetailForm.getApplicationForm2File().getBytes(), userExtendInput.getApplicationFormExt2()));
		}
		
		userExtendMapper.insert(userExtendInput);
		
		MUserCard userCard = Optional.ofNullable(userInfo.getUserCard()).orElse(new MUserCard());
		//UUID
		userCard.setId(UUID.randomUUID().toString());
		userCard.setUserId(userInput.getId());
		//G20240704000001
		if (Strings.isBlank(userCard.getUserCode())) {
			userCard.setUserCode(EncryptorUtil.generateGansuUserCode());
		}
		userCard.setValidStatus(ValidType.VALID.getKey());
		userCard.setCreatedBy(LoginInfoHelper.getLoginId(request));
		userCard.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		
		userCardMapper.insert(userCard);
		
		return userInput.getId();
	}
	
	private  byte[] resizeImage(byte[] src, String ext) throws IOException{
		if (!Strings.isBlank(ext) && ("jpg".equals(ext.toLowerCase()) || "jpeg".equals(ext.toLowerCase()))) {
			return ImageUtil.resizeImage(src, GlobalConstants.IMAGE_RESIZE_WIDTH_SAVE, GlobalConstants.IMAGE_RESIZE_HEIGHT_SAVE, "jpg");
		}
		
		return src;
	}
	
	@Transactional
	public void insertUserInfo(RegistForm ｇegistForm) throws IOException{
		MUser userInput = ｇegistForm.getUser();
		if (userInput != null && !StringUtils.isBlank(userInput.getName())) {
			//UUID
			userInput.setId(UUID.randomUUID().toString());
			//密码加密
			userInput.setPassword(EncryptorUtil.encrypt(userInput.getPassword()));
			//审核状态：待审核
			userInput.setCheckStatus(UserCheckStatusType.NEW.getKey());
			//申请时间
			userInput.setApplicationDate(LocalDateUtility.getCurrentDateTimeString());
			//会员类型
			userInput.setUserType(UserType.PERSON_GANSU.getKey());
			//有效状态
			userInput.setValidStatus(ValidType.INVALID.getKey());
			//删除状态
			userInput.setDeleted(DeleteType.UNDELETED.getkey());
			
			userMapper.insert(userInput);
			
			MUserExtend userExtendInput = new MUserExtend();
			userExtendInput.setId(userInput.getId());
			userExtendMapper.insert(userExtendInput);
			
//			MUserCard userCard = new MUserCard();
//			//UUID
//			userCard.setId(UUID.randomUUID().toString());
//			userCard.setUserId(userInput.getId());
//			userCard.setValidStatus(ValidType.VALID.getKey());
//			//G20240704000001
//			userCard.setUserCode(EncryptorUtil.generateGansuUserCode());
//			//userCard.setCreatedBy(userInput.getId());
//			userCard.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
//			
//			userCardMapper.insert(userCard);
		}
	}
	
	@Transactional
	public List<AdminUserImportResult> importUserInfo(HttpServletRequest request, List<UserInfo> userInfoList, ImportUserUpdateType updateType) throws IOException{
		List<AdminUserImportResult> result = new ArrayList<AdminUserImportResult>();
		for(UserInfo userInfo:userInfoList) {
			AdminUserImportResult importResult = checkUserInfo(userInfo, updateType);
			result.add(importResult);
			if (importResult.getExecuteReturnType() == ExecuteReturnType.NG) {
				continue;
			}
			
			MUser userInput = userInfo.getUser();
			//UUID
			userInput.setId(UUID.randomUUID().toString());
			userInput.setUserType(userInfo.getUserCode().startsWith("M") ? UserType.PERSON_CHINA.getKey():UserType.PERSON_GANSU.getKey());
			//审核状态：审核通过
			userInput.setCheckStatus(UserCheckStatusType.PASS.getKey());
			//申请时间
			userInput.setApplicationDate(LocalDateUtility.getCurrentDateTimeString());
			//有效状态
			userInput.setValidStatus(ValidType.VALID.getKey());
			//删除状态
			userInput.setDeleted(DeleteType.UNDELETED.getkey());
//			//入会时间
//			if(!Strings.isBlank(userInput.getRegistDate())) {
//				userInput.setRegistDate(userInput.getRegistDate()+" 00:00:00");
//			}
//			//有效日期
//			if(!Strings.isBlank(userInput.getValidEndDate())) {
//				userInput.setValidEndDate(userInput.getValidEndDate()+" 23:59:59");
//			}
			userInput.setCreatedBy(LoginInfoHelper.getLoginId(request));
			userInput.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
			userMapper.insert(userInput);
			
			MUserExtend userExtendInput = userInfo.getUserExtend();
			userExtendInput.setId(userInput.getId());
			userExtendMapper.insert(userExtendInput);
			
			MUserCard userCard = userInfo.getUserCard();
			//UUID
			userCard.setId(UUID.randomUUID().toString());
			userCard.setUserId(userInput.getId());
			userCard.setValidStatus(ValidType.VALID.getKey());
			userCard.setCreatedBy(LoginInfoHelper.getLoginId(request));
			userCard.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
			userCardMapper.insert(userCard);
		}
		
		return result;
	}
	
	public AdminUserImportResult checkUserInfo(UserInfo userInfo, ImportUserUpdateType updateType) {
		AdminUserImportResult importResult = new AdminUserImportResult();
		String msg = "";
		//会员登记号
		ColumnInfoForm clmForm = T105MUserCard.getColumnInfo(T105MUserCard.COL_USER_CODE);
		if (Strings.isBlank(userInfo.getUserCode())) {
			msg += getContext("userImport.requiredCheck.msg", clmForm.getLabelName()) + "|";
		}else if(!CommonUtil.checkUserCode(userInfo.getUserCode())) {
			 msg += getContext("userImport.valideCheck.msg", clmForm.getLabelName()) + "|";
		 }else if (updateType == ImportUserUpdateType.INSERT_ONLY && 
				 isUserCodeExist(userInfo.getUserCode())) {
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
		 if (!Strings.isBlank(userInfo.getUser().getCertificateCode()) && 
				 userInfo.getUser().getCertificateCode().length() > 18) {
			 userInfo.getUser().setCertificateCode(null);
		 }
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
			 userInfo.getUser().setBirth(null);
			 if (date.length == 3) {
				 date[0] = date[0].replaceAll("[^0-9]", "");
				 date[1] = StringUtility.padLeft(date[1].replaceAll("[^0-9]", ""), 2, "0");
				 date[2] = StringUtility.padLeft(date[2].replaceAll("[^0-9]", ""), 2, "0");
				 if (!Strings.isBlank(date[0]) && date[0].length() == 4) {
					 userInfo.getUser().setBirth(date[0]+"-"+date[1]+"-"+date[2]);
				 }
			 }
		 }
		//专业
		//通信地址（工作单位、详细联系地址、邮编）"
		//通信地址
		//邮编
		//﹡电子信箱
		//﹡联系电话（手机）
		 if (!Strings.isBlank(userInfo.getUser().getPhone()) && isPhoneNumberExist(userInfo.getUser().getPhone())) {
			 clmForm = T100MUser.getColumnInfo(T100MUser.COL_PHONE);
			 msg += getContext("userImport.existedCheck.msg", clmForm.getLabelName()) + "|";
		 }
		 
		//审批日期（YYYY-MM-DD） 2013|2019/7/2|2022-03-01|2015.8.7|2014.12|1905-07-05
		 String registDate = userInfo.getUser().getRegistDate();
		 userInfo.getUser().setRegistDate(null);
		 if (!Strings.isBlank(registDate)) {
			 registDate = registDate.replace(".", "-").replace("/", "-");
			 String[] date = registDate.split("-");
			 if (date.length == 1) {
				 date = new String[] {date[0], "01", "01"};
			 }else if (date.length == 2) {
				 date = new String[] {date[0], date[1], "01"};
			 }
			 date[0] = date[0].replaceAll("[^0-9]", "");
			 date[1] = StringUtility.padLeft(date[1].replaceAll("[^0-9]", ""), 2, "0");
			 date[2] = StringUtility.padLeft(date[2].replaceAll("[^0-9]", ""), 2, "0");
			 LocalDate localDate = DateUtility.parseDate(date[0]+"-"+date[1]+"-"+date[2], DateFormat.UUUUHMMHDD.getFormat());
			 if (localDate != null) {
				 userInfo.getUser().setRegistDate(DateUtility.formatDate(localDate, DateFormat.UUUUHMMHDD.getFormat())+" 00:00:00");
			 }
		 }
		 
		//续费时间（YYYY-MM-DD） 2023|2020/8/7总会续费|2017年省学会表格要求更改|2023/9/17神志病
		 String validStartDate = userInfo.getUser().getValidStartDate();
		 userInfo.getUser().setValidStartDate(null);
		 if (!Strings.isBlank(validStartDate)) {
			 validStartDate = validStartDate.replace(".", "-").replace("/", "-");
			 String[] date = validStartDate.split("-");
			 if (date.length == 3) {
				 date[0] = date[0].replaceAll("[^0-9]", "");
				 date[1] = StringUtility.padLeft(date[1].replaceAll("[^0-9]", ""), 2, "0");
				 date[2] = StringUtility.padLeft(date[2].replaceAll("[^0-9]", ""), 2, "0");
				 validStartDate = date[0]+"-"+date[1]+"-"+date[2];
				 LocalDate localDate = DateUtility.parseDate(validStartDate, DateFormat.UUUUHMMHDD.getFormat());
				 if (localDate != null) {
					 userInfo.getUser().setValidStartDate(DateUtility.formatDate(localDate, DateFormat.UUUUHMMHDD.getFormat())+" 00:00:00");
					 userInfo.getUser().setValidEndDate(DateUtility.formatDate(localDate.plusYears(5), DateFormat.UUUUHMMHDD.getFormat())+" 00:00:00");
					 if (Strings.isBlank(userInfo.getUser().getRegistDate()) || userInfo.getUser().getRegistDate().startsWith("1905")) {
						 userInfo.getUser().setRegistDate(DateUtility.formatDate(localDate.plusYears(-5), DateFormat.UUUUHMMHDD.getFormat())+" 00:00:00");
					 }
				 }
			 }
		 }
		 
		if (!Strings.isBlank(msg)) {
			importResult.setExecuteReturnType(ExecuteReturnType.NG);
			importResult.setResult(getContext("userImport.line.Check.msg", String.valueOf(userInfo.getIndex()), msg));;
		}else {
			importResult.setExecuteReturnType(ExecuteReturnType.OK);
			importResult.setResult(getContext("userImport.line.Check.msg", String.valueOf(userInfo.getIndex()), getContext("userImport.success.msg")));
		}
		
		return importResult;
	}
	
	@Transactional
	public void deleteUserInfo(HttpServletRequest request, String userId) throws IOException{
		String adminUserId = null;
		MAdmin adminUserInfo = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		if (adminUserInfo != null) {
			adminUserId = adminUserInfo.getId();
		}
		
		MUser user = new MUser();
		user.setId(userId);
		user.setDeleted(DeleteType.DELETED.getkey());
		user.setUpdatedBy(adminUserId);
		user.setUpdatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	@Transactional
	public void updateAdminUserInfo(AdminUserDetailForm userDetailForm) throws IOException{
		MAdmin mAdmin = adminMapper.selectByPrimaryKey(userDetailForm.getUserInfo().getUser().getId());
		mAdmin.setName(userDetailForm.getUserInfo().getUser().getName());
		mAdmin.setUserType(userDetailForm.getUserInfo().getUser().getUserType());
		mAdmin.setPhone(userDetailForm.getUserInfo().getUser().getPhone());
		mAdmin.setMail(userDetailForm.getUserInfo().getUser().getMail());
		if (userDetailForm.getPhotoFile() != null && !StringUtils.isBlank(userDetailForm.getPhotoFile().getOriginalFilename())) {
			mAdmin.setPhoto(userDetailForm.getPhotoFile().getBytes());
			mAdmin.setPhotoExt(userDetailForm.getPhotoFile().getOriginalFilename().split("\\.")[1]);
		}
		adminMapper.updateByPrimaryKeySelective(mAdmin);
	}
	
	@Transactional
	public void insertAdminUserInfo(HttpServletRequest request, AdminUserDetailForm userDetailForm) throws IOException{
		MAdmin mAdmin = new MAdmin();
		//G20240704000001
		mAdmin.setId("A"+LocalDateUtility.getCurrentDateString(DatePattern.UUUUMMDD)+LocalDateUtility.formatTime(LocalTime.now(), TimePattern.HHMISS));
		mAdmin.setName(userDetailForm.getUserInfo().getUser().getName());
		mAdmin.setPassword(EncryptorUtil.encrypt(userDetailForm.getUserInfo().getUser().getPassword()));
		mAdmin.setUserType(userDetailForm.getUserInfo().getUser().getUserType());
		mAdmin.setPhone(userDetailForm.getUserInfo().getUser().getPhone());
		mAdmin.setMail(userDetailForm.getUserInfo().getUser().getMail());
		if (userDetailForm.getPhotoFile() != null && !StringUtils.isBlank(userDetailForm.getPhotoFile().getOriginalFilename())) {
			mAdmin.setPhoto(userDetailForm.getPhotoFile().getBytes());
			mAdmin.setPhotoExt(userDetailForm.getPhotoFile().getOriginalFilename().split("\\.")[1]);
		}
		mAdmin.setCreatedBy(LoginInfoHelper.getLoginId(request));
		mAdmin.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		
		adminMapper.insert(mAdmin);
	}
	
	public void exportUserInfo(HttpServletRequest request, HttpServletResponse response, UserSearchForm pageForm) throws IOException{
		List<String> headerList = new ArrayList<String>();
		headerList.add(T105MUserCard.getColumnInfo(T105MUserCard.COL_USER_CODE).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_USER_TYPE).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_NAME).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_SEX).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_BIRTH).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_PHONE).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_MAIL).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_NATIONALITY).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_POLITICAL).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_EDU_DEGREE).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_BACHELOR).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_POSITION).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER_TYPE).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_EMPLOYER).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_JOB_TITLE).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_CERTIFICATE_TYPE).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_CERTIFICATE_CODE).getLabelName());
//		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_AREA).getLabelName());
//		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_AREA_SUB).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_ADDRESS).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_POSTAL_CODE).getLabelName());
//		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_APPLICATION_DATE).getLabelName());
//		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_CHECK_DATE).getLabelName());
//		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_CHECK_STATUS).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_VALID_STATUS).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_REGIST_DATE).getLabelName());
		headerList.add("有效日期");
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_SOCIETY_TYPE).getLabelName());
//		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_GROUP_NAME).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_MEMBERSHIP_PATH).getLabelName());
		headerList.add(T100MUser.getColumnInfo(T100MUser.COL_FOCUS_ON).getLabelName());
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_INTRODUCER1).getLabelName());
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_INTRODUCER2).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PHOTO).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PHOTO_EXT).getLabelName());
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_MAJOR).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_EDUCATIONAL_AT).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_EDUCATIONAL_AT_EXT).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_BACHELOR_AT).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_BACHELOR_AT_EXT).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_VOCATIONAL_AT).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_VOCATIONAL_AT_EXT).getLabelName());
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_RESEARCH_DIR).getLabelName().replace("<br/>", ""));
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_LEARN_EXPERIENCE).getLabelName().replace("<br/>", ""));
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_WORK_EXPERIENCE).getLabelName().replace("<br/>", ""));
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_PAPERS).getLabelName().replace("<br/>", ""));
		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_HONORS).getLabelName().replace("<br/>", ""));
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_APPLICATION_FORM).getLabelName());
//		headerList.add(T101MUserExtend.getColumnInfo(T101MUserExtend.COL_APPLICATION_FORM_EXT).getLabelName());

		UserListOutput userListOutput = getUserList(request, pageForm);
	    List<UserInfo> userInfoList = userListOutput.getUserList();
	    List<List<String>> excelData = new ArrayList<List<String>>();
	    excelData.add(headerList);
	    
	    for(UserInfo userInfo:userInfoList) {
	    	List<String> list = new ArrayList<String>();
	    	list.add(nonNull(userInfo.getUserCode()));//T105MUserCard.COL_USER_CODE
	    	list.add(nonNull(userInfo.getUserTypeName()));//T100MUser.COL_USER_TYPE
	    	list.add(nonNull(userInfo.getUser().getName()));//T100MUser.COL_NAME
	    	list.add(nonNull(userInfo.getSexName()));//T100MUser.COL_SEX
	    	list.add(nonNull(userInfo.getUser().getBirth()));//T100MUser.COL_BIRTH
	    	list.add(nonNull(userInfo.getUser().getPhone()));//T100MUser.COL_PHONE
	    	list.add(nonNull(userInfo.getUser().getMail()));//T100MUser.COL_MAIL
	    	list.add(nonNull(userInfo.getNationalityName()));//T100MUser.COL_NATIONALITY
	    	list.add(nonNull(userInfo.getPoliticalName()));//T100MUser.COL_POLITICAL
	    	list.add(nonNull(userInfo.getEduDegreeName()));//T100MUser.COL_EDU_DEGREE
	    	list.add(nonNull(userInfo.getBachelorName()));//T100MUser.COL_BACHELOR
	    	list.add(nonNull(userInfo.getPositionName()));//T100MUser.COL_POSITION
	    	list.add(nonNull(userInfo.getEmployerTypeName()));//T100MUser.COL_EMPLOYER_TYPE
	    	list.add(nonNull(userInfo.getUser().getEmployer()));//T100MUser.COL_EMPLOYER
	    	list.add(nonNull(userInfo.getJobTitleName()));//T100MUser.COL_JOB_TITLE
	    	list.add(nonNull(userInfo.getCertificateTypeName()));//T100MUser.COL_CERTIFICATE_TYPE
	    	list.add(nonNull(userInfo.getUser().getCertificateCode()));//T100MUser.COL_CERTIFICATE_CODE
//	    	list.add(nonNull(userInfo));//T100MUser.COL_AREA
//	    	list.add(nonNull(userInfo));//T100MUser.COL_AREA_SUB
	    	list.add(nonNull(userInfo.getUser().getAddress()));//T100MUser.COL_ADDRESS
	    	list.add(nonNull(userInfo.getUser().getPostalCode()));//T100MUser.COL_POSTAL_CODE
//	    	list.add(nonNull(userInfo));//T100MUser.COL_APPLICATION_DATE
//	    	list.add(nonNull(userInfo));//T100MUser.COL_CHECK_DATE
//	    	list.add(nonNull(userInfo));//T100MUser.COL_CHECK_STATUS
	    	list.add(nonNull(getContext(CommonUtil.getValidStatus(userInfo.getUser().getValidEndDate()).getMsg())));//T100MUser.COL_VALID_STATUS
	    	list.add(nonNull(LocalDateUtility.formatDateZH(nonNull(userInfo.getUser().getRegistDate()))));//T100MUser.COL_REGIST_DATE
//	    	list.add(nonNull(userInfo));//T100MUser.COL_VALID_START_DATE
	    	list.add(nonNull(LocalDateUtility.formatDateZH(nonNull(userInfo.getUser().getValidEndDate()))));//T100MUser.COL_VALID_END_DATE
	    	list.add(nonNull(userInfo.getSocietyTypeName()));//T100MUser.COL_SOCIETY_TYPE
//	    	list.add(nonNull(userInfo));//T100MUser.COL_GROUP_NAME
	    	list.add(nonNull(userInfo.getMembershipPathName()));//T100MUser.COL_MEMBERSHIP_PATH
	    	list.add(nonNull(userInfo.getFocusOnName()));//T100MUser.COL_FOCUS_ON
	    	list.add(nonNull(userInfo.getUserExtend().getIntroducer1()));//T101MUserExtend.COL_INTRODUCER1
	    	list.add(nonNull(userInfo.getUserExtend().getIntroducer2()));//T101MUserExtend.COL_INTRODUCER2
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_PHOTO
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_PHOTO_EXT
	    	list.add(nonNull(userInfo.getUserExtend().getMajor()));//T101MUserExtend.COL_MAJOR
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_EDUCATIONAL_AT
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_EDUCATIONAL_AT_EXT
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_BACHELOR_AT
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_BACHELOR_AT_EXT
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_VOCATIONAL_AT
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_VOCATIONAL_AT_EXT
	    	list.add(nonNull(userInfo.getUserExtend().getResearchDir()));//T101MUserExtend.COL_RESEARCH_DIR
	    	list.add(nonNull(userInfo.getUserExtend().getLearnExperience()));//T101MUserExtend.COL_LEARN_EXPERIENCE
	    	list.add(nonNull(userInfo.getUserExtend().getWorkExperience()));//T101MUserExtend.COL_WORK_EXPERIENCE
	    	list.add(nonNull(userInfo.getUserExtend().getPapers()));//T101MUserExtend.COL_PAPERS
	    	list.add(nonNull(userInfo.getUserExtend().getHonors()));//T101MUserExtend.COL_HONORS
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_APPLICATION_FORM
//	    	list.add(nonNull(userInfo));//T101MUserExtend.COL_APPLICATION_FORM_EXT
	    	
	    	excelData.add(list);
	    
	    }

	    ExcelUtil.exportExcel(response, excelData, "会员一览", "会员一览", 15);
	}
	
	public static String nonNull(String value) {
        return Objects.nonNull(value) ? value : "";
    }
}
