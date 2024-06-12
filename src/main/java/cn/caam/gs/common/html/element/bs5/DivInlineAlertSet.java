package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivInlineAlertSet {

    private String text; 
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    private CssClassType cssClassType;
    private String id;
    public String html() {
        return get();
    }

    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='alert alert-dismissible alert-" + cssClassType.getKey() + " ");
        if (Objects.nonNull(fontSize)) {
            sb.append(fontSize.getKey());
        } else {
            sb.append("label-14");
        }
        sb.append("' ");
        if (Objects.nonNull(id)) {
            sb.append(" id='" + id + "'");
        }
        sb.append(">");
        sb.append("<strong>");
        sb.append(text);
        sb.append("</strong>");
        sb.append("</div>");
        return getContext(sb.toString());
    }
    
    private String getContext(String coreStr) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        if (Objects.nonNull(grids)) {
            sb.append("class='col-xl-" + grids.getKey() 
            + " col-xxl-" + grids.getKey() 
            + " col-lg-" + grids.getKey()
            + " col-md-12 col-sm-12"
            +" '>");
        } else {
            sb.append("class='col'>");
        }
        sb.append(coreStr);
        sb.append("</div>");
        return sb.toString();
    }
}
