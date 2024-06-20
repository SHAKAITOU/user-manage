package cn.caam.gs.common.html.element;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckBoxSet {
    
    

    private int labelWidth;
    private String labelName;
    private String prefixId;
    private String prefixName;
    private List<String> selectedValues;
    private List<HtmlRadio> radios;
    
    private String id;
    @Default
    private String name = "";
    private boolean checkedFlg;
    @Default
    private String value = "";
    private HtmlRadio item;
    
    private CheckBoxSetType outPutType;
    
    public String html() {
        if(outPutType == CheckBoxSetType.MULTIPLE) {
            return getCheckBoxs();
        }else if(outPutType == CheckBoxSetType.MULTIPLE_WITH_LABEL) {
            return getCheckBoxsWithLabel();
        }else if(outPutType == CheckBoxSetType.SINGLE_FOR_TABLE) {
            return getTableCheckBox();
        }else if(outPutType == CheckBoxSetType.MULTIPLE_BUTTON) {
            return getButtonCheckBoxs(false);
        }else if(outPutType == CheckBoxSetType.MULTIPLE_BUTTON_WITH_LABEL) {
            return getButtonCheckBoxsWithLabel(false);
        }else if(outPutType == CheckBoxSetType.LARGE_MULTIPLE_BUTTON_WITH_LABEL) {
            return getButtonCheckBoxsWithLabel(true);
        }else {
            return getCheckBox();
        }
    }
    
    private String getCheckBoxsWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group item-row'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label'>" + labelName + "</span>");
        sb.append("</div>");
        sb.append(getCheckBoxs());
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getButtonCheckBoxsWithLabel(boolean largeFlg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label"+ (largeFlg ? "-lg" : "") +"'>" + labelName + "</span>");
        sb.append("</div>");
        sb.append(getButtonCheckBoxs(largeFlg));
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getCheckBoxs() {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        for(HtmlRadio radio : radios) {
            boolean checkedFlg = false;
            if(selectedValues.stream().filter(s -> Objects.nonNull(s)).anyMatch(s -> s.equals(radio.getValue()))) {
                checkedFlg = true;
            }
            sb.append(getCheckBox(prefixId+"_"+index, prefixName+"["+index+"]", checkedFlg, radio));
            sb.append("&nbsp;&nbsp;");
            index++;
        }
        return sb.toString();
    }
    
    private String getButtonCheckBoxs(boolean largeFlg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='btn-group-toggle' data-toggle='buttons'>");
        int index = 0;
        for(HtmlRadio radio : radios) {
            boolean checkedFlg = false;
            if(selectedValues.stream().filter(s -> Objects.nonNull(s)).anyMatch(s -> s.equals(radio.getValue()))) {
                checkedFlg = true;
            }
            sb.append(getButtonCheckBox(prefixId+"_"+index, prefixName+"["+index+"]", checkedFlg, radio, largeFlg));
            index++;
        }
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getTableCheckBox() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='custom-control custom-switch' style='margin-left: 5px;'>");
        sb.append("<input type='checkbox' class='custom-control-input " + name + "' ");
        sb.append(" id='"+id+"'");
        sb.append(" name='"+name+"'");
        sb.append(" value='"+value+"'");
        sb.append(" style='height:20px; padding-top: 1px;'");
        if(checkedFlg) {
            sb.append(" checked");
        }
        sb.append(">");
        sb.append("<label class='custom-control-label' for='"+id+"'>");
        sb.append("&nbsp;");
        sb.append("</label>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getCheckBox() {
        return getCheckBox(id, name, checkedFlg, item);
    }
    
    private String getCheckBox(String id, String name, boolean checkedFlg, HtmlRadio item) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='custom-control custom-switch'>");
        sb.append("<input type='checkbox' class='custom-control-input' ");
        sb.append(" id='"+id+"'");
        sb.append(" name='"+name+"'");
        sb.append(" value='"+item.getValue()+"'");
        if(checkedFlg) {
            sb.append(" checked");
        }
        sb.append(">");
        sb.append("<label class='custom-control-label' for='"+id+"'>");
        sb.append(item.getName());
        sb.append("</label>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String getButtonCheckBox(String id, String name, boolean checkedFlg, HtmlRadio item, boolean largeFlg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<label class='btn btn-outline-"+item.getClassType().getKey());
        if(checkedFlg) {
            sb.append(" active");
        }
        if(largeFlg) {
            sb.append(" btn_60h");
        }
        sb.append("' ");
        sb.append(" style='margin-left: 1px;margin-bottom: 2px;'>");
        sb.append("<input type='checkbox' class='custom-control-input' ");
        sb.append(" id='"+id+"'");
        sb.append(" name='"+name+"'");
        sb.append(" value='"+item.getValue()+"'");
        sb.append(" autocomplete='off'");
        if(checkedFlg) {
            sb.append(" checked=''");
        }
        sb.append(">");
        sb.append(item.getName());
        sb.append("</label>");
        return sb.toString();
    }
    
    public enum CheckBoxSetType {

        SINGLE                     (1),
        MULTIPLE                 (2),
        MULTIPLE_WITH_LABEL        (3),
        SINGLE_FOR_TABLE        (4),
        MULTIPLE_BUTTON            (5),
        MULTIPLE_BUTTON_WITH_LABEL(6),
        LARGE_MULTIPLE_BUTTON_WITH_LABEL(7),
        
        ;
        
        /** type. */
        private int key;

        private CheckBoxSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public CheckBoxSetType[] list() {
            return CheckBoxSetType.values();
        }
        
        public static CheckBoxSetType keyOf(int key) {
            for(CheckBoxSetType type : CheckBoxSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
