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
public class BindPhoneChangeForm {
	private String        errorMsg;
	private String        id;
	private String        orgAuthCode;
	private String        authCode;
	private MAuthCode     mauthCode;
	private String        phone;
}
