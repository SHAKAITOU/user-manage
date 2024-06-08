package cn.caam.gs.common.html.element;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelRadioSet {
    
    private int labelWidth;
    private String labelName;
    private String id;
    private String name;
    private String selectedValue;
    private List<HtmlRadio> radios;
    private LabelRadioSetType outPutType;
    
    public String html() {
        if(outPutType == LabelRadioSetType.WITH_LABEL) {
            return getWithLabel();
        }else {
            return get();
        }
    }
    
    private String getWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:"+labelWidth+"px;'>");
        sb.append("<span class='item-input-label'>"+labelName+"</span>");
        sb.append("</div>");
        sb.append(get());
        sb.append("</div>");
        return sb.toString();
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='btn-group btn-group-toggle' data-toggle='buttons'>");
        for(int i=0; i<radios.size(); i++) {
            sb.append("<label ");
            sb.append(" style='margin-left: 1px;' ");
            sb.append("class='btn btn-outline-");
            sb.append(radios.get(i).getClassType().getKey());
            if(selectedValue.equals(radios.get(i).getValue())) {
                sb.append(" active");
            }
            sb.append("'>");
            sb.append("<input type='radio' name='"+name+"'");
            sb.append(" id='"+id+radios.get(i).getValue()+"'");
            sb.append(" autocomplete='off'");
            sb.append(" value='"+radios.get(i).getValue()+"'");
            if(selectedValue.equals(radios.get(i).getValue())) {
                sb.append(" checked");
            }
            sb.append("/>");
            sb.append(radios.get(i).getName());
            sb.append("</label>");
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum LabelRadioSetType {

        SIMPLE        (1),
        WITH_LABEL    (2),
        ;
        
        /** type. */
        private int key;

        private LabelRadioSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelRadioSetType[] list() {
            return LabelRadioSetType.values();
        }
        
        public static LabelRadioSetType keyOf(int key) {
            for(LabelRadioSetType type : LabelRadioSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
