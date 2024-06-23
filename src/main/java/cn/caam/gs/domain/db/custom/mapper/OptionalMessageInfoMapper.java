package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.caam.gs.app.common.form.MessageSearchForm;
import cn.caam.gs.domain.db.base.entity.MMessageRead;
import cn.caam.gs.domain.db.custom.entity.MessageInfo;

@Mapper
public interface OptionalMessageInfoMapper {

    int getMessageListCount(MessageSearchForm pageForm);
	List<MessageInfo> getMessageList(MessageSearchForm pageForm);
	
	int getUserMessageListCount(String userId);
    List<MessageInfo> getUserMessageList(
            @Param("userId") String userId, 
            @Param("limit") int limit,
            @Param("offset") int offset);
    
    int getUserMessageUnReadCnt(@Param("userId") String userId);
    
    MMessageRead getMessageRead(
            @Param("msgId") String msgId,
            @Param("userId") String userId);
}
