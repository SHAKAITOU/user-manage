package cn.caam.gs.domain.db.custom.entity;

import cn.caam.gs.domain.db.base.entity.MBill;
import cn.caam.gs.domain.db.base.entity.MImage;
import cn.caam.gs.domain.db.base.entity.MOrder;
import cn.caam.gs.domain.db.base.entity.MUser;
import cn.caam.gs.domain.db.base.entity.MUserExtend;
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
public class OrderInfo {
	
    private String id;
	private MOrder order;
	private MBill bill;
    private MImage orderImg;
    
    private String userName;
    
	/** 缴费渠道(F0012) */
	private String payPathName;
	
	/** 订单方式(F0020) */
	private String orderMethodName;
	
	/** 订单类型(F0015) */
    private String orderTypeName;
    
    /** 缴费类型(F0014) */
    private String payTypeName;
    
    /** 审核状态(F0013)*/
    private String checkStatusName;
    
    /** 退款状态(F0016) */
    private String refundStatusName;
    
    /** 开票状态(F0018) */
    private String billStatusName;
    
    /** 发票类型(F0017) */
    private String billTypeName;
    
    /** 取票方式(F0019) */
    private String voteMethodName;

}
