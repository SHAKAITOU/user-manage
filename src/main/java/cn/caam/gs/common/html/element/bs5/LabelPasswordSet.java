package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelPasswordSet {

    private String labelName; 
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    private String placeholder;
    private String id;
    private String name;
    private String value;
    private String buttonId;
    @Default
    private boolean notBlank = false;
    @Default
    private boolean showIcon = false;
    @Default
    private int maxlength = 0;
    public String html() {
        return get();
    }

    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");
        sb.append("<span class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName);
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</span>");
        sb.append("<input type='password' style='background-color:" + GlobalConstants.INPUT_BG_COLOER + "' class='form-control " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "'");
        if (Objects.nonNull(placeholder)) {
            sb.append(" placeholder='" + placeholder + "'");
        }
        if(Objects.nonNull(id)) {
            sb.append(" id='"+ id + "'");
        }
        
        if (Objects.nonNull(name)) {
            sb.append(" name='"+ name + "'");
        }
        if (maxlength > 0) {
            sb.append(" maxlength='"+ maxlength + "'");
        }
        
        if (Objects.nonNull(value)) {
            sb.append(" value='"+ value + "'");
        }
        sb.append(">");
        if (Objects.nonNull(buttonId) && showIcon) {
            sb.append("<span class=' input-group-text text-center password'>");
            sb.append(IconSet.builder().type(IconSetType.EYE_SLASH).id(buttonId).css(IconSetCss.NOMAL_12).build().html());
            sb.append("</span>");
        }
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
