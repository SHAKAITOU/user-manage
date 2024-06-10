package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.custom.entity.UserInfo;

@Mapper
public interface OptionalUserInfoMapper {

    int getUserListCount(MUser user);
	List<UserInfo> getUserList(MUser user);
}
