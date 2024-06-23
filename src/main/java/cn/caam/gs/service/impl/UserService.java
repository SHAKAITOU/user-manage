package cn.caam.gs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.app.admin.usersearch.output.UserListOutput;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.base.mapper.MAdminMapper;
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

	public UserListOutput getUserList(UserSearchForm pageForm) {
	    UserListOutput userListOutput = new UserListOutput();
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
	
	public MAdmin getLoginAdminInfo(String userCode) {
        return adminMapper.selectByPrimaryKey(userCode);
    }
}
