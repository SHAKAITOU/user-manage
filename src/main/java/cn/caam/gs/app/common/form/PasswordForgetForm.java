package cn.caam.gs.app.common.form;

import cn.caam.gs.domain.db.base.entity.MAuthCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordForgetForm {
	private String        errorMsg;
	private String        orgAuthCode;
	private String        authCode;
	private String        authMethod;
	private MAuthCode     mauthCode;
	private boolean       fromRedirect;
	private String        stepStatus;
	
	private String        phone;
	private String        newPassword;
	private String        newPasswordRep;
}
