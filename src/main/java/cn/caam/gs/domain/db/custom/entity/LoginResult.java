package cn.caam.gs.domain.db.custom.entity;

import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LoginResult {
	
	private MUser user;
	private MUserExtend userExtend;

}
