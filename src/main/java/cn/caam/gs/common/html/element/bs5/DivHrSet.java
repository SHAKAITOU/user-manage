package cn.caam.gs.common.html.element.bs5;

import cn.caam.gs.common.enums.CssFontSizeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivHrSet {

    private CssFontSizeType fontSize;
    public String html() {
        return get();
    }

    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row'>");
        sb.append("<div class='col-12 d-flex justify-content-center'>");
        sb.append("<hr style='color: #e9e5e5;border: 1px solid;width: 100%;'>");
        sb.append("</div>");
        sb.append("</div>");
        
        return sb.toString();
    }
}
