package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.caam.gs.app.admin.message.form.AdminMessageSearchForm;
import cn.caam.gs.domain.db.custom.entity.MessageInfo;

@Mapper
public interface OptionalMessageInfoMapper {

    int getMessageListCount(AdminMessageSearchForm pageForm);
	List<MessageInfo> getMessageList(AdminMessageSearchForm pageForm);
}
