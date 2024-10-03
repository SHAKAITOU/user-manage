package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertiImgDivSet {

    @Default
    private CertiImgDivType certiImgDivType  = CertiImgDivType.CHINA;
    private CssGridsType grids;
    private GridFlexType gridFlexType;
    private String id;
    private String src;
    private String extent;
    @Default
    private int imgWidth = 0;
    @Default
    private int imgHeight = 0;
    private String base64String;
    @Default
    private boolean visible = true;
    
    public String html() {
        if (certiImgDivType == CertiImgDivType.CHINA) {
            return getChina();
        } else {
            return getGansu();
        }
        
    }
    
    private String getGansu() {
//        StringBuffer sb = new StringBuffer();
//        
//        return getContext(sb.toString());
    	return getChina();
    }

    private String getChina() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='center-block");
        if (!visible) {
        	sb.append(" d-none");
        }
        sb.append("'>");
        sb.append("<img style='");
        if (imgWidth > 0) {
            sb.append(" width:"+imgWidth+"px;");
        }
        
        if(imgHeight > 0) {
            sb.append(" height:"+imgHeight+"px;");
            sb.append(" object-fit:contain;");
        }
        sb.append(" ' ");
        if(!StringUtils.isBlank(src)) {
            sb.append(" src='"+src+"'");
        }else {
            sb.append(" src='data:image/"+extent+";base64, "+base64String+"'");
        }
        sb.append(" id='" + id + "'>");
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
    
    public enum CertiImgDivType {

        CHINA                     (1),
        GANSU                     (2),
        ;
        
        /** type. */
        private int key;

        private CertiImgDivType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public CertiImgDivType[] list() {
            return CertiImgDivType.values();
        }
        
        public static CertiImgDivType keyOf(int key) {
            for(CertiImgDivType type : CertiImgDivType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
