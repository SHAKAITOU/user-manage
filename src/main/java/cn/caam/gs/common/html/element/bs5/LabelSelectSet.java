package cn.caam.gs.common.html.element.bs5;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.caam.gs.GlobalConstants;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.HtmlRadio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelSelectSet {

    private String labelName;
    private String id;
    private String name;
    private int size;
    private String selectedValue;
    private List<String> selectedValues;
    private List<HtmlRadio> radios;
    private LabelSelectSetType outPutType;
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    
    public String html() {
        if(outPutType == LabelSelectSetType.WITH_LABEL) {
            return getWithLabel(false);
        } else if(outPutType == LabelSelectSetType.MULTI_SELECT) {
            return getMulti();
        } else if(outPutType == LabelSelectSetType.MULTI_SELECT_WITH_LABEL) {
            return getWithLabel(true);
        } else {
            return get();
        }
    }
    
    private String getWithLabel(boolean withMulti) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");
        sb.append("<span class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName + "</span>");
        sb.append(withMulti ? getMulti() : get());
        sb.append("</div>");
        return getContext(sb.toString());
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<select style='background-color:" + GlobalConstants.INPUT_BG_COLOER + "' class='form-control ");
        sb.append((Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ));
        sb.append("' name='"+name+"'");
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
            
            sb.append(" class='" + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) + "'");
            
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
 
    
    private String getMulti() {
        StringBuffer sb = new StringBuffer();
        sb.append("<select multiple='' style='background-color:" + GlobalConstants.INPUT_BG_COLOER + "' class='form-control input-sm item-input-select' name='"+name+"'");
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
    
    private String getContext(String coreStr) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        if (Objects.nonNull(grids)) {
            sb.append("class='col-xl-" + grids.getKey() 
            + " col-xxl-" + grids.getKey() 
            + " col-lg-" + grids.getKey()
            + " col-md-12 col-sm-12"
            +" '>");
        } else {
            sb.append("class='col'>");
        }
        sb.append(coreStr);
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum LabelSelectSetType {

        SIMPLE            (1),
        WITH_LABEL        (2),
        MULTI_SELECT      (3),
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
