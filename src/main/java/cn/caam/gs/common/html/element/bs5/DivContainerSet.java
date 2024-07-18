package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivContainerSet {

    private int scrollHeight;
    private String id;
    private String[] contexts;
    private DivContainerSetType outPutType;
    
    public String html() {
        if(outPutType == DivContainerSetType.SCROLL) {
            return getScroll();
        }else {
            return getSimple();
        }
    }
    
    private String getSimple() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='container'");
        if(!StringUtils.isEmpty(id)) {
            sb.append(" id='"+id+"'");
        }
        sb.append(">");
        for(String context : contexts) {
            if (Objects.nonNull(context)) {
                context  += "<div class='col-12'>";
                sb.append(context);
                sb.append("</div>");
            }
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getScroll() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='container div-scrollable'");
        if(!StringUtils.isEmpty(id)) {
            sb.append(" id='"+id+"'");
        }
        sb.append(" style='min-width: 100%;max-width: 100%; max-height:"+scrollHeight+"px; min-height:"+scrollHeight+"px;'>");
        for(String context : contexts) {
            context  += "<div class='col-12'>";
            sb.append(context);
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum DivContainerSetType {

        SIMPLE                     (1),
        SCROLL                     (2),
        ;
        
        /** type. */
        private int key;

        private DivContainerSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public DivContainerSetType[] list() {
            return DivContainerSetType.values();
        }
        
        public static DivContainerSetType keyOf(int key) {
            for(DivContainerSetType type : DivContainerSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
