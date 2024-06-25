package cn.caam.gs.common.html.element;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssClassType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TabSet {
    
    private String id;
    private String[] tabTitles;
    private String[] tabTitleIds;
    private String[] tabBodyIds;
    private String[] tabBodys;

    public String html() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div id='"+id+"'>");
        sb.append("<ul class='nav nav-tabs' role='tablist'>");
        int index = 0;
        for(String title : tabTitles) {
            sb.append("<li class='nav-item' role='presentation'>");
            sb.append("<a class='nav-link label-16b a-tab ");
            if (index == 0) {
                sb.append("active");
            }
            sb.append("' id='"+tabTitleIds[index]+"'");
            sb.append(" data-bs-toggle='tab' href='#' role='tab'>"+title+"</a>");
            sb.append("</li>");
            index++;
        }
        sb.append("</ul>");
        sb.append("<div class='tab-content'>");
        index = 0;
        for(String context : tabBodys) {
            
            sb.append("<div class='tab-pane ");
            if (index == 0) {
                sb.append(" active show");
            }
            sb.append("' id='"+tabBodyIds[index]+"' role='tabpanel'>");
            sb.append(context);
            sb.append("</div>");
            index++;
        }        
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
}
