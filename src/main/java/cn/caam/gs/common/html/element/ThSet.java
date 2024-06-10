package cn.caam.gs.common.html.element;


import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.html.HtmlBaseHelper;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.common.util.UtilConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThSet {
    
    private int grids;
    private int height;
    private int width = 0;
    private String context;
    private CssFontSizeType fontSize;
    private CssAlignType align;
    private String tooltipContext;
    private ThSetType outPutType;
    
    public String html() {
        if(outPutType == ThSetType.INDEX) {
            return getIndex();
        }else if(outPutType == ThSetType.WITH_TRIM) {
            return getWithTrim(false);
        }else if(outPutType == ThSetType.WITH_CUSTOMIZE_TOOLTIP) {
            return getWithTrim(true);
        }else {
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
        sb.append(" height='"+(height == 0 ? HtmlViewBaseHelper.TH_DEFAULT_HEIGHT : height)+"'");        
        sb.append(">");
        sb.append(setMiddleForCell(
                IconSet.builder().type(IconSetType.INDEX_MARK).css(IconSetCss.NOMAL_14).build().html(), CssAlignType.CENTER));
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
        sb.append(" height='"+(height == 0 ? HtmlViewBaseHelper.TH_DEFAULT_HEIGHT : height)+"'");
        sb.append(">");
        sb.append(setMiddleForCell(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE));
        sb.append("</th>");
        return sb.toString();
    }
    
    private String getWithTrim(boolean customizeTooltip) {
        StringBuffer sb = new StringBuffer();
        int byteLength = StringUtility.byteLength(context);
        int maxLength = HtmlBaseHelper.getMaxLengthByGrids(grids);
        sb.append("<th class=' ");
        sb.append((Objects.nonNull(fontSize) ? fontSize.getKey(): CssFontSizeType.LABEL_12B.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        if (width > 0) {
            sb.append("' width='"+width+"' ");
        } else {
            sb.append(" col-xs-"+grids+"' ");
        }
        if(byteLength > maxLength) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip ? tooltipContext : context)+"'");
        }
        sb.append(" height='"+(height == 0 ? HtmlViewBaseHelper.TH_DEFAULT_HEIGHT : height)+"'");
        sb.append(">");
        sb.append(setMiddleForCell(HtmlBaseHelper.trimFitForTd(grids, context, false)));
        sb.append("</th>");
        return sb.toString();
    }
    
    private String setMiddleForCell(String context) {
        return setMiddleForCell(context, (Objects.nonNull(align) ? align : CssAlignType.LEFT));
    }
    
    private String setMiddleForCell(String context, CssAlignType cssAlignType) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div style='display: table;height:100%;width:100%'>");
        sb.append("<div style='display: table-cell; vertical-align: middle;");
        sb.append(" text-align: "+cssAlignType.getKey()+";'>");
        sb.append(context);
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();
    }
    
    public enum ThSetType {

        INDEX                     (1),
        NORMAL                     (2),
        WITH_TRIM                 (3),
        WITH_CUSTOMIZE_TOOLTIP     (4),
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
