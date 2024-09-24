package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.OnInputMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelInputSet {

    private String labelName; 
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    private String placeholder;
    private String id;
    private String name;
    private String value;
    private String buttonName;
    private String buttonId;
    private String footHtml;
    @Default
    private boolean notBlank = false;
    @Default
    private int maxlength = 0;
    @Default
    boolean disabled = false;
    @Builder.Default
    boolean readonly = false;
    
    private OnInputMode onInputMode;

    public String html() {
        return get();
    }

    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");//style='display:inline-block;width:150px;'
        sb.append("<span  class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName);
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</span>");
        sb.append("<input type='text' ");
        sb.append(" class='form-control "); 
        sb.append((Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "'");
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
        if (readonly) {
            sb.append(" readonly ");
        }
        if (disabled) {
            sb.append(" disabled ");
        }
        sb.append("style='background-color:" + (disabled ? GlobalConstants.INPUT_DISABLED_BG_COLOER:GlobalConstants.INPUT_BG_COLOER) + "' ");
        if (!Objects.isNull(onInputMode)) {
        	sb.append(" oninput=\"value=value.replace("+onInputMode.getRegex()+",'')\"");
        }
        sb.append(">");
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append("<span class=' input-group-text text-center "
                    + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" )
                    + "'>");
            sb.append(footHtml);
            sb.append("</span>");
            sb.append("</div>");
        }
        if (Objects.nonNull(buttonId)) {
            sb.append("<button class='btn btn-primary " 
                    + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "' type='button' id='button-addon'>");
            sb.append("<i class='fas fa-search'></i>&nbsp;&nbsp;" + buttonName + "&nbsp;&nbsp;");
            sb.append("</button>");
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
