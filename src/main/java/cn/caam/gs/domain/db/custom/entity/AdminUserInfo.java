package cn.caam.gs.domain.db.custom.entity;

import cn.caam.gs.domain.db.base.entity.MAdmin;
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
public class AdminUserInfo {
	
    private String id;
	private MAdmin user;
	
	/** 管理员类型(F0000)*/
	private String userTypeName;
	
	private String photoExt;
	private byte[] photo;
	
	private String password;
	private String passwordRep;
}
