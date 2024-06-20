package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.caam.gs.app.user.order.form.OrderSearchForm;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;

@Mapper
public interface OptionalOrderInfoMapper {

    int getOrderListCount(OrderSearchForm pageForm);
	List<OrderInfo> getOrderList(OrderSearchForm pageForm);
}
