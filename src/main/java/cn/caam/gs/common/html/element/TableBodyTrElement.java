package cn.caam.gs.common.html.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TableBodyTrElement {
    
    private List<String> tdList;
    
    private Map<String, String> properties;
    
    public TableBodyTrElement(Map<String, String> properties) {
        this.properties = properties;
        tdList = new ArrayList<>();
    }
    
    public void addTd(String tdContext) {
        tdList.add(tdContext);
    }
    
    public String getContext() {
        StringBuffer sb = new StringBuffer();
        sb.append("<tr ");
        for(Map.Entry<String, String> property : properties.entrySet()) {
            sb.append(property.getKey()+"='"+property.getValue()+"' ");
        }
        sb.append(">");
        for(String td : tdList) {
            sb.append(td);
        }
        sb.append("</tr>");
        return sb.toString();
    }
}
