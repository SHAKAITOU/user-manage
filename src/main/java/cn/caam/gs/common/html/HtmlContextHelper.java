package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.element.LabelContextSet;
import cn.caam.gs.common.html.element.LabelContextSet.LabelContextSetType;
import cn.caam.gs.common.html.element.LabelSet.LabelType;
import cn.caam.gs.common.html.element.LabelSet;

@Component
public class HtmlContextHelper {

    public String withLabel(int labelWidth, String labelName) {
        return LabelSet.builder().outPutType(LabelType.NORMAL)
                .labelWidth(labelWidth).labelName(labelName)
                .build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String context, CssAlignType textAlign) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.NORMAL)
                .labelWidth(labelWidth).labelName(labelName)
                .contextAlign(textAlign)
                .context(context).build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String context, 
            CssAlignType textAlign, CssClassType classType) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.NORMAL)
                .labelWidth(labelWidth).labelName(labelName)
                .contextAlign(textAlign).contextType(classType)
                .context(context).build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String spanId, String context, CssAlignType textAlign) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.NORMAL)
                .labelWidth(labelWidth).labelName(labelName).spanId(spanId)
                .contextAlign(textAlign)
                .context(context).build().html();
    }

    public String narrowWithLabel(int labelWidth, String labelName, String context, CssAlignType textAlign) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.NARROW)
                .labelWidth(labelWidth).labelName(labelName)
                .contextAlign(textAlign)
                .context(context).build().html();
    }
    
    public String withLabelCustomize(int labelWidth, String labelName, String context, CssAlignType textAlign) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.CUSTOMIZE)
                .labelWidth(labelWidth).labelName(labelName)
                .contextAlign(textAlign)
                .context(context).build().html();
    }
    
    
    public String narrowWithLabelCustomize(int labelWidth, String labelName, String context, CssAlignType textAlign) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.NARROW_CUSTOMIZE)
                .labelWidth(labelWidth).labelName(labelName)
                .contextAlign(textAlign)
                .context(context).build().html();
    }
    
    public String withLabelWithFoot(int labelWidth, String labelName, String spanId, String context, 
            CssAlignType textAlign, CssClassType classType, String foot) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.WITH_FOOT)
                .labelWidth(labelWidth).labelName(labelName).spanId(spanId)
                .contextAlign(textAlign).contextType(classType)
                .context(context).footHtml(foot).build().html();
    }
    
    public String withLabelWithFoot(int labelWidth, String labelName, String spanId, String context, CssAlignType textAlign, String foot) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.WITH_FOOT)
                .labelWidth(labelWidth).labelName(labelName).spanId(spanId)
                .contextAlign(textAlign)
                .context(context).footHtml(foot).build().html();
    }
    
    public String largeWithLabel(int labelWidth, String labelName, String spanId, String context, CssAlignType textAlign) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.LARGE)
                .labelWidth(labelWidth).labelName(labelName).spanId(spanId)
                .contextAlign(textAlign)
                .context(context).build().html();
    }
    
    public String largeWithLabelWithFoot(int labelWidth, String labelName, String spanId, String context, CssAlignType textAlign, String foot) {
        return LabelContextSet.builder().outPutType(LabelContextSetType.LARGE)
                .labelWidth(labelWidth).labelName(labelName).spanId(spanId)
                .contextAlign(textAlign)
                .context(context).footHtml(foot).build().html();
    }
    
}
