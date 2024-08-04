package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

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
public class LabelFileSet {

    private String labelName; 
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    private String placeholder;
    private String id;
    private String idLablel;
    private String name;
    private String value;
    @Default
    private boolean notBlank = false;
    @Default
    private int maxlength = 0;
    @Builder.Default
    boolean disabled = false;

    public String html() {
        return get();
    }

    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");
        sb.append("<div class='input-group-append'>");
        sb.append("<span class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName);
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</span>");
        sb.append("</div>");
        sb.append("<div class='custom-file'>");
        sb.append("<input type='file' ");
        sb.append(" class='custom-file-input "); 
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
        if (disabled) {
            sb.append(" disabled ");
        }
        sb.append("style='background-color:" + (disabled ? GlobalConstants.INPUT_DISABLED_BG_COLOER:GlobalConstants.INPUT_BG_COLOER) + "' >");
        sb.append("<label class='custom-file-label "
                + ((Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14") + "' ") 
                + "style='background-color:" + (disabled ? GlobalConstants.INPUT_DISABLED_BG_COLOER:GlobalConstants.INPUT_BG_COLOER) + "' "
                + "id='" + idLablel + "' "
                + " for='" + id + "'>");
        sb.append("<i class='fa fa-upload'></i>&nbsp;&nbsp;");
        sb.append("</label>");
        sb.append("</div>");

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
