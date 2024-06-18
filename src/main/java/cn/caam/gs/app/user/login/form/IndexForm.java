package cn.caam.gs.app.user.login.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexForm {

	private String loginFormJson;
	
	private int mediaHeight;
	
	private int mediaWidth;
	
	private String mobileDisplay;
	
	private String envMode;
}
