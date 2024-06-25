package cn.caam.gs.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.app.admin.userbill.form.BillForm;
import cn.caam.gs.app.admin.userbill.form.BillSearchForm;
import cn.caam.gs.app.admin.userbill.form.RefundSearchForm;
import cn.caam.gs.app.admin.userorder.form.ReviewOkForm;
import cn.caam.gs.app.common.form.IdForm;
import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.user.order.form.OrderForm;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.enums.BillStatusType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.enums.FixedValueType;
import cn.caam.gs.common.enums.MsgType;
import cn.caam.gs.common.enums.ReFundStatusType;
import cn.caam.gs.common.enums.SexType;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MBill;
import cn.caam.gs.domain.db.base.entity.MImage;
import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.mapper.MBillMapper;
import cn.caam.gs.domain.db.base.mapper.MImageMapper;
import cn.caam.gs.domain.db.base.mapper.MOrderMapper;
import cn.caam.gs.domain.db.base.mapper.MUserMapper;
import cn.caam.gs.domain.db.custom.entity.FixValueInfo;
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
	MBillMapper billMapper;
	
	@Autowired
	MessageService messageService;

	public OrderListOutput getOrderList(OrderSearchForm pageForm) {
	    OrderListOutput listOutput = new OrderListOutput();
	    listOutput.setCount(optionalOrderInfoMapper.getOrderListCount(pageForm));
	    listOutput.setOrderList(optionalOrderInfoMapper.getOrderList(pageForm));
    	return listOutput;
	}
	
	public OrderListOutput getBillList(BillSearchForm pageForm) {
        OrderListOutput listOutput = new OrderListOutput();
        listOutput.setCount(optionalOrderInfoMapper.getBillListCount(pageForm));
        listOutput.setOrderList(optionalOrderInfoMapper.getBillList(pageForm));
        return listOutput;
    }
	
	public OrderListOutput getRefundList(RefundSearchForm pageForm) {
        OrderListOutput listOutput = new OrderListOutput();
        listOutput.setCount(optionalOrderInfoMapper.getRefundListCount(pageForm));
        listOutput.setOrderList(optionalOrderInfoMapper.getRefundList(pageForm));
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
                LocalDateUtility.getCurrentDateTimeString());
        message.setMsg(msgBody);
        message.setUserId(orderDb.getUserId());
        messageService.addMessage(message, MsgType.PERSONAL);
    }
	
	@Transactional
    public void updateOrderReviewOk(ReviewOkForm pageForm) {
        MOrder orderDb = mOrderMapper.selectByPrimaryKey(pageForm.getId());
        MUser userDb = userMapper.selectByPrimaryKey(orderDb.getUserId());
        MOrder order = new MOrder();
        order.setId(pageForm.getId());
        order.setCheckStatus(CheckStatusType.PASS.getKey());
        order.setCheckDate(LocalDateUtility.getCurrentDateTimeString());
        order.setAns(pageForm.getAns());
        mOrderMapper.updateByPrimaryKeySelective(order);
        
        MUser user = new MUser();
        user.setId(orderDb.getUserId());
        String newValidDt = pageForm.getValidEndDate() + " 23:59:59";
        user.setValidEndDate(newValidDt);
        userMapper.updateByPrimaryKeySelective(user);
        
        MMessage message = new MMessage();
        message.setTitle(getContext("admin.order.msg.reviewOk.message.title", pageForm.getId()));
        String msgBody = getContext("admin.order.msg.reviewOk.message.body", 
                userDb.getName(),
                getContext(SexType.keyOf(userDb.getSex()).getMsg()),
                pageForm.getId(), 
                LocalDateUtility.getCurrentDateTimeString(),
                newValidDt);
        message.setMsg(msgBody);
        message.setUserId(orderDb.getUserId());
        messageService.addMessage(message, MsgType.PERSONAL);
    }
	
	@Transactional
    public void updateOrderReviewNg(ReviewOkForm pageForm) {
        MOrder orderDb = mOrderMapper.selectByPrimaryKey(pageForm.getId());
        MUser userDb = userMapper.selectByPrimaryKey(orderDb.getUserId());
        MOrder order = new MOrder();
        order.setId(pageForm.getId());
        order.setCheckStatus(CheckStatusType.REFUSED.getKey());
        order.setCheckDate(LocalDateUtility.getCurrentDateTimeString());
        order.setRefundStatus(pageForm.getRefundStatus());
        order.setAns(pageForm.getAns());
        mOrderMapper.updateByPrimaryKeySelective(order);
        
        @SuppressWarnings("unchecked")
        Map<FixedValueType, List<FixValueInfo>> fixedValueMap = 
                (Map<FixedValueType, List<FixValueInfo>>)request.getSession().getAttribute(SessionConstants.FIXED_VALUE.getValue());
        List<FixValueInfo> refundStatusList = fixedValueMap.get(FixedValueType.REFUND_STATUS);
        String refundName = "";
        for (FixValueInfo fValueInfo : refundStatusList) {
            if (fValueInfo.getValueObj().getValue().equals(pageForm.getRefundStatus())) {
                refundName = fValueInfo.getValueObj().getName();
                break;
            }
        }
        MMessage message = new MMessage();
        message.setTitle(getContext("admin.order.msg.reviewNg.message.title", pageForm.getId()));
        String msgBody = getContext("admin.order.msg.reviewNg.message.body", 
                userDb.getName(),
                getContext(SexType.keyOf(userDb.getSex()).getMsg()),
                pageForm.getId(), 
                pageForm.getAns(),
                refundName);
        message.setMsg(msgBody);
        message.setUserId(orderDb.getUserId());
        messageService.addMessage(message, MsgType.PERSONAL);
    }
	
   @Transactional
    public void addBill(BillForm pageForm) throws IOException {

        MOrder order = pageForm.getOrder();
        order.setPayDate(LocalDateUtility.getCurrentDateTimeString());
        order.setPayAmount(pageForm.getBill().getBillAmount());
        order.setBillStatus(BillStatusType.INVOICED.getKey());
        mOrderMapper.updateByPrimaryKeySelective(order);
        
        MBill billDb = billMapper.selectByPrimaryKey(order.getId());
        MBill bill = pageForm.getBill();
        bill.setId(order.getId());
        bill.setUserId(order.getUserId());
        bill.setBillDate(LocalDateUtility.getCurrentDateTimeString());
        if (billDb == null) {
            billMapper.insert(bill);
        } else {
            billMapper.updateByPrimaryKeySelective(bill);
        }
        
        MImage imageDb = mImageMapper.selectByPrimaryKey(order.getId());
        MImage image = new MImage();
        image.setId(order.getId());
        image.setBillPhoto(pageForm.getBillPhotoFile().getBytes());
        image.setBillPhotoExt(pageForm.getBillPhotoFile().getOriginalFilename().split("\\.")[1]);
        
        if (imageDb == null) {
            mImageMapper.insert(image);
        } else {
            mImageMapper.updateByPrimaryKeySelective(image);
        }
        
        MUser userDb = userMapper.selectByPrimaryKey(order.getUserId());
        MMessage message = new MMessage();
        message.setTitle(getContext("admin.bill.msg.add.message.title", order.getId()));
        String msgBody = getContext("admin.bill.msg.add.message.body", 
                userDb.getName(),
                getContext(SexType.keyOf(userDb.getSex()).getMsg()),
                order.getId(), 
                LocalDateUtility.getCurrentDateTimeString());
        message.setMsg(msgBody);
        message.setUserId(order.getUserId());
        messageService.addMessage(message, MsgType.PERSONAL);
    }
	
}
