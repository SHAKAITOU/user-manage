package cn.caam.gs.app.common.output;

import java.util.ArrayList;
import java.util.List;

import cn.caam.gs.domain.db.custom.entity.OrderInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListOutput {

    @Default
    private int count = 0;
    @Default
	private List<OrderInfo> orderList = new ArrayList<OrderInfo>();
	
}
