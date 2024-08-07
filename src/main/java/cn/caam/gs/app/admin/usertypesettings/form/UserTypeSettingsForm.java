package cn.caam.gs.app.admin.usertypesettings.form;

import java.util.List;

import cn.caam.gs.domain.db.custom.entity.UserTypeSettingsInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeSettingsForm {

    private String id;
    private List<UserTypeSettingsInfo> userTypeSettingsInfoList;	
}
