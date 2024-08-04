package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.domain.db.custom.entity.UserCheckHistoryInfo;

@Mapper
public interface OptionalUserCheckHistoryInfoMapper {

    List<UserCheckHistoryInfo> getUserCheckHistoryList(@Param("userId") String userId);
    
}
