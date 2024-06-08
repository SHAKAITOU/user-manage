package cn.caam.gs.common.html.element;


import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.html.HtmlBaseHelper;
import cn.caam.gs.common.html.HtmlViewBaseHelper;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.common.util.UtilConstants;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TdSet {
    
    private int grids;
    private int height;
    private String context;
    private CssFontSizeType fontSize;
    private CssAlignType align;
    private String tooltipContext;
    private String footContext;
    private TdSetType outPutType;
    
    public String html() {
        if(outPutType == TdSetType.INDEX) {
            return getIndex();
        }else if(outPutType == TdSetType.WITH_TRIM) {
            return getWithTrim(false);
        }else if(outPutType == TdSetType.WITH_CUSTOMIZE_TOOLTIP) {
            return getWithTrim(true);
        }else if(outPutType == TdSetType.WITH_SUB_TRIM) {
            return getSubWithTrim(false);
        }else if(outPutType == TdSetType.WITH_SUB_CUSTOMIZE_TOOLTIP) {
            return getSubWithTrim(true);
        }else if(outPutType == TdSetType.TOOLTIP) {
            return getWithTooltip();
        }else {
            return get();
        }
    }
    
    private String getIndex() {
        StringBuffer sb = new StringBuffer();
        sb.append("<td scope='col' ");
        if (height > 0) {
            sb.append(" height='"+height+"'");
        }
        sb.append("class='");
        sb.append((Objects.nonNull(fontSize) ? align.getKey(): CssFontSizeType.LABEL_12.getKey()));
        sb.append(" text-center col-xs-"+grids+"'>"+setMiddleForCell(context, CssAlignType.CENTER)+"</td>");
        return sb.toString();
    }
    
    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<td class='");
        sb.append((Objects.nonNull(fontSize) ? align.getKey(): CssFontSizeType.LABEL_12.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" col-xs-"+grids+"'");
        if (height > 0) {
            sb.append(" height='"+height+"'");
        }
        sb.append(">");
        String contextFull = StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE;
        if(Objects.nonNull(footContext)) {
            contextFull = contextFull + footContext;
        }    
        sb.append(setMiddleForCell(contextFull));
        sb.append("</td>");
        return sb.toString();
    }
    
    private String getWithTooltip() {
        StringBuffer sb = new StringBuffer();
        sb.append("<td class=' ");
        sb.append((Objects.nonNull(fontSize) ? align.getKey(): CssFontSizeType.LABEL_12.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" col-xs-"+grids+"'");
        sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(tooltipContext)+"'");
        if (height > 0) {
            sb.append(" height='"+height+"'");
        }
        sb.append(">");
        String contextFull = StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE;
        if(Objects.nonNull(footContext)) {
            contextFull = contextFull + footContext;
        }
        sb.append(setMiddleForCell(contextFull));
        sb.append("</td>");
        return sb.toString();
    }

    private String getWithTrim(boolean customizeTooltip) {
        StringBuffer sb = new StringBuffer();
        int byteLength = StringUtility.byteLength(context);
        int maxLength = HtmlBaseHelper.getMaxLengthByGrids(grids);
        sb.append("<td class=' ");
        sb.append((Objects.nonNull(fontSize) ? align.getKey(): CssFontSizeType.LABEL_12.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" col-xs-"+grids+"'");
        if(customizeTooltip || byteLength > maxLength) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip ? tooltipContext : context)+"'");
        }
        if (height > 0) {
            sb.append(" height='"+height+"'");
        }
        sb.append(">");
        String contextFull = HtmlBaseHelper.trimFitForTd(grids, context, false);
        if(Objects.nonNull(footContext)) {
            contextFull = contextFull + footContext;
        }
        sb.append(setMiddleForCell(contextFull));
        sb.append("</td>");
        return sb.toString();
    }
    
    private String getSubWithTrim(boolean customizeTooltip) {
        StringBuffer sb = new StringBuffer();
        int byteLength = StringUtility.byteLength(context);
        int maxLength = HtmlBaseHelper.getMaxLengthBySubGrids(grids);
        sb.append("<td class=' ");
        sb.append((Objects.nonNull(fontSize) ? align.getKey(): CssFontSizeType.LABEL_12.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" col-xs-"+grids+"'");
        if(customizeTooltip || byteLength > maxLength) {
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(customizeTooltip ? tooltipContext : context)+"'");
        }
        if (height > 0) {
            sb.append(" height='"+height+"'");
        }
        sb.append(">");
        String contextFull = HtmlBaseHelper.trimFitForSubTd(grids, context, false);
        if(Objects.nonNull(footContext)) {
            contextFull = contextFull + footContext;
        }
        sb.append(setMiddleForCell(contextFull));
        sb.append("</td>");
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
    
    public enum TdSetType {

        INDEX                         (1),
        NORMAL                         (2),
        WITH_TRIM                     (3),
        WITH_CUSTOMIZE_TOOLTIP         (4),
        WITH_SUB_TRIM                 (5),
        WITH_SUB_CUSTOMIZE_TOOLTIP     (6),
        TOOLTIP                     (7),
        ;
        
        /** type. */
        private int key;

        private TdSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public TdSetType[] list() {
            return TdSetType.values();
        }
        
        public static TdSetType keyOf(int key) {
            for(TdSetType type : TdSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
