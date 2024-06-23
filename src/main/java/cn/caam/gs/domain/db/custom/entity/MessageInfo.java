package cn.caam.gs.domain.db.custom.entity;

import cn.caam.gs.domain.db.base.entity.MMessage;
import cn.caam.gs.domain.db.base.entity.MMessageRead;
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
public class MessageInfo {
	
    private String id;
	private MMessage message;
	private MMessageRead messageRead;
    
	/** 未读状态 */
	private boolean readSts;
	/** 站内消息类型(F0021) */
	private String msgTypeName;
}
