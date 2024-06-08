package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.element.SpanSet;
import cn.caam.gs.common.html.element.SpanSet.SpanSetType;

@Component
public class HtmlSpanHelper extends HtmlBaseHelper {
    
    public String getLink(String id, String context) {
        return SpanSet.builder().outPutType(SpanSetType.A_LINK)
        .id(id).context(context).build().html();
    }
    
    public String get(String id, String context, CssAlignType align) {
        return SpanSet.builder().outPutType(SpanSetType.SIMPLE)
        .type(CssClassType.CONTEXT).align(align)
        .id(id).context(context).build().html();
    }
    
    public String get(String id, String context, CssAlignType align, String customizeClassName) {
        return SpanSet.builder().outPutType(SpanSetType.SIMPLE)
        .type(CssClassType.CONTEXT).align(align)
        .customizeClassName(customizeClassName)
        .id(id).context(context).build().html();
    }
    
    public String withTrim(String context, int maxLength) {
        return SpanSet.builder().outPutType(SpanSetType.SIMPLE_WITH_TRIM)
        .type(CssClassType.CONTEXT).maxShowLength(maxLength)
        .context(context).build().html();
    }
    public String withTrim(String id, String context, int maxLength, CssAlignType align) {
        return SpanSet.builder().outPutType(SpanSetType.SIMPLE_WITH_TRIM)
        .type(CssClassType.CONTEXT).align(align).maxShowLength(maxLength)
        .id(id).context(context).build().html();
    }
    
    public String pill(String context, CssClassType classType) {
        return SpanSet.builder().outPutType(SpanSetType.PILL).align(CssAlignType.CENTER)
                .type(classType).context(context).build().html();
    }
    
    public String narrowBadge(String context, CssClassType type, CssAlignType textAlign) {
        return SpanSet.builder().outPutType(SpanSetType.NARROW_BADGE)
                .type(type).align(CssAlignType.LEFT)
                .context(context).build().html();
    }
}
