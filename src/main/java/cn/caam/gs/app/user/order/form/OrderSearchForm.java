package cn.caam.gs.app.user.order.form;

import cn.caam.gs.domain.db.base.entity.MOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchForm {

	private MOrder order;
	private String payDateFrom;
	private String payDateTo;
	
}
