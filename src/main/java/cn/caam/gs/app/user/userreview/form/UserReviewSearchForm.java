package cn.caam.gs.app.user.userreview.form;

import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.app.GlobalConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewSearchForm {
	private MUser         user;
	
    @Default
    private boolean hideSearch = false;
    @Default
    private int limit = GlobalConstants.DEFAULT_GROWING_CNT;
    @Default
    private int offset = 0;
    
    @Default
    private int userPageLinkIdPrefixIndex = 0;
}
