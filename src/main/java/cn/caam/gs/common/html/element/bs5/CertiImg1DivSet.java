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
public class CertiImg1DivSet {

    @Default
    private CertiImg1DivType certiImg1DivType  = CertiImg1DivType.CHINA;
    private CssGridsType grids;
    private GridFlexType gridFlexType;
    public String html() {
        if (certiImg1DivType == CertiImg1DivType.CHINA) {
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
        sb.append("<div class='certi-china-bgImg1'>");
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
    
    public enum CertiImg1DivType {

        CHINA                     (1),
        GANSU                     (2),
        ;
        
        /** type. */
        private int key;

        private CertiImg1DivType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public CertiImg1DivType[] list() {
            return CertiImg1DivType.values();
        }
        
        public static CertiImg1DivType keyOf(int key) {
            for(CertiImg1DivType type : CertiImg1DivType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
