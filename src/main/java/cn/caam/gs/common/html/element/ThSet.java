package cn.caam.gs.common.html.element;


import java.util.Objects;

import cn.caam.gs.app.GlobalConstants;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.html.element.bs5.IconSet;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.bs5.IconSet.IconSetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThSet {
    
    private int grids;
    private int height;
    private int width;
    private String[] contexts;
    private CssFontSizeType fontSize;
    private CssAlignType align;
    private ThSetType outPutType;
    
    public String html() {
        if(outPutType == ThSetType.INDEX) {
            return getIndex();
        } else {
            return get();
        }
    }
    
    private String getIndex() {
        StringBuffer sb = new StringBuffer();
        sb.append("<th scope='col' class=' ");
        sb.append(IconSetCss.NOMAL_14.getKey());
        sb.append(" text-center ");
        if (width > 0) {
            sb.append("' width='"+width+"' ");
        } else {
            sb.append(" col-xs-"+grids+"' ");
        }
        sb.append(" height='"+(height == 0 ? GlobalConstants.TH_DEFAULT_HEIGHT : height)+"'");        
        sb.append(">");
        sb.append(setMiddleForCell(CssAlignType.CENTER, 
                IconSet.builder().type(IconSetType.INDEX_MARK).css(IconSetCss.NOMAL_14).build().html()));
        sb.append("</th>");
        return sb.toString();
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<th class=' ");
        sb.append((Objects.nonNull(fontSize) ? fontSize.getKey(): CssFontSizeType.LABEL_12B.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        if (width > 0) {
            sb.append("' width='"+width+"' ");
        } else {
            sb.append(" col-xs-"+grids+"' ");
        }
        sb.append(" height='"+(height == 0 ? GlobalConstants.TH_DEFAULT_HEIGHT : height)+"'");
        sb.append(">");
        sb.append(setMiddleForCell(contexts));
        sb.append("</th>");
        return sb.toString();
    }
    
    private String setMiddleForCell(String... contexts) {
        return setMiddleForCell((Objects.nonNull(align) ? align : CssAlignType.LEFT), contexts);
    }
    
    private String setMiddleForCell(CssAlignType cssAlignType, String context) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div style='display: table;height:100%;width:100%'>");
        sb.append("<div style='display: table-cell; vertical-align: middle;");
        sb.append(" text-align: "+cssAlignType.getKey()+";'>");
        sb.append(context);
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    private String setMiddleForCell(CssAlignType cssAlignType, String... contexts) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div style='display: table;height:100%;width:100%'>");
        sb.append("<div style='display: table-cell; vertical-align: middle;");
        sb.append(" text-align: "+cssAlignType.getKey()+";'>");
        for (String context : contexts) {
            sb.append(context);
        }
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum ThSetType {

        INDEX                     (1),
        NORMAL                     (2),
        ;
        
        /** type. */
        private int key;

        private ThSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public ThSetType[] list() {
            return ThSetType.values();
        }
        
        public static ThSetType keyOf(int key) {
            for(ThSetType type : ThSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
