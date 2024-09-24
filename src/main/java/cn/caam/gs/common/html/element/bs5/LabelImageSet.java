package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.caam.gs.common.enums.CssGridsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelImageSet {

    private int labelWidth; 
    private String labelName; 
    private String id;
    @Default
    private int imgWidth = 0;
    @Default
    private int imgHeight = 0;
    private String src;
    private String base64String;
    private String extent;
    private LabelImageSetType outPutType;
    private CssGridsType grids;
    
    public String html() {
        if(outPutType == LabelImageSetType.WITH_LABEL) {
            return getWithLabel();
        }else {
            return get();
        }
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
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
        return getContext(sb.toString());
    }
    
    private String getWithLabel() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group'>");
        sb.append("<div class='td-t2d input-group-append' style='width:" + labelWidth + "px;'>");
        sb.append("<span class='item-input-label'>" + labelName + "</span>");
        sb.append("</div>");
        sb.append(get());
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
            +" '>");
            sb.append(coreStr);
            sb.append("</div>");
        } else {
            sb.append(coreStr);
        }
        return sb.toString();
    }

    public enum LabelImageSetType {

        SIMPLE             (1),
        WITH_LABEL         (2),
        ;
        
        /** type. */
        private int key;

        private LabelImageSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public LabelImageSetType[] list() {
            return LabelImageSetType.values();
        }
        
        public static LabelImageSetType keyOf(int key) {
            for(LabelImageSetType type : LabelImageSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
