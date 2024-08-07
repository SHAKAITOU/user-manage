package cn.caam.gs.domain.db.custom.entity;

import cn.caam.gs.domain.db.base.entity.MUserTypeSettings;
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
public class UserTypeSettingsInfo {
	
	private MUserTypeSettings userTypeSettings;
    
	/** 会员类型(F0002) */
	private String userTypeName;
}
