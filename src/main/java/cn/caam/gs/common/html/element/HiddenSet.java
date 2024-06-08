package cn.caam.gs.common.html.element;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HiddenSet {
    private String id; 
    private String name; 
    private String value;
    
    public String html() {
        return getHidden();
    }
    private String getHidden() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='hidden'");
        if(Objects.nonNull(id)) {
            sb.append(" id='").append(id).append("'");
        }
        if(Objects.nonNull(name)) {
            sb.append(" name='").append(name).append("'");
        }
        sb.append(" value='").append(value).append("'");
        sb.append(" />");
        return sb.toString();
    }
}
