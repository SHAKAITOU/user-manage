package cn.caam.gs.common.html.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.html.HtmlBaseHelper;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelTextSet {

    private int labelWidth; 
    private String labelName; 
    private String textId; 
    private String textName;
    private String textValue; 
    private CssAlignType textAlign; 
    private String footHtml;
    private LabelTextSetType outPutType;
    private String customizeTooltip;
    private int maxLength;
    private String placeholder;
    @Default
    private boolean notBlank = false;
    
    public String html() {
        if(outPutType == LabelTextSetType.WITH_LABEL) {
            return getWithLabel();
        }else if(outPutType == LabelTextSetType.WITH_LABEL_FOOT) {
            return getWithLabelFoot();
        }else if(outPutType == LabelTextSetType.SIMPLE_LARGE) {
            return getLg();
        }else if(outPutType == LabelTextSetType.LARGE_WITH_LABEL) {
            return getLgWithLabel();
        } else if(outPutType == LabelTextSetType.SIMPLE_WITH_FOOT) {
            return getWithFoot();
        } else {
            return get();
        }
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='text'");
        sb.append(" class='form-control item-input ");
        sb.append(" text-"+ (Objects.nonNull(textAlign) ? textAlign.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" ' ");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        
        if (maxLength > 0) {
            sb.append(" maxlength='" + maxLength + "' ");
        }
        
        if (!StringUtils.isEmpty(placeholder)) {
            sb.append(" placeholder='(" + placeholder + ")' ");
        }
        sb.append(" id='" + textId + "' name='" + textName + "' value='" + (Objects.nonNull(textValue) ? textValue : "") + "'/>");
        return sb.toString();
    }
    
    private String getWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label'>" + labelName + "</span>");
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</div>");
        sb.append(get());
        if(!StringUtils.isEmpty(footHtml)) {
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
        if (notBlank) {
            sb.append("<span class='label-14b-red'>*</span>");
        }
        sb.append("</div>");
        sb.append(get());
        sb.append("<div class='input-group-append'>");
        sb.append(footHtml);
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getWithFoot() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append(get());
        sb.append("<div class='input-group-append'>");
        sb.append(footHtml);
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getLg() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='text'");
        sb.append(" class='form-control item-input-lg ");
        sb.append(" text-"+ (Objects.nonNull(textAlign) ? textAlign.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" ' ");
        if (maxLength > 0) {
            sb.append(" maxlength='" + maxLength + "' ");
        }
        sb.append(" id='" + textId + "' name='" + textName + "' value='" + (Objects.nonNull(textValue) ? textValue : "") + "'/>");
        return sb.toString();
    }
    
    private String getLgWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label-lg'>" + labelName + "</span>");
        sb.append("</div>");
        sb.append(getLg());
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum LabelTextSetType {

        SIMPLE             (1),
        WITH_LABEL         (2),
        WITH_LABEL_FOOT    (3),
        SIMPLE_LARGE    (4),
        LARGE_WITH_LABEL(5),
        SIMPLE_WITH_FOOT (6),
        ;
        
        /** type. */
        private int key;

        private LabelTextSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelTextSetType[] list() {
            return LabelTextSetType.values();
        }
        
        public static LabelTextSetType keyOf(int key) {
            for(LabelTextSetType type : LabelTextSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
