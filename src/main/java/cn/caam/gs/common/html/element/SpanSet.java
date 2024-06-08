package cn.caam.gs.common.html.element;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.HtmlBaseHelper;
import cn.caam.gs.common.util.StringUtility;
import cn.caam.gs.common.util.UtilConstants;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpanSet {
    
    
    
    private String id; 
    private int width;
    private String context;
    private String customizeClassName;
    private int maxShowLength; 
    private boolean formCtrol;
    private CssClassType type; 
    private CssAlignType align; 
    private SpanSetType outPutType;
    
    public String html() {
        if(outPutType == SpanSetType.NARROW_SIMPLE) {
            return getNarrowSimple();
        }else if(outPutType == SpanSetType.BADGE) {
            return getBadge();
        }else if(outPutType == SpanSetType.NARROW_BADGE) {
            return getNarrowBadge();
        }else if(outPutType == SpanSetType.PILL) {
            return getPill();
        }else if(outPutType == SpanSetType.SIMPLE_WITH_TRIM) {
            return getSimpleWithTrim();
        }else if(outPutType == SpanSetType.LARGE_BADGE) {
            return getLargeBadge();
        } else if(outPutType == SpanSetType.A_LINK) {
            return getLink();
        } else {
            return getSimple();
        }
    }
    
    private String getSimple() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class=' ");
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.CENTER.getKey()));
        if(StringUtils.isNotBlank(customizeClassName)) {
            sb.append(" "+customizeClassName);
        }
        sb.append(" '>");
        sb.append(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE);
        sb.append("</span>");
        return sb.toString();
    }
    
    private String getSimpleWithTrim() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class=' ");
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.CENTER.getKey()));
        if(StringUtils.isNotBlank(customizeClassName)) {
            sb.append(" "+customizeClassName);
        }
        sb.append("' ");
        int byteLength = StringUtility.byteLength(context);
        String showText = StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE;
        if( maxShowLength != 0 && byteLength > maxShowLength) {
            showText = HtmlBaseHelper.trimStringByByte(context, maxShowLength, false);
            sb.append(" data-toggle='tooltip' data-toolip='"+HtmlBaseHelper.filterSpecialCharacters(context)+"'");
        }
        sb.append(">");
        sb.append(showText);
        sb.append("</span>");
        return sb.toString();
    }
    
    private String getNarrowSimple() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='item-label'>");
        sb.append(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE);
        sb.append("</span>");
        return sb.toString();
    }
    
    private String getBadge() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        if(width > 0) {
            sb.append(" style='width:" + width + "px;'");
        }
        sb.append(" class='badge ");
        sb.append(" badge-"+ (Objects.nonNull(type) ? type.getKey() : CssClassType.CONTEXT.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" item-input ");
        if(formCtrol) {
            sb.append(" form-control");
        }
        sb.append(" '>");
        sb.append(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE);
        sb.append("</span>");
        return sb.toString();
    }
    
    private String getLargeBadge() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        if(width > 0) {
            sb.append(" style='width:" + width + "px;'");
        }
        sb.append(" class='badge ");
        sb.append(" badge-"+ (Objects.nonNull(type) ? type.getKey() : CssClassType.CONTEXT.getKey()));
        sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" item-context-lg ");
        if(formCtrol) {
            sb.append(" form-control");
        }
        sb.append(" '>");
        sb.append(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE);
        sb.append("</span>");
        return sb.toString();
    }
    
    private String getNarrowBadge() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        if(width > 0) {
            sb.append(" style='width:" + width + "px;'");
        }
        sb.append(" class='badge ");
        sb.append(" badge-"+ (Objects.nonNull(type) ? type.getKey() : CssClassType.CONTEXT.getKey()));
        sb.append(" text-"+ (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
        sb.append(" item-context ");
        if(formCtrol) {
            sb.append(" form-control");
        }
        sb.append(" '>");
        sb.append(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE);
        sb.append("</span>");
        return sb.toString();
    }
    
    private String getPill() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='badge badge-pill ");
        sb.append(" badge-"+(Objects.nonNull(type) ? type.getKey() : CssClassType.CONTEXT.getKey()));
        sb.append("'>");
        sb.append(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE);
        sb.append("</span>");
        return sb.toString();
    }
    
    private String getLink() {
        StringBuffer sb = new StringBuffer();
        sb.append("<span ");
        if(StringUtils.isNotBlank(id)) {
            sb.append(" id='"+id+"' ");
        }
        sb.append(" class='a-a2' ");
        sb.append("'>");
        sb.append(StringUtils.isNotBlank(context) ? context : UtilConstants.HTML_SPACE);
        sb.append("</span>");
        return sb.toString();
    }
    
    public enum SpanSetType {

        SIMPLE             (1),
        NARROW_SIMPLE     (2),
        BADGE             (3),
        NARROW_BADGE     (4),
        PILL             (5),
        SIMPLE_WITH_TRIM(6),
        LARGE_BADGE     (7),
        A_LINK             (8),
        ;
        
        /** type. */
        private int key;

        private SpanSetType(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public SpanSetType[] list() {
            return SpanSetType.values();
        }
        
        public static SpanSetType keyOf(int key) {
            for(SpanSetType type : SpanSetType.values()) {
                if(key == type.getKey()) {
                    return type;
                }
            }
            
            return null;
        }
    }
}
