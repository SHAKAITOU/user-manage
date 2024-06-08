package cn.caam.gs.common.html.element;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.html.element.SpanSet.SpanSetType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelSet {

    

    
    private int labelWidth; 
    private String id; 
    private String labelName; 
    private LabelType outPutType;
    
    public String html() {
        if(outPutType == LabelType.NARROW) {
            return getNarrow();
        }else if(outPutType == LabelType.LARGE) {
            return getLarge();
        }else {
            return get();
        }
    }
    
    
    private String getNarrow() {
        StringBuffer sb = new StringBuffer();
        //label[
        sb.append("<div class='td-t2d input-group-append' ");
        if(labelWidth > 0) {
            sb.append(" style='width:"+labelWidth+"px;'");
        }
        sb.append(">");
        SpanSet spanLabel = SpanSet.builder().outPutType(SpanSetType.NARROW_SIMPLE)
                                .id(id)
                                .context(labelName)
                                .build();
        sb.append(spanLabel.html());
        sb.append("</div>");
        //]
        return sb.toString();
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='td-t2d input-group-append' ");
        if(labelWidth > 0) {
            sb.append(" style='width:"+labelWidth+"px;'");
        }
        sb.append(">");
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='item-input-label'>"+labelName+"</span>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getLarge() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='td-t2d input-group-append' ");
        if(labelWidth > 0) {
            sb.append(" style='width:"+labelWidth+"px;'");
        }
        sb.append(">");
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='item-input-label-lg'>"+labelName+"</span>");
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum LabelType {

        NORMAL                 (1),
        NARROW                 (2),
        LARGE                (3),
        ;
        
        /** type. */
        private int key;

        private LabelType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelType[] list() {
            return LabelType.values();
        }
        
        public static LabelType keyOf(int key) {
            for(LabelType type : LabelType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
