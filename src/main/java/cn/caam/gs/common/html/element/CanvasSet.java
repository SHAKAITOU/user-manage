package cn.caam.gs.common.html.element;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CanvasSet {

    private String id; 
    private int width; 
    private int height; 
    private LabelType outPutType;
    
    public String html() {
        if(outPutType == LabelType.NARROW) {
            return get();
        }else if(outPutType == LabelType.LARGE) {
            return get();
        }else {
            return get();
        }
    }
    
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        //label[
        sb.append("<div class='col-md-12 col-sx-12 div-center' style='height:"+height+"px'>");
        sb.append("<canvas id='"+id+"' height='"+height+"' ");
        if (width > 0) {
            sb.append(" height='"+width+"' ");
        }
        sb.append(" style='border-radius: 5px;background-color: #fef4f4;'>");
        sb.append("</canvas>");
        sb.append("</div>");
        //]
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
