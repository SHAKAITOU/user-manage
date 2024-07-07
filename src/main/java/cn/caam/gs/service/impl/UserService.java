package cn.caam.gs.service.impl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.app.user.detail.form.UserDetailForm;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DatePattern;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.base.entity.MImage;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.base.mapper.MAdminMapper;
import cn.caam.gs.domain.db.base.mapper.MUserExtendMapper;
import cn.caam.gs.domain.db.base.mapper.MUserMapper;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService extends BaseService {
	
	@Autowired
	OptionalUserInfoMapper optionalUserInfoMapper;
	
	@Autowired
	MAdminMapper adminMapper;
	
	@Autowired
	MUserMapper userMapper;
	
	@Autowired
	MUserExtendMapper userExtendMapper;

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
	
	public UserInfo getBaseUserInfo(String userId) {
        return optionalUserInfoMapper.getBaseUserInfo(userId);
    }
	
	public MAdmin getLoginAdminInfo(String userCode) {
        return adminMapper.selectByPrimaryKey(userCode);
    }
	
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
			
			if (isUpdate) {
				userExtendMapper.updateByPrimaryKeyWithBLOBs(userExtend);
			}else {
				userExtendMapper.insert(userExtend);
			}
		}
	}
}
