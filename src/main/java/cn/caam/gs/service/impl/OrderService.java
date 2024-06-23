package cn.caam.gs.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.app.common.output.OrderListOutput;
import cn.caam.gs.app.user.order.form.OrderForm;
import cn.caam.gs.app.util.SessionConstants;
import cn.caam.gs.common.enums.BillStatusType;
import cn.caam.gs.common.enums.CheckStatusType;
import cn.caam.gs.common.util.EncryptorUtil;
import cn.caam.gs.common.util.LocalDateUtility;
import cn.caam.gs.common.util.LocalDateUtility.DateTimePattern;
import cn.caam.gs.domain.db.base.entity.MImage;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.domain.db.base.mapper.MImageMapper;
import cn.caam.gs.domain.db.base.mapper.MOrderMapper;
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
	MImageMapper mImageMapper;

	public OrderListOutput getOrderList(OrderSearchForm pageForm) {
	    OrderListOutput listOutput = new OrderListOutput();
	    listOutput.setCount(optionalOrderInfoMapper.getOrderListCount(pageForm));
	    listOutput.setOrderList(optionalOrderInfoMapper.getOrderList(pageForm));
    	return listOutput;
	}
	
	public OrderInfo getOrder(String orderId) {
        return optionalOrderInfoMapper.getOrder(orderId);
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
	
}
