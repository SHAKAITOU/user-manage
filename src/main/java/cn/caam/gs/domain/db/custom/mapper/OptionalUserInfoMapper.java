package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import cn.caam.gs.domain.db.custom.entity.UserInfo;

@Mapper
public interface OptionalUserInfoMapper {

    int getUserListCount(MUser user);
	List<UserInfo> getUserList(MUser user);
	
	UserInfo getUserInfo(@Param("userId") String userId);
	
	UserInfo getLoginUserInfo(@Param("userCode") String userCode);
	
	MUserExtend getUserPhoto(@Param("userId") String userId);
}
