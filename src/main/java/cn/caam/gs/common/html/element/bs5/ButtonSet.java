package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.html.HtmlBaseHelper;
import cn.caam.gs.common.html.element.IconSet;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ButtonSet {

    private String id;
    private String keyValue; 
    private String buttonName;
    private String customizeClassName;
    private String classDistinctName;
    private String customizeTooltip;
    private IconSet iconSet;
    
    @Builder.Default
    boolean isBorderOnly = false;
    private CssClassType classType; 
    private ButtonSetType outPutType;
    private CssGridsType grids;
    private CssFontSizeType fontSize;
    private GridFlexType gridFlexType;
    
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
        } else if(outPutType == ButtonSetType.GRID_STYLE) {
            return getContext(get());
        } else {
            return getContext(get());
        }
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button style='margin-top:2px'");
        if(StringUtils.isNotEmpty(keyValue)) {
            sb.append(" data='"+keyValue+"' ");
        }
        sb.append(" id='"+id+"' ");
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" "+(Objects.nonNull(classDistinctName) ? classDistinctName : "")
                +" "+(Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14")
                +" btn_com' ");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        if (Objects.nonNull(iconSet)) {
            sb.append(iconSet.html() + "&nbsp;&nbsp;");
        }
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getContext(String coreStr) {
        StringBuffer sb = new StringBuffer();
        if (Objects.nonNull(grids)) {
            sb.append("<div ");
            sb.append("class='col-xl-" + grids.getKey() 
            + " col-xxl-" + grids.getKey() 
            + " col-lg-" + grids.getKey()
            + " col-md-12 col-sm-12"
            + " " + (Objects.nonNull(gridFlexType) ? gridFlexType.getKey() : GridFlexType.LEFT.getKey()) + " '>");
            sb.append(coreStr);
            sb.append("</div>");
        } else {
            sb.append(coreStr);
        }
        return sb.toString();
    }
    
    private String getClose(boolean hidden) {
        StringBuffer sb = new StringBuffer();
        sb.append("<button id='"+id+"' style='margin-top:2px'");
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" "+(Objects.nonNull(fontSize) ? fontSize.getKey() + " btn-block" : "label-14 btn-block")
                +" btn_com' ");
        sb.append(" data-dismiss='modal' aria-label='Close'");
        if(hidden) {
            sb.append(" style='display: none'");
        }
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(">");
        if (Objects.nonNull(iconSet)) {
            sb.append(iconSet.html() + "&nbsp;&nbsp;");
        }
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getTable() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button data='"+keyValue+"' style='margin-top:2px' ");
        if(StringUtils.isNotEmpty(id)) {
            sb.append(" id='"+id+"'");
        }
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" "+(Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14")
                +" btn_com_tb btn-sm ");
        sb.append(" "+(Objects.nonNull(classDistinctName) ? classDistinctName : ""));
        sb.append("'");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        if (Objects.nonNull(iconSet)) {
            sb.append(iconSet.html() + "&nbsp;&nbsp;");
        }
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getCustomize() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button data='"+keyValue+"' style='margin-top:2px' ");
        if(StringUtils.isNotEmpty(id)) {
            sb.append(" id='"+id+"'");
        }
        sb.append(" type='button' class='btn btn-"
                +(isBorderOnly? "outline-" : "")
                +(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())
                +" "+(Objects.nonNull(fontSize) ? fontSize.getKey() + " btn-block" : "label-14 btn-block")
                +" btn_com ");
        sb.append(" "+(Objects.nonNull(customizeClassName) ? customizeClassName : ""));
        sb.append(" "+(Objects.nonNull(classDistinctName) ? classDistinctName : ""));
        sb.append("'");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        if (Objects.nonNull(iconSet)) {
            sb.append(iconSet.html() + "&nbsp;&nbsp;");
        }
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    private String getAlinkMenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("<button style='margin-top:2px' type='button' class='btn btn-outline-"+(Objects.nonNull(classType) ? classType.getKey() : CssClassType.PRIMARY.getKey())+" btn_com btn-block btn_30h_card text-truncate' ");
        sb.append(" id='"+id+"' href='#'");
        if(!StringUtils.isEmpty(customizeTooltip)) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip)+"'");
        }
        sb.append(" >");
        if (Objects.nonNull(iconSet)) {
            sb.append(iconSet + "&nbsp;&nbsp;");
        }
        sb.append(buttonName);
        sb.append("</button>");
        return sb.toString();
    }
    
    public enum ButtonSetType {

        NORMAL           (1),
        TABLE            (2),
        CUSTOMIZE        (3),
        CLOSE            (4),
        CLOSE_NOT_SHOW   (5),
        A_LINK_MENU      (6),
        GRID_STYLE       (7),
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
