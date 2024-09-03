package cn.caam.gs.app.admin.adminmanage.output;

import java.util.ArrayList;
import java.util.List;

import cn.caam.gs.domain.db.custom.entity.AdminUserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserListOutput {

    @Default
    private int count = 0;
    @Default
	private List<AdminUserInfo> userList = new ArrayList<AdminUserInfo>();
	
}
