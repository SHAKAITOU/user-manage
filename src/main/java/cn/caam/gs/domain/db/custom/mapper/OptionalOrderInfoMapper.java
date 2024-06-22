package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.caam.gs.app.common.form.OrderSearchForm;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;

@Mapper
public interface OptionalOrderInfoMapper {

    int getOrderListCount(OrderSearchForm pageForm);
	List<OrderInfo> getOrderList(OrderSearchForm pageForm);
}
