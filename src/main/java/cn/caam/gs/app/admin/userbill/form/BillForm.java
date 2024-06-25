package cn.caam.gs.app.admin.userbill.form;

import org.springframework.web.multipart.MultipartFile;

import cn.caam.gs.domain.db.base.entity.MBill;
import cn.caam.gs.domain.db.base.entity.MImage;
import cn.caam.gs.domain.db.base.entity.MOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillForm {

    private MultipartFile billPhotoFile;
	private MOrder order;
	private MBill bill;
	private MImage image;
	
}
