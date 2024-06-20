package cn.caam.gs.app.admin.usersearch.form;

import cn.caam.gs.domain.db.base.entity.MUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchForm {
	private MUser         user;
	private String        validStatus;
	private String        registDateFrom;
	private String        registDateTo;
	private String        validEndDateFrom;
    private String        validEndDateTo;
}
