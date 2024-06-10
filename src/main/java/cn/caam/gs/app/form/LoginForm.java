package cn.caam.gs.app.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
	
	private String userCode;
	
	private String password;
	
	private int returnType;
	
	private String errorMsg;
	
	private String lang;
}
