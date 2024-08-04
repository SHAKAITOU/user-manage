package cn.caam.gs.app.common.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdForm {

	private String id;
	
	private String[] user_check_;
	
}
