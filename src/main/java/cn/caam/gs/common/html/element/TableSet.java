package cn.caam.gs.common.html.element;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableSet {

    private String id;
    private int bodyHeight;
    private int bodyWidth;
    
    private TrSet headTr;
    private List<TrSet> bodyList;
    private TableSetType outPutType;
    
    public String html() {
        if(outPutType == TableSetType.NORMAL) {
            return get();
        }else {
            return get();
        }
    }
    private String get() {
        StringBuffer sb = new StringBuffer();

        sb.append("<table id='"+id+"' class='table table-striped table-bordered table-hover table-sm table-fixed' ");
        /*
        if(Objects.nonNull(minWidthType)) {
            sb.append(" style='min-width: " + TableMinWidthType.LG.getKey() + "px'");
        } else {
            sb.append(" style='min-width: " + minWidthType.getKey() + "px'");
        }
        */
        sb.append(" >");
        sb.append("<thead>");
        sb.append(headTr.html());
        sb.append("</thead>");
        sb.append("<tbody ");
        sb.append(" style='");
        if (bodyHeight > 0) {
            sb.append(" height:"+bodyHeight+"px;");
        }
        if (bodyWidth > 0) {
            sb.append(" width:"+bodyWidth+"px;");
        }
        sb.append("'>");
        if(Objects.nonNull(bodyList)){
            for(TrSet trSet : bodyList) {
                sb.append(trSet.html());
            }
        }
        sb.append("</tbody>");
        sb.append("</table>");
        return sb.toString();
    }
    
    public enum TableSetType {

        NORMAL                         (1),
        ;
        
        /** type. */
        private int key;

        private TableSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public TableSetType[] list() {
            return TableSetType.values();
        }
        
        public static TableSetType keyOf(int key) {
            for(TableSetType type : TableSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
    
    public enum TableMinWidthType {

        SM                         (580),
        MD                         (780),
        LG                         (980),
        ;
        
        /** type. */
        private int key;

        private TableMinWidthType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public TableMinWidthType[] list() {
            return TableMinWidthType.values();
        }
        
        public static TableMinWidthType keyOf(int key) {
            for(TableMinWidthType type : TableMinWidthType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
