package cn.caam.gs.common.html.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssClassType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrSet {
    
    private Map<String, String> properties;
    private List<ThSet> thSetList;
    private List<TdSet> tdSetList;
    private CssClassType headType;
    private CssClassType textType;
    private TrSetType outPutType;
    
    public String html() {
        if(outPutType == TrSetType.HEAD_ROW) {
            return get(true);
        }else {
            return get(false);
        }
    }
    
    public void addTd(TdSet td) {
        if(Objects.isNull(tdSetList)) {
            tdSetList = new ArrayList<TdSet>();
        }
        tdSetList.add(td);
    }
    
    public void addTh(ThSet th) {
        if(Objects.isNull(thSetList)) {
            thSetList = new ArrayList<ThSet>();
        }
        thSetList.add(th);
    }
    
    
    private String get(boolean headRow) {
        StringBuffer sb = new StringBuffer();
        sb.append("<tr ");
        sb.append("class='");
        if(Objects.nonNull(headType)) {
            sb.append(" table-"+ headType.getKey());
        }
        if(Objects.nonNull(textType)) {
            sb.append(" text-"+textType.getKey());
        }
        sb.append("' ");
        if(Objects.nonNull(properties)){
            for(Map.Entry<String, String> property : properties.entrySet()) {
                sb.append(property.getKey()+"='"+property.getValue()+"' ");
            }
        }
        sb.append(">");
        if(headRow) {
            if(Objects.nonNull(thSetList)){
                for(ThSet thSet : thSetList) {
                    sb.append(thSet.html());
                }
            }
        }else {
            if(Objects.nonNull(tdSetList)) {
                for(TdSet tdSet : tdSetList) {
                    sb.append(tdSet.html());
                }
            }
        }
        sb.append("</tr>");
        return sb.toString();
    }
    
    public enum TrSetType {

        HEAD_ROW     (1),
        BODY_ROW     (2),
        ;
        
        /** type. */
        private int key;

        private TrSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public TrSetType[] list() {
            return TrSetType.values();
        }
        
        public static TrSetType keyOf(int key) {
            for(TrSetType type : TrSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
