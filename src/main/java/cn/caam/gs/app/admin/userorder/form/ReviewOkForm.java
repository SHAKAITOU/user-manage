package cn.caam.gs.app.admin.userorder.form;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewOkForm {

	private String id;
	private String validEndDate;
	private String ans;
	private String refundStatus;
	private BigDecimal payAmount;
}
