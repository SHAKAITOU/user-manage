package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.caam.gs.domain.db.custom.entity.UserTypeSettingsInfo;

@Mapper
public interface OptionalUserTypeSettingsInfoMapper {

    List<UserTypeSettingsInfo> getUserTypeSettingsList();
    
}
