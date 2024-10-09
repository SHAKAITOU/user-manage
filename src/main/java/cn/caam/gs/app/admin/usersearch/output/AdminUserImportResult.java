package cn.caam.gs.app.admin.usersearch.output;

import cn.caam.gs.common.enums.ExecuteReturnType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserImportResult {

	ExecuteReturnType executeReturnType;
	String result;
}
