package cn.caam.gs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import cn.caam.gs.domain.db.custom.mapper.OptionalOrderInfoMapper;
import cn.caam.gs.service.BaseService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService extends BaseService {
	
	@Autowired
	OptionalOrderInfoMapper optionalOrderInfoMapper;

	public List<OrderInfo> getOrderList(MUser user) {
    	return optionalOrderInfoMapper.getOrderList(user);
	}
	
}
