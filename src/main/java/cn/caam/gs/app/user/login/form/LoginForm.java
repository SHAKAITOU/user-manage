package cn.caam.gs.app.user.login.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    
    private int mediaHeight;
    
    private int mediaWidth;
	
	private String userCode;
	
	private String password;
	
	private int returnType;
	
	private String errorMsg;
	
	private String lang;
	
	private String authImg;
	
	private String authImgNumber;
	
	private String phoneAuthCode;
	
	private String phoneUserCode;
	
	private String loginBy;
}
