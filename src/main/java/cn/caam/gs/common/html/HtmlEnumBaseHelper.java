package cn.caam.gs.common.html;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.EnumImpl;
import cn.caam.gs.common.html.element.SpanSet;
import cn.caam.gs.common.html.element.SpanSet.SpanSetType;

@Component
public class HtmlEnumBaseHelper extends HtmlBaseHelper {
    
    public String pill(EnumImpl enumType) {
        return SpanSet.builder().outPutType(SpanSetType.PILL).align(CssAlignType.CENTER)
                .type(enumType.getClassType()).context(getContext(enumType.getMsg())).build().html();
    }
    
    public String pill(EnumImpl enumType, CssClassType classType) {
        return SpanSet.builder().outPutType(SpanSetType.PILL).align(CssAlignType.CENTER)
                .type(classType).context(getContext(enumType.getMsg())).build().html();
    }
    
    public String pill(int id, EnumImpl[] enumTypes) {
        for(EnumImpl type : enumTypes) {
            if(type.getId() == id) {
                return SpanSet.builder().outPutType(SpanSetType.PILL).align(CssAlignType.CENTER)
                    .type(type.getClassType()).context(getContext(type.getMsg())).build().html();
            }
        }
        
        return "";

    }
    
    public String badge(int id, EnumImpl[] enumTypes) {
        for(EnumImpl type : enumTypes) {
            if(type.getId() == id) {
                return SpanSet.builder().outPutType(SpanSetType.BADGE).align(CssAlignType.CENTER)
                    .type(type.getClassType()).context(getContext(type.getMsg())).build().html();
            }
        }
        
        return "";

    }
    
    public String badge(EnumImpl enumType) {
        return SpanSet.builder().outPutType(SpanSetType.BADGE).align(CssAlignType.CENTER)
                .type(enumType.getClassType()).context(getContext(enumType.getMsg())).build().html();
    }
    
    public String narrowBadge(int id, EnumImpl[] enumTypes) {
        for(EnumImpl type : enumTypes) {
            if(type.getId() == id) {
                return SpanSet.builder().outPutType(SpanSetType.NARROW_BADGE).align(CssAlignType.CENTER)
                    .type(type.getClassType()).context(getContext(type.getMsg())).build().html();
            }
        }
        
        return "";

    }

}
