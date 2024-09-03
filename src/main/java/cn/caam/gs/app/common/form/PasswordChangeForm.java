package cn.caam.gs.app.common.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeForm {

	private String oldPassword;
	
	private String newPassword;
	
	private String newPasswordRep;
	
	
}
