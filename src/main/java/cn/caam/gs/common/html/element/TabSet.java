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
    private String[] tabIds;
    private CssClassType[] tabTypes;
    private String[] tabContexts;

    public String html() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row'>");
        sb.append("<div id='"+id+"' class='col-mb-12 col-sm-12 btn-group btn-group-toggle' data-toggle='buttons'>");
        int index = 0;
        for(String title : tabTitles) {
            sb.append("<label for='"+tabIds[index]+"' class='btn btn-outline-"+tabTypes[index].getKey()+" btn_tab'>");
            sb.append("<input type='radio' name='"+id+"' autocomplete='off'>");
            sb.append(title);
            sb.append("</label>");
            index++;
        }
        sb.append("</div>");
        sb.append("</div>");
         index = 0;
        for(String context : tabContexts) {
            sb.append("<div id='"+tabIds[index]+"'>");
            sb.append(context);
            sb.append("</div>");
            index++;
        }
        return sb.toString();
    }
}
