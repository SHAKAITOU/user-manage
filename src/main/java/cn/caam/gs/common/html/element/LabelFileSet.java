package cn.caam.gs.common.html.element;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelFileSet {

    private int labelWidth; 
    private String labelName; 
    private String textId; 
    private String textName;
    private String footHtml;
    private LabelFileSetType outPutType;
    
    public String html() {
        if(outPutType == LabelFileSetType.WITH_LABEL) {
            return getWithLabel();
        }else {
            return get();
        }
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='file' class='form-control item-input-file' ");
        sb.append(" id='" + textId + "' name='" + textName + "'>");
        return sb.toString();
    }
    
    private String getWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label'>" + labelName + "</span>");
        sb.append("</div>");
        sb.append(get());
        if(StringUtils.isNotEmpty(footHtml)) {
            sb.append("<div class='input-group-append'>");
            sb.append(footHtml);
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }

    public enum LabelFileSetType {

        SIMPLE             (1),
        WITH_LABEL         (2),
        ;
        
        /** type. */
        private int key;

        private LabelFileSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelFileSetType[] list() {
            return LabelFileSetType.values();
        }
        
        public static LabelFileSetType keyOf(int key) {
            for(LabelFileSetType type : LabelFileSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
