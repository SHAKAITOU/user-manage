package cn.caam.gs.app.user.order.form;

import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.app.GlobalConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
	@Default
	private boolean hideSearch = false;
	@Default
	private int limit = GlobalConstants.DEFAULT_GROWING_CNT;
	@Default
	private int offset = 0;
	
}