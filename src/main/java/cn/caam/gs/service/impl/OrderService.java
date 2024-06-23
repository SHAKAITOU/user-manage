package cn.caam.gs.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.user.order.form.OrderForm;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.enums.BillStatusType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.enums.SexType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MImage;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.mapper.MImageMapper;
import cn.caam.gs.domain.db.base.mapper.MOrderMapper;
import cn.caam.gs.domain.db.base.mapper.MUserMapper;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.entity.UserInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalOrderInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService extends BaseService {
	
	@Autowired
	OptionalOrderInfoMapper optionalOrderInfoMapper;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	MOrderMapper mOrderMapper;
	
	@Autowired
	MUserMapper userMapper;
	
	@Autowired
	MImageMapper mImageMapper;
	
	@Autowired
	MessageService messageService;

	public OrderListOutput getOrderList(OrderSearchForm pageForm) {
	    OrderListOutput listOutput = new OrderListOutput();
	    listOutput.setCount(optionalOrderInfoMapper.getOrderListCount(pageForm));
	    listOutput.setOrderList(optionalOrderInfoMapper.getOrderList(pageForm));
    	return listOutput;
	}
	
	public OrderInfo getOrder(String orderId) {
        return optionalOrderInfoMapper.getOrder(orderId);
    }
	
	public int getOrderWaitCount() {
	    return optionalOrderInfoMapper.getOrderWaitCount();
	}
    
	public int getOrderReviewCount() {
	    return optionalOrderInfoMapper.getOrderReviewCount();
	}

	@Transactional
	public void addOrder(OrderForm pageForm) throws IOException {
	    UserInfo userInfo = (UserInfo)request.getSession().getAttribute(SessionConstants.LOGIN_INFO.getValue());
	    MOrder order = pageForm.getOrder();
	    String orderId = EncryptorUtil.generateOrderId();
	    order.setId(orderId);
	    order.setUserId(userInfo.getId());
	    order.setPayDate(LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
	    order.setCheckStatus(CheckStatusType.WAIT_FOR_REVIEW.getKey());
	    order.setBillStatus(BillStatusType.TO_BE_INVOICED.getKey());
	    mOrderMapper.insert(order);
	    
	    MImage image = new MImage();
	    image.setId(orderId);
	    image.setOrderPhoto(pageForm.getOrderPhotoFile().getBytes());
	    image.setOrderPhotoExt(pageForm.getOrderPhotoFile().getOriginalFilename().split("\\.")[1]);
	    mImageMapper.insert(image);
	}
	
	@Transactional
    public void updateOrderToReview(IdForm pageForm) {
        MOrder orderDb = mOrderMapper.selectByPrimaryKey(pageForm.getId());
        MUser user = userMapper.selectByPrimaryKey(orderDb.getUserId());
        MOrder order = new MOrder();
        order.setId(pageForm.getId());
        order.setCheckStatus(CheckStatusType.REVIEW.getKey());
        mOrderMapper.updateByPrimaryKeySelective(order);
        
        MMessage message = new MMessage();
        message.setTitle(getContext("admin.order.msg.putToReview.message.title", pageForm.getId()));
        String msgBody = getContext("admin.order.msg.putToReview.message.body", 
                user.getName(),
                getContext(SexType.keyOf(user.getSex()).getMsg()),
                pageForm.getId(), 
                LocalDateUtility.getCurrentDateTimeString(DateTimePattern.UUUUHMMHDDHHQMIQSS));
        message.setMsg(msgBody);
        message.setUserId(orderDb.getUserId());
        messageService.addMessage(message, MsgType.PERSONAL);
    }
	
}
