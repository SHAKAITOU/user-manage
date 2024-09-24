package cn.caam.gs.app.common.form;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.domain.db.base.entity.MMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageSearchForm {

	private MMessage message;
	private String userName;
	private String userCode;
	private String registDateFrom;
	private String registDateTo;
	@Default
	private boolean hideSearch = false;
	@Default
	private int limit = GlobalConstants.DEFAULT_GROWING_CNT;
	@Default
	private int offset = 0;
	
    @Default
    private int messagePageLinkIdPrefixIndex = 0;
}
