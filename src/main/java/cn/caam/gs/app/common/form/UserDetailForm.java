package cn.caam.gs.app.common.form;

import org.springframework.web.multipart.MultipartFile;

import cn.caam.gs.domain.db.custom.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailForm {

    private String id;
    private MultipartFile photoFile;
    private MultipartFile educationalAtFile;
    private MultipartFile bachelorAtFile;
    private MultipartFile vocationalAtFile;
    private MultipartFile applicationFormFile;
    private MultipartFile applicationForm2File;
    private UserInfo userInfo;	
}
