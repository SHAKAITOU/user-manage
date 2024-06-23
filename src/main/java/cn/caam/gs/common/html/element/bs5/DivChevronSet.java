package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivChevronSet {

    private CssFontSizeType fontSize;
    private String idUp;
    private String idDown;
    @Default
    private boolean inVlisable = false;
    public String html() {
        return get();
    }

    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='row'");
        if (inVlisable) {
            sb.append(" style='display:none;'");
        }
        sb.append(">");
        sb.append("<div class='col-2 d-flex justify-content-center' style='padding:0px;'></div>");
        sb.append("<div class='col-12 d-flex justify-content-center' style='padding:0px;'>");
        sb.append("<span id='"+ idUp + "' class='a-a2'>");
        sb.append("<i class='fa fa-chevron-up "
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-16") +"'></i>");
        sb.append("</span>");
        sb.append("<span id='"+ idDown + "' class='a-a2'>");
        sb.append("<i class='fa fa-chevron-down "
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-16") +"'></i>");
        sb.append("</span>");
        sb.append("</div>");
        sb.append("<div class='col-2 d-flex justify-content-center' style='padding:0px;'></div>");
        sb.append("</div>");
        
        return sb.toString();
    }
}
