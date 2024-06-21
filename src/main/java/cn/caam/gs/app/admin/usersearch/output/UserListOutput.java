package cn.caam.gs.app.admin.usersearch.output;

import java.util.ArrayList;
import java.util.List;

import cn.caam.gs.domain.db.custom.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListOutput {

    @Default
    private int count = 0;
    @Default
	private List<UserInfo> userList = new ArrayList<UserInfo>();
	
}
