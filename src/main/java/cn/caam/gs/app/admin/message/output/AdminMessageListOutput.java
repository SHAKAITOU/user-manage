package cn.caam.gs.app.admin.message.output;

import java.util.ArrayList;
import java.util.List;

import cn.caam.gs.domain.db.custom.entity.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminMessageListOutput {

    @Default
    private int count = 0;
    @Default
	private List<MessageInfo> messageList = new ArrayList<MessageInfo>();
	
}
