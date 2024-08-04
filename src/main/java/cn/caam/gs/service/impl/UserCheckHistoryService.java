package cn.caam.gs.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.app.common.output.UserCheckHistoryListOutput;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.enums.SexType;
import cn.caam.gs.common.enums.UserCheckStatusType;
import cn.caam.gs.common.enums.ValidType;
import cn.caam.gs.common.util.CommonUtil;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.config.SmsConfig;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserCheckHistory;
import cn.caam.gs.domain.db.base.mapper.MUserCheckHistoryMapper;
import cn.caam.gs.domain.db.base.mapper.MUserMapper;
import cn.caam.gs.domain.db.custom.mapper.OptionalUserCheckHistoryInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserCheckHistoryService extends BaseService {
	
	@Autowired
	OptionalUserCheckHistoryInfoMapper optionalUserCheckHistoryInfoMapper;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MUserCheckHistoryMapper mMUserCheckHistoryMapper;
	
	@Autowired
	MUserMapper userMapper;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
    SmsConfig smsConfig;
    
	public UserCheckHistoryListOutput getUserCheckHistoryList(String userId) {
		UserCheckHistoryListOutput listOutput = new UserCheckHistoryListOutput();
//	    listOutput.setCount(optionalMessageInfoMapper.getMessageListCount(pageForm));
	    listOutput.setUserCheckHistoryList(optionalUserCheckHistoryInfoMapper.getUserCheckHistoryList(userId));
    	return listOutput;
	}
	
	@Transactional
	public boolean reviewMulti(MUserCheckHistory mUserCheckHistory, String[] userIds) throws Exception{
		for(int i=0; i<userIds.length; i++) {
			review(mUserCheckHistory, userIds[i]);
		}
		return true;
	}
	
	@Transactional
	public boolean review(MUserCheckHistory mUserCheckHistory, String userId) throws Exception{
		MUser userDb = userMapper.selectByPrimaryKey(userId);
		if (userDb == null) {
			throw new Exception(messageSourceUtil.getContext("login.fail.msg.notexist"));
		}
		if (!UserCheckStatusType.WAIT_FOR_REVIEW.getKey().equals(userDb.getCheckStatus())) {
			throw new Exception(messageSourceUtil.getContext("admin.userReview.review.already_msg"));
		}
		
		mUserCheckHistory.setId(EncryptorUtil.generateUserReviewId());
		mUserCheckHistory.setUserId(userId);
		mUserCheckHistory.setCheckDate(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		MAdmin adminUserInfo = (MAdmin)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
		if (adminUserInfo != null) {
			mUserCheckHistory.setCreatedBy(adminUserInfo.getId());
		}
		mUserCheckHistory.setCreatedAt(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
		
		mMUserCheckHistoryMapper.insert(mUserCheckHistory);
		
		
		MUser user = new MUser();
        user.setId(mUserCheckHistory.getUserId());
        user.setCheckStatus(mUserCheckHistory.getCheckStatus());
        user.setCheckDate(mUserCheckHistory.getCheckDate());
        userDb.setCheckStatus(mUserCheckHistory.getCheckStatus());
        if (UserCheckStatusType.REFUSED.getKey().equals(user.getCheckStatus())) {//审核不通过
        	user.setValidStatus(ValidType.INVALID.getKey());
        }
        userDb.setCheckDate(mUserCheckHistory.getCheckDate());
        userMapper.updateByPrimaryKeySelective(user);
        
        MMessage message = new MMessage();
        /*
		 *admin.userReview.msg.reviewOk.message.title		= 通知：会员信息审核通过
		 *admin.userReview.msg.reviewOk.message.body		= 尊敬的会员〖{0}〗{1}，<BR>您的会员信息审核通过，请登录管理系统查阅。
         */
        message.setTitle(getReviewMessageTitle(userDb));
        String msg = getReviewMessageBody(userDb, mUserCheckHistory);
        message.setMsg(msg);
        message.setUserId(mUserCheckHistory.getUserId());
        messageService.addMessage(message, MsgType.PERSONAL);
        
        //发送审核结果短信
        if (CommonUtil.isMobilePhoneNumber(userDb.getPhone())) {
//        	HuaWeiSMSUtil.sendUserReviewMessage(smsConfig, smsConfig.getUserReviewTemplateId(), userDb.getPhone(), msg);
        }
		
		return true;
	}
	
	public String getReviewMessageTitle(MUser user) {
		if (UserCheckStatusType.PASS.getKey().equals(user.getCheckStatus())) {
			return getContext("admin.userReview.msg.reviewOk.message.title");
		}else if (UserCheckStatusType.REFUSED.getKey().equals(user.getCheckStatus())) {
			return getContext("admin.userReview.msg.reviewReturn.message.title");
		}else if (UserCheckStatusType.RETURN.getKey().equals(user.getCheckStatus())) {
			return getContext("admin.userReview.msg.reviewNg.message.title");
		}
		return "";
	}

	public String getReviewMessageBody(MUser user, MUserCheckHistory mUserCheckHistory) {
		if (UserCheckStatusType.PASS.getKey().equals(user.getCheckStatus())) {
			return  getContext("admin.userReview.msg.reviewOk.message.body", 
	        		user.getName(),
	                getContext(SexType.keyOf(user.getSex()).getMsg()));
		}else if (UserCheckStatusType.REFUSED.getKey().equals(user.getCheckStatus())) {
			return  getContext("admin.userReview.msg.reviewReturn.message.body", 
	        		user.getName(),
	                getContext(SexType.keyOf(user.getSex()).getMsg()),
	                mUserCheckHistory.getMemo());
		}else if (UserCheckStatusType.RETURN.getKey().equals(user.getCheckStatus())) {
			return  getContext("admin.userReview.msg.reviewNg.message.body", 
	        		user.getName(),
	                getContext(SexType.keyOf(user.getSex()).getMsg()),
	                mUserCheckHistory.getMemo());
		}
		return "";
	}
	
}
