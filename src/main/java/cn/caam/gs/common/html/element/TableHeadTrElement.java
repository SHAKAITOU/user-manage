package cn.caam.gs.common.html.element;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TableHeadTrElement {
    
    private List<String> thList;
    
    public TableHeadTrElement() {
        thList = new ArrayList<>();
    }
    
    public void addTh(String thContext) {
        thList.add(thContext);
    }
    
    public String getContext() {
        StringBuffer sb = new StringBuffer();
        sb.append("<tr class='table-info'>");
        for(String th : thList) {
            sb.append(th);
        }
        sb.append("</tr>");
        return sb.toString();
    }
}
