package cn.caam.gs.app.user.order.form;

import org.springframework.web.multipart.MultipartFile;

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
public class OrderForm {

    private MultipartFile orderPhotoFile;
	private MOrder order;
	private MImage image;
	
}
