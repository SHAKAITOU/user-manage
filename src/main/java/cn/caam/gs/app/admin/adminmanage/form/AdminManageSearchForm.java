package cn.caam.gs.app.admin.adminmanage.form;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.domain.db.base.entity.MAdmin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminManageSearchForm {
	private MAdmin         user;
	private String        userType;
    @Default
    private boolean hideSearch = false;
    @Default
    private int limit = GlobalConstants.DEFAULT_GROWING_CNT;
    @Default
    private int offset = 0;
    
    @Default
    private int userPageLinkIdPrefixIndex = 0;
    
    private String selectedUserId;

}
