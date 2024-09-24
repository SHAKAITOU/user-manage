package cn.caam.gs.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.DeleteType;
import cn.caam.gs.common.enums.DownloadFileType;
import cn.caam.gs.common.enums.SmsConfigType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.enums.ValidType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DatePattern;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.common.util.LocalDateUtility.TimePattern;
import cn.caam.gs.common.util.PasswordGenerator;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserCard;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.base.mapper.MAdminMapper;
import cn.caam.gs.domain.db.base.mapper.MUserCardMapper;
import cn.caam.gs.domain.db.base.mapper.MUserExtendMapper;
import cn.caam.gs.domain.db.base.mapper.MUserMapper;
import cn.caam.gs.domain.db.custom.entity.AdminUserInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalAdminUserInfoMapper;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

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

	public UserListOutput getUserList(UserSearchForm pageForm) {
	    UserListOutput userListOutput = new UserListOutput();
	    pageForm.setExpiredSoonDate(
	            LocalDateUtility.addDate(LocalDateUtility.getCurrentDateString(), DatePattern.UUUUHMMHDD, 5));
	    pageForm.setVaryExpiredDate(LocalDateUtility.addDate(LocalDateUtility.getCurrentDateString(), DatePattern.UUUUHMMHDD, -60));
	    userListOutput.setCount(optionalUserInfoMapper.getUserListCount(pageForm));
	    userListOutput.setUserList(optionalUserInfoMapper.getUserList(pageForm));
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
	
	public void downloadAppliactionForm(String userId, OutputStream  outputStream) throws Exception{
		 UserInfo userInfo = this.getUserInfo(userId);
		 if (userInfo.getUserExtend().getApplicationForm() != null && userInfo.getUserExtend().getApplicationForm().length > 0){
			IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getApplicationForm()), outputStream);
		 }
	}
	
	private String createDownloadImageFileName(String userCode, String name, String ext)  throws Exception{
		if (Strings.isBlank(ext)) {
			ext = "jpg";
		}
		return URLEncoder.encode(userCode+"_"+name+"."+ext,"UTF-8");
	}
	public void downloadFile(String userId, DownloadFileType fileType, HttpServletResponse response) throws Exception{
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
			 String filename = URLEncoder.encode(GlobalConstants.APPLICATION_FORM_NAME+".pdf","UTF-8");
			 response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
			 response.setContentType("application/pdf;charset=UTF-8");
			 if (userInfo.getUserExtend().getApplicationForm() != null && userInfo.getUserExtend().getApplicationForm().length > 0){
				IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getApplicationForm()), response.getOutputStream());
			 }
			 
		 }else if (fileType == DownloadFileType.APPLICATION_FORM_TEMPLATE) {//申请资料模版word
			 response.setHeader(HttpHeaders.PRAGMA, "No-cache");
			response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
			String filename = URLEncoder.encode(GlobalConstants.APPLICATION_FORM_NAME+".docx","UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="+filename+";"+"filename*=utf-8''"+filename);
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=UTF-8");
			outputAppliactionForm(userId, response.getOutputStream());
			
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
				userExtend.setPhoto(userDetailForm.getPhotoFile().getBytes());
				userExtend.setPhotoExt(userDetailForm.getPhotoFile().getOriginalFilename().split("\\.")[1]);
			}
			//学历证书附件
			if (userDetailForm.getEducationalAtFile() != null && !StringUtils.isBlank(userDetailForm.getEducationalAtFile().getOriginalFilename())) {
				userExtend.setEducationalAt(userDetailForm.getEducationalAtFile().getBytes());
				userExtend.setEducationalAtExt(userDetailForm.getEducationalAtFile().getOriginalFilename().split("\\.")[1]);
			}
			//学位证书附件
			if (userDetailForm.getBachelorAtFile() != null && !StringUtils.isBlank(userDetailForm.getBachelorAtFile().getOriginalFilename())) {
				userExtend.setBachelorAt(userDetailForm.getBachelorAtFile().getBytes());
				userExtend.setBachelorAtExt(userDetailForm.getBachelorAtFile().getOriginalFilename().split("\\.")[1]);
			}
			//职业证书附件
			if (userDetailForm.getVocationalAtFile() != null && !StringUtils.isBlank(userDetailForm.getVocationalAtFile().getOriginalFilename())) {
				userExtend.setVocationalAt(userDetailForm.getVocationalAtFile().getBytes());
				userExtend.setVocationalAtExt(userDetailForm.getVocationalAtFile().getOriginalFilename().split("\\.")[1]);
			}
			
			//入会申请表
			if (userDetailForm.getApplicationFormFile() != null && !StringUtils.isBlank(userDetailForm.getApplicationFormFile().getOriginalFilename())) {
				userExtend.setApplicationForm(userDetailForm.getApplicationFormFile().getBytes());
				userExtend.setApplicationFormExt(userDetailForm.getApplicationFormFile().getOriginalFilename().split("\\.")[1]);
			}
			
			if (isUpdate) {
//				userExtendMapper.updateByPrimaryKeyWithBLOBs(userExtend);
				userExtendMapper.updateByPrimaryKeySelective(userExtend);
			}else {
				userExtendMapper.insert(userExtend);
			}
		}
		
		//入会申请表上传之后，审核返回-》等待审核
		if (userDetailForm.getApplicationFormFile() != null && !StringUtils.isBlank(userDetailForm.getApplicationFormFile().getOriginalFilename())) {
			MUser user = userMapper.selectByPrimaryKey(userInfo.getUser().getId());
			if (UserCheckStatusType.NEW.getKey().equals(user.getCheckStatus()) || 
					UserCheckStatusType.RETURN.getKey().equals(user.getCheckStatus())) {
				user.setCheckStatus(UserCheckStatusType.WAIT_FOR_REVIEW.getKey());
				userMapper.updateByPrimaryKeySelective(user);
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
		userInput.setCheckStatus(CheckStatusType.PASS.getKey());
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
}
