package cn.caam.gs.common.html.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelPasswordSet {
    private int labelWidth; 
    private String labelName; 
    private String textId; 
    private String textName;
    private String textValue;
    private LabelPasswordSetType outPutType;
    private int maxLength;
    private String placeholder;
    @Default
    private boolean notBlank = true;
    
    public String html() {
        if(outPutType == LabelPasswordSetType.WITH_LABEL) {
            return getWithLabel();
        }else {
            return get();
        }
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='password'");
        sb.append(" class='form-control item-input' ");
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
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum LabelPasswordSetType {

        SIMPLE             (1),
        WITH_LABEL         (2),
        ;
        
        /** type. */
        private int key;

        private LabelPasswordSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelPasswordSetType[] list() {
            return LabelPasswordSetType.values();
        }
        
        public static LabelPasswordSetType keyOf(int key) {
            for(LabelPasswordSetType type : LabelPasswordSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
