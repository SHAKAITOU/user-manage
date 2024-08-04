package cn.caam.gs.domain.db.custom.entity;

import cn.caam.gs.domain.db.base.entity.MUserCheckHistory;
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
public class UserCheckHistoryInfo {
	
    private String id;
	private MUserCheckHistory userCheckHistory;
    
	/** 审核状态(F0024) */
	private String checkStatusName;
}
