package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.element.DivContainerSet;
import cn.caam.gs.common.html.element.DivContainerSet.DivContainerSetType;
import cn.caam.gs.common.html.element.bs5.DivAlertSet;

@Component
public class HtmlDivContainerHelper extends HtmlBaseHelper {

    public String getNoContainer(String id, String... contexts) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div id='"+id+"'>");
        for(String context : contexts) {
            sb.append(context);
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    public String get(String... contexts) {
        return DivContainerSet.builder().outPutType(DivContainerSetType.SIMPLE).contexts(contexts).build().html();
    }
    
    public String withScroll(int scrollHeight, String... contexts) {
        return DivContainerSet.builder().outPutType(DivContainerSetType.SCROLL)
                .scrollHeight(scrollHeight)
                .contexts(contexts).build().html();
    }
    
    public String getAlert(CssClassType cssClassType, String... contexts) {
        return DivAlertSet.builder()
                .classType(cssClassType).contexts(contexts).build().html();
        
    }
    
}
