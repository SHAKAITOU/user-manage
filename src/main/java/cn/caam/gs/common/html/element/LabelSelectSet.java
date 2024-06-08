package cn.caam.gs.common.html.element;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelSelectSet {
    private int labelWidth;
    private String labelName;
    private String id;
    private String name;
    private int size;
    private String selectedValue;
    private List<String> selectedValues;
    private List<HtmlRadio> radios;
    private LabelSelectSetType outPutType;
    
    public String html() {
        if(outPutType == LabelSelectSetType.WITH_LABEL) {
            return getWithLabel();
        } else if(outPutType == LabelSelectSetType.MULTI_SELECT) {
            return getMulti();
        } else if(outPutType == LabelSelectSetType.MULTI_SELECT_WITH_LABEL) {
            return getMultiWithLabel();
        } else {
            return get();
        }
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<select class='form-control input-sm item-input-select' name='"+name+"'");
        sb.append(" id='"+id+"'");
        if(size > 1) {
            sb.append(" size='"+size+"' style='height:"+(size+1)*20+"px;'");
        }
        sb.append(">");
        for(int i=0; i<radios.size(); i++) {
            sb.append("<option value='"+radios.get(i).getValue()+"'");
            if(selectedValue.equals(radios.get(i).getValue())) {
                sb.append(" selected");
            }
            
            if(radios.get(i).isDisabled()) {
                sb.append(" style='background: #868e96;'");
                sb.append(" disabled");
            }
            
            if (StringUtils.isNotEmpty(radios.get(i).getHiddenData())) {
                sb.append(" data='" + radios.get(i).getHiddenData() + "'");
            }
            sb.append(">");
            sb.append(radios.get(i).getName());
            sb.append("</option>");
        }
        sb.append("</select>");
        return sb.toString();
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
    
    private String getMultiWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:"+labelWidth+"px;'>");
        sb.append("<span class='item-input-label'>"+labelName+"</span>");
        sb.append("</div>");
        sb.append(getMulti());
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getMulti() {
        StringBuffer sb = new StringBuffer();
        sb.append("<select multiple='' class='form-control input-sm item-input-select' name='"+name+"'");
        sb.append(" id='"+id+"'");
        if(size > 1) {
            sb.append(" size='"+size+"'");
        }
        sb.append(">");
        for(int i=0; i<radios.size(); i++) {
            sb.append("<option value='"+radios.get(i).getValue()+"'");
            if(selectedValues != null) {
                for(int j=0; j<selectedValues.size(); j++) {
                    if(selectedValues.get(j).equals(radios.get(i).getValue())) {
                        sb.append(" selected");
                        continue;
                    }
                }
            }
            if(radios.get(i).isDisabled()) {
                sb.append(" style='background: #868e96;'");
                sb.append(" disabled");
            }
            
            if (StringUtils.isNotEmpty(radios.get(i).getHiddenData())) {
                sb.append(" data='" + radios.get(i).getHiddenData() + "'");
            }
            sb.append(">");
            sb.append(radios.get(i).getName());
            sb.append("</option>");
        }
        sb.append("</select>");
        return sb.toString();
    }
    
    public enum LabelSelectSetType {

        SIMPLE            (1),
        WITH_LABEL        (2),
        MULTI_SELECT    (3),
        MULTI_SELECT_WITH_LABEL    (4),
        ;
        
        /** type. */
        private int key;

        private LabelSelectSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelSelectSetType[] list() {
            return LabelSelectSetType.values();
        }
        
        public static LabelSelectSetType keyOf(int key) {
            for(LabelSelectSetType type : LabelSelectSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
