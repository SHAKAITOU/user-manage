package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;
import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelDateInputSet {

    private String labelName; 
    private String id; 
    private String name;
    private String value;
    private String footHtml;
    private String placeholder;
    @Default
    private boolean notBlank = false;
    private LabelDateInputSetType outPutType;
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    
    public String html() {
        if(outPutType == LabelDateInputSetType.WITH_LABEL) {
            return getWithLabel();
        }else if(outPutType == LabelDateInputSetType.WITH_LABEL_FOOT) {
            return getWithLabelFoot();
        }else if(outPutType == LabelDateInputSetType.WITH_FOOT) {
            return getWithFoot();
        }else {
            return get();
        }
    }

    private String getWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");
        sb.append("<span class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName);
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</span>");
        sb.append(get());
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append(footHtml);
            sb.append("</div>");
        }
        sb.append("</div>");
        return getContext(sb.toString());
    }
    
    private String getWithLabelFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");
        sb.append("<span class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName);
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</span>");
        sb.append(get());
        sb.append("<div class='input-group-append'>");
        sb.append("<span class=' input-group-text text-center'>");
        sb.append(IconSet.builder().type(IconSetType.CALENDAR).css(IconSetCss.NOMAL_12).build().html());
        sb.append("</span>");
        sb.append("</div>");
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append(footHtml);
            sb.append("</div>");
        }
        sb.append("</div>");
        return getContext(sb.toString());
    }
    
    private String getWithFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");
        sb.append(get());
        sb.append("<div class='input-group-append'>");
        sb.append("<span class=' input-group-text text-center'>");
        sb.append(IconSet.builder().type(IconSetType.CALENDAR).css(IconSetCss.NOMAL_12).build().html());
        sb.append("</span>");
        sb.append("</div>");
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append(footHtml);
            sb.append("</div>");
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
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='text'");
        sb.append(" class='form-control " + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "'");
        sb.append(" style='background-color:" + GlobalConstants.INPUT_BG_COLOER + "'");
        if (Objects.nonNull(placeholder)) {
            sb.append(" placeholder='" + placeholder + "'");
        }
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "' readonly/>");
        return sb.toString();
    }
    
    public enum LabelDateInputSetType {

        SIMPLE                 (1),
        WITH_LABEL            (2),
        WITH_LABEL_FOOT        (3),
        WITH_FOOT            (4),
        ;
        
        /** type. */
        private int key;

        private LabelDateInputSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelDateInputSetType[] list() {
            return LabelDateInputSetType.values();
        }
        
        public static LabelDateInputSetType keyOf(int key) {
            for(LabelDateInputSetType type : LabelDateInputSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
