package cn.caam.gs.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.app.common.form.MessageSearchForm;
import cn.caam.gs.app.common.output.MessageListOutput;
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.base.entity.MMessageRead;
import cn.caam.gs.domain.db.base.mapper.MMessageMapper;
import cn.caam.gs.domain.db.base.mapper.MMessageReadMapper;
import cn.caam.gs.domain.db.custom.mapper.OptionalMessageInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService extends BaseService {
	
	@Autowired
	OptionalMessageInfoMapper optionalMessageInfoMapper;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MMessageMapper mMessageMapper;
    
    @Autowired
    MMessageReadMapper messageReadMapper;	

	public MessageListOutput getMessageList(MessageSearchForm pageForm) {
	    MessageListOutput listOutput = new MessageListOutput();
	    listOutput.setCount(optionalMessageInfoMapper.getMessageListCount(pageForm));
	    listOutput.setMessageList(optionalMessageInfoMapper.getMessageList(pageForm));
    	return listOutput;
	}
	
	public MessageListOutput getMessageList(String userId, int limit, int offset) {
        MessageListOutput listOutput = new MessageListOutput();
        listOutput.setCount(optionalMessageInfoMapper.getUserMessageListCount(userId));
        listOutput.setMessageList(optionalMessageInfoMapper.getUserMessageList(userId, limit, offset));
        return listOutput;
    }
	
	public int getUnReadCnt(String userId) {
	    return optionalMessageInfoMapper.getUserMessageUnReadCnt(userId);
	}
	
	public void setMessageRead(String msgId, String userId) {
	    MMessageRead msgRead = optionalMessageInfoMapper.getMessageRead(msgId, userId);
	    if (msgRead == null) {
            msgRead = new MMessageRead();
            msgRead.setId(msgId);
            msgRead.setUserId(userId);
            messageReadMapper.insert(msgRead);
        }
    }
	
	@Transactional
	public void addMessage(MMessage message, MsgType msgType) {
        
	    message.setId(EncryptorUtil.generateMsgId());
	    message.setMsgType(msgType.getKey());
	    message.setRegistDate(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
	    
	    mMessageMapper.insert(message);
	}
	
}
