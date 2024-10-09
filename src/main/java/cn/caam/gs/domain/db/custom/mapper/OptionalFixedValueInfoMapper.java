package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.domain.db.base.entity.MFixedValue;
import cn.caam.gs.domain.db.base.entity.MUser;

@Mapper
public interface OptionalFixedValueInfoMapper {

    int getUserListCount(MUser user);
	List<MFixedValue> getAll();
	
	List<MFixedValue> selectByName(@Param("code") String code, @Param("name") String name);
}
