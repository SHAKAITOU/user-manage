package cn.caam.gs.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import cn.caam.gs.app.admin.adminmanage.form.AdminUserDetailForm;
import cn.caam.gs.app.admin.adminmanage.form.AdminManageSearchForm;
import cn.caam.gs.app.admin.adminmanage.output.AdminUserListOutput;
import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.common.form.UserDetailForm;
import cn.caam.gs.app.user.regist.form.RegistForm;
import cn.caam.gs.app.util.LoginInfoHelper;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.enums.UserType;
import cn.caam.gs.common.enums.ValidType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DatePattern;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.common.util.LocalDateUtility.TimePattern;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.base.mapper.MAdminMapper;
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
		 return optionalUserInfoMapper.isPhoneNumberExist(phoneNumber);
	}
	
	public boolean isEmailExist(String email) {
		return optionalUserInfoMapper.isEmailExist(email);
	}
	
	public void downloadAppliactionForm(String userId, OutputStream  outputStream) throws Exception{
		 UserInfo userInfo = this.getUserInfo(userId);
		 if (userInfo.getUserExtend().getApplicationForm() != null && userInfo.getUserExtend().getApplicationForm().length > 0){
			IOUtils.copy(new ByteArrayInputStream(userInfo.getUserExtend().getApplicationForm()), outputStream);
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
	public void updateUserInfo(UserDetailForm userDetailForm) throws IOException{
		UserInfo userInfo = userDetailForm.getUserInfo();
		if (userInfo.getUser() != null && !StringUtils.isBlank(userInfo.getUser().getName())) {
			MUser userInput = userInfo.getUser();
			MUser user = userMapper.selectByPrimaryKey(userInput.getId());
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
			
			userMapper.updateByPrimaryKey(user);
		}
		if (userInfo.getUserExtend() != null && !StringUtils.isBlank(userInfo.getUserExtend().getMajor())) {
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
				userExtendMapper.updateByPrimaryKeyWithBLOBs(userExtend);
			}else {
				userExtendMapper.insert(userExtend);
			}
		}
		
		MUser user = userMapper.selectByPrimaryKey(userInfo.getUser().getId());
		if (UserCheckStatusType.RETURN.getKey().equals(user.getCheckStatus())) {
			user.setCheckStatus(UserCheckStatusType.WAIT_FOR_REVIEW.getKey());
			userMapper.updateByPrimaryKeySelective(user);
		}
	}
	
	@Transactional
	public void insertUserInfo(RegistForm ｇegistForm) throws IOException{
		MUser userInput = ｇegistForm.getUser();
		if (userInput != null && !StringUtils.isBlank(userInput.getName())) {
			//G20240704000001
			userInput.setId("G"+LocalDateUtility.getCurrentDateString(DatePattern.UUUUMMDD)+LocalDateUtility.formatTime(LocalTime.now(), TimePattern.HHMISS));
			//密码加密
			userInput.setPassword(EncryptorUtil.encrypt(userInput.getPassword()));
			//审核状态：待审核
			userInput.setCheckStatus(CheckStatusType.WAIT_FOR_REVIEW.getKey());
			//申请时间
			userInput.setApplicationDate(LocalDateUtility.getCurrentDateTimeString());
			//会员类型
			userInput.setUserType(UserType.PERSON_REGULAR.getKey());
			//有效状态
			userInput.setValidStatus(ValidType.INVALID.getKey());
			
			userMapper.insert(userInput);
			
			MUserExtend userExtendInput = new MUserExtend();
			userExtendInput.setId(userInput.getId());
			userExtendMapper.insert(userExtendInput);
		}
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
		String adminUserId = null;
		MAdmin adminUserInfo = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		if (adminUserInfo != null) {
			adminUserId = adminUserInfo.getId();
		}
		
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
		mAdmin.setCreatedBy(adminUserId);
		mAdmin.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		
		adminMapper.insert(mAdmin);
	}
}
