package cn.caam.gs.domain.db.custom.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionalAuthCodeInfoMapper {

    String selectRecentlyInvalidDateByReceiveBy(String recieved_by);
}
