package cn.caam.gs.domain.db.custom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.custom.entity.OrderInfo;

@Mapper
public interface OptionalOrderInfoMapper {

    int getOrderListCount(MUser user);
	List<OrderInfo> getOrderList(MUser user);
}
