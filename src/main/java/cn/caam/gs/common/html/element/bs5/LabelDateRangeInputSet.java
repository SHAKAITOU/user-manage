package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.GlobalConstants;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.IconSet;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelDateRangeInputSet {

    private String labelName; 
    private String idFrom; 
    private String nameFrom;
    private String valueFrom;
    private String idTo; 
    private String nameTo;
    private String valueTo;
    private String footHtml;
    private LabelDateRangeInputSetType outPutType;
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    
    public String html() {
        if(outPutType == LabelDateRangeInputSetType.WITH_LABEL) {
            return getWithLabel();
        }else if(outPutType == LabelDateRangeInputSetType.WITH_LABEL_FOOT) {
            return getWithLabelFoot();
        }else if(outPutType == LabelDateRangeInputSetType.WITH_FOOT) {
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
                + "'>" + labelName + "</span>");
        sb.append(getFrom());
        sb.append("<div class='input-group-append' style='margin-top:5px'>");
        sb.append("<span class='text-center " + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14") + "' width='30px'>");
        sb.append("&nbsp;&nbsp;-&nbsp;&nbsp;");
        sb.append("</span>");
        sb.append("</div>");
        sb.append("<div class='input-group-append'>");
        sb.append(getTo());
        sb.append("</div>");
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
                + "'>" + labelName + "</span>");
        sb.append(getFrom());
        sb.append("<div class='input-group-append' style='margin-top:5px'>");
        sb.append("<span class='text-center " + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14") + "' width='30px'>");
        sb.append("&nbsp;&nbsp;-&nbsp;&nbsp;");
        sb.append("</span>");
        sb.append("</div>");
        sb.append("<div class='input-group-append'>");
        sb.append(getTo());
        sb.append("</div>");
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
        sb.append(getFrom());
        sb.append("<div class='input-group-append' style='margin-top:5px'>");
        sb.append("<span class='text-center " + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14") + "' width='30px'>");
        sb.append("&nbsp;&nbsp;-&nbsp;&nbsp;");
        sb.append("</span>");
        sb.append("</div>");
        sb.append("<div class='input-group-append'>");
        sb.append(getTo());
        sb.append("</div>");
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
        sb.append("<div class='input-group mb-3'>");
        sb.append(getFrom());
        sb.append("<div class='input-group-append'>");
        sb.append("<span class=' input-group-text text-center'>");
        sb.append("&nbsp;&nbsp;-&nbsp;&nbsp;");
        sb.append("</span>");
        sb.append("</div>");
        sb.append("<div class='input-group-append'>");
        sb.append(getTo());
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
    
    private String getFrom() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='text'");
        sb.append(" class='form-control " + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "'");
        sb.append(" style='background-color:" + GlobalConstants.INPUT_BG_COLOER + "'");
        sb.append(" id='" + idFrom + "' name='" + nameFrom + "' value='" + (Objects.nonNull(valueFrom) ? valueFrom : "") + "' readonly/>");
        return sb.toString();
    }
    
    private String getTo() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='text'");
        sb.append(" class='form-control " + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "'");
        sb.append(" style='background-color:" + GlobalConstants.INPUT_BG_COLOER + "'");
        sb.append(" id='" + idTo + "' name='" + nameTo + "' value='" + (Objects.nonNull(valueTo) ? valueTo : "") + "' readonly/>");
        return sb.toString();
    }
    
    public enum LabelDateRangeInputSetType {

        SIMPLE                 (1),
        WITH_LABEL            (2),
        WITH_LABEL_FOOT        (3),
        WITH_FOOT            (4),
        ;
        
        /** type. */
        private int key;

        private LabelDateRangeInputSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelDateRangeInputSetType[] list() {
            return LabelDateRangeInputSetType.values();
        }
        
        public static LabelDateRangeInputSetType keyOf(int key) {
            for(LabelDateRangeInputSetType type : LabelDateRangeInputSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
