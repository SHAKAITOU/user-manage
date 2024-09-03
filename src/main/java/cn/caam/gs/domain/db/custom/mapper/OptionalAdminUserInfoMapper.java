package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.app.admin.adminmanage.form.AdminManageSearchForm;
import cn.caam.gs.domain.db.custom.entity.AdminUserInfo;

@Mapper
public interface OptionalAdminUserInfoMapper {

	int getUserListCount(AdminManageSearchForm pageForm);
	
	List<AdminUserInfo> getUserList(AdminManageSearchForm pageForm);
	
	AdminUserInfo getUserInfo(@Param("userId") String userId);
	
}
