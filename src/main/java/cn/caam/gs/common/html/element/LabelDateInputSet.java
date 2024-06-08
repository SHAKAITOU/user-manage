package cn.caam.gs.common.html.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelDateInputSet {

    private int labelWidth; 
    private String labelName; 
    private String id; 
    private String name;
    private String value;
    private String footHtml;
    private LabelDateInputSetType outPutType;
    
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
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='text'");
        sb.append(" class='form-control item-input' ");
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "' readonly/>");
        return sb.toString();
    }
    
    private String getWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label'>" + labelName + "</span>");
        sb.append("</div>");
        sb.append("<input type='text'");
        sb.append(" class='form-control item-input' ");
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "' readonly/>");
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append(footHtml);
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getWithLabelFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label'>" + labelName + "</span>");
        sb.append("</div>");
        sb.append("<input type='text'");
        sb.append(" class='form-control item-input' ");
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "' readonly/>");
        sb.append("<div class='input-group-append'>");
        sb.append("<span class='td-t2d input-group-text text-center'>&nbsp;&nbsp;&nbsp;");
        sb.append(IconSet.builder().type(IconSetType.CALENDAR).css(IconSetCss.NOMAL_8).build().html());
        sb.append("</span>");
        sb.append("</div>");
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append(footHtml);
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getWithFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<input type='text'");
        sb.append(" class='form-control item-input' ");
        sb.append(" id='" + id + "' name='" + name + "' value='" + (Objects.nonNull(value) ? value : "") + "' readonly/>");
        sb.append("<div class='input-group-append'>");
        sb.append("<span class='td-t2d input-group-text text-center'>&nbsp;&nbsp;&nbsp;");
        sb.append(IconSet.builder().type(IconSetType.CALENDAR).css(IconSetCss.NOMAL_8).build().html());
        sb.append("</span>");
        sb.append("</div>");
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append(footHtml);
            sb.append("</div>");
        }
        sb.append("</div>");
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
