package cn.caam.gs.app.admin.adminmanage.form;

import org.springframework.web.multipart.MultipartFile;

import cn.caam.gs.domain.db.custom.entity.AdminUserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserDetailForm {

    private String id;
    private MultipartFile photoFile;
    private AdminUserInfo userInfo;	
}
