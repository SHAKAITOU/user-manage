package cn.caam.gs.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.app.admin.message.form.AdminMessageSearchForm;
import cn.caam.gs.app.admin.message.output.AdminMessageListOutput;
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.base.mapper.MMessageMapper;
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

	public AdminMessageListOutput getMessageList(AdminMessageSearchForm pageForm) {
	    AdminMessageListOutput listOutput = new AdminMessageListOutput();
	    listOutput.setCount(optionalMessageInfoMapper.getMessageListCount(pageForm));
	    listOutput.setMessageList(optionalMessageInfoMapper.getMessageList(pageForm));
    	return listOutput;
	}
	
	@Transactional
	public void addMessage(MMessage message) {
        
	    message.setId(EncryptorUtil.generateMsgId());
	    message.setMsgType(MsgType.ALL.getKey());
	    message.setRegistDate(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
	    
	    mMessageMapper.insert(message);
	}
	
}
