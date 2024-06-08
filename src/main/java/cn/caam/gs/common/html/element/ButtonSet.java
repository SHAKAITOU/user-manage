package cn.caam.gs.common.html.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.HtmlBaseHelper;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ButtonSet {

    String id;
    String keyValue; 
    String buttonName;
    String customizeClassName;
    String classDistinctName;
    private String customizeTooltip;
    @Builder.Default
    boolean isBorderOnly = false;
    private CssClassType classType; 
    private ButtonSetType outPutType;
    
    public String html() {
        if(outPutType == ButtonSetType.TABLE) {
            return getTable();
        }else if(outPutType == ButtonSetType.CUSTOMIZE) {
            return getCustomize();
        }else if(outPutType == ButtonSetType.CLOSE) {
            return getClose(false);
        }else if(outPutType == ButtonSetType.CLOSE_NOT_SHOW) {
            return getClose(true);
        }else if(outPutType == ButtonSetType.A_LINK_MENU) {
            return getAlinkMenu();
        }else {
            return get();
        }
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button ");
        if(StringUtils.isNotEmpty(keyValue)) {
            sb.append(" data='"+keyValue+"' ");
        }
        sb.append(" id='"+id+"' ");
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" "+(Objects.nonNull(classDistinctName) ? classDistinctName : "")
                +" btn_com' ");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getClose(boolean hidden) {
        StringBuffer sb = new StringBuffer();
        sb.append("<button id='"+id+"' ");
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" btn_com' ");
        sb.append(" data-dismiss='modal' aria-label='Close'");
        if(hidden) {
            sb.append(" style='display: none'");
        }
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(">");
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getTable() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button data='"+keyValue+"' ");
        if(StringUtils.isNotEmpty(id)) {
            sb.append(" id='"+id+"'");
        }
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" btn_com_tb btn-sm ");
        sb.append(" "+(Objects.nonNull(classDistinctName) ? classDistinctName : ""));
        sb.append("'");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getCustomize() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button data='"+keyValue+"' ");
        if(StringUtils.isNotEmpty(id)) {
            sb.append(" id='"+id+"'");
        }
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" btn_com ");
        sb.append(" "+(Objects.nonNull(customizeClassName) ? customizeClassName : ""));
        sb.append(" "+(Objects.nonNull(classDistinctName) ? classDistinctName : ""));
        sb.append("'");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getAlinkMenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button type='button' class='btn btn-outline-"+(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())+" btn_com btn-block btn_30h_card text-truncate' ");
        sb.append(" id='"+id+"' href='#'");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    public enum ButtonSetType {

        NORMAL            (1),
        TABLE            (2),
        CUSTOMIZE        (3),
        CLOSE            (4),
        CLOSE_NOT_SHOW    (5),
        A_LINK_MENU        (6),
        ;
        
        /** type. */
        private int key;

        private ButtonSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public ButtonSetType[] list() {
            return ButtonSetType.values();
        }
        
        public static ButtonSetType keyOf(int key) {
            for(ButtonSetType type : ButtonSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
