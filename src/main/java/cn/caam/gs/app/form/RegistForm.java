package cn.caam.gs.app.form;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.caam.gs.domain.db.base.entity.MAuthCode;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserExtend;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistForm {
	private String        errorMsg;
	private LocalDateTime expiredDt;
	private String        orgAuthCode;
	private String        authCode;
	private String        authMethod;
	private MAuthCode     mauthCode;
	private boolean       fromRedirect;
	private String        stepStatus;
	private MUser         user;
	private MUserExtend   userExtend;
	
	
}
