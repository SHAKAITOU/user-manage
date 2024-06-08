package cn.caam.gs.common.html.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivAlertSet {

    private String id;
    private String[] contexts;
    private CssClassType classType; 
    private CssAlignType align; 
    
    public String html() {
        return getSimple();
    }
    
    private String getSimple() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='alert ");
        sb.append(" alert-"+ (Objects.nonNull(classType) ? classType.getKey() : CssClassType.CONTEXT.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" '");
        if(!StringUtils.isEmpty(id)) {
            sb.append(" id='"+id+"'");
        }
        sb.append(">");
        for(String context : contexts) {
            sb.append(context);
        }
        sb.append("</div>");
        return sb.toString();
    }
}
