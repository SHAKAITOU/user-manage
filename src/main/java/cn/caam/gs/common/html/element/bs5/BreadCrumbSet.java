package cn.caam.gs.common.html.element.bs5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BreadCrumbSet {

    private String[] labelNames; 
    private BreadCrumb outPutType;
    
    public String html() {
        return get();
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<ol class='breadcrumb'> ");
        if(labelNames.length > 0) {
            for (String name : labelNames) {
                sb.append("<li class='breadcrumb-item label-14'>");
                sb.append(name);
                sb.append("</li>");
            }
        }
        sb.append("</ol>");
        return sb.toString();
    }
    
    
    public enum BreadCrumb {

        NORMAL                 (1),
        NARROW                 (2),
        LARGE                (3),
        ;
        
        /** type. */
        private int key;

        private BreadCrumb(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public BreadCrumb[] list() {
            return BreadCrumb.values();
        }
        
        public static BreadCrumb keyOf(int key) {
            for(BreadCrumb type : BreadCrumb.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
