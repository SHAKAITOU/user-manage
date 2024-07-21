package cn.caam.gs.app.admin.userbill.form;

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
public class RefundSearchForm {

	private MOrder order;
    private String refundDateFrom;
    private String refundDateTo;
	
	@Default
	private boolean hideSearch = false;
    @Default
    private boolean inVisableSearch = false;
	@Default
	private int limit = GlobalConstants.DEFAULT_GROWING_CNT;
	@Default
	private int offset = 0;
    @Default
    private int billPageLinkIdPrefixIndex = 0;
}
