package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import cn.caam.gs.common.html.element.bs5.DivContainerSet.DivContainerSetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertiImg4DivSet {


    private String validStartDate;
    private String validEndDate;
    
    @Default
    private CertiImg4DivType certiImg4DivType  = CertiImg4DivType.CHINA;
    @Default
    private CssFontSizeType fontSize = CssFontSizeType.LABEL_16;
    @Default
    private CssClassType classType = CssClassType.PRIMARY;
    private CssGridsType grids;
    private GridFlexType gridFlexType;
    public String html() {
        if (certiImg4DivType == CertiImg4DivType.CHINA) {
            return getChina();
        } else {
            return getGansu();
        }
        
    }
    
    private String getGansu() {
        StringBuffer sb = new StringBuffer();
        
        return getContext(sb.toString());
    }

    private String getChina() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='");
        sb.append(classType.getKey());
        sb.append(" ");
        sb.append(fontSize.getKey());
        sb.append(" certi-china-bgImg4 '");
        sb.append(">");
        sb.append("<div class='row'>");
        //左半部分
        //--------------
        sb.append("<div class='col-6'>");
        //有效期至
        sb.append("<div class='row'>");
        sb.append("<div class='col-12'>");
        sb.append("<div style='padding-left:45px; padding-top:163px;'>");
        sb.append(validStartDate);
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        sb.append("&nbsp;&nbsp;&nbsp;");
        sb.append(validEndDate);
        sb.append("</div>");
        sb.append("</div>");
        
        
        sb.append("</div>");
        //--------------
        //右半部分
        //--------------
        sb.append("<div class='col-6'>");
        sb.append("</div>");
        //--------------
        
        sb.append("</div>");
        sb.append("</div>");
        
        return getContext(sb.toString());
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
    
    public enum CertiImg4DivType {

        CHINA                     (1),
        GANSU                     (2),
        ;
        
        /** type. */
        private int key;

        private CertiImg4DivType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public CertiImg4DivType[] list() {
            return CertiImg4DivType.values();
        }
        
        public static CertiImg4DivType keyOf(int key) {
            for(CertiImg4DivType type : CertiImg4DivType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
