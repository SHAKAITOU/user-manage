package cn.caam.gs.domain.db.custom.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.domain.db.base.entity.MSmsConfig;

@Mapper
public interface OptionalSmsConfigMapper {

	MSmsConfig selectByTemplateType(@Param("templateType") String templateType);

}
