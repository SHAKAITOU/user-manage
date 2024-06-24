package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.app.admin.usersearch.form.UserSearchForm;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.custom.entity.UserInfo;

@Mapper
public interface OptionalUserInfoMapper {

    int getUserListCount(UserSearchForm pageForm);
	List<UserInfo> getUserList(UserSearchForm pageForm);
	
	UserInfo getUserInfo(@Param("userId") String userId);
	
	UserInfo getLoginUserInfo(@Param("userCode") String userCode);
	
	UserInfo getBaseUserInfo(@Param("userId") String userId);
	
	MUserExtend getUserPhoto(@Param("userId") String userId);
}
