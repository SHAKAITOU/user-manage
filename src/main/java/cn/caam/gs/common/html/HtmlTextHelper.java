package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.html.element.LabelTextSet;
import cn.caam.gs.common.html.element.LabelTextSet.LabelTextSetType;

@Component
public class HtmlTextHelper extends HtmlBaseHelper {

    public String noLabel(String name, String value, CssAlignType textAlign, 
            int maxLength, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.SIMPLE)
                .textAlign(textAlign)
                .maxLength(maxLength)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value).build().html();
    }
    
    public String noLabelWithFoot(String name, String value, String footHtml, 
            CssAlignType textAlign, int maxLength, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.SIMPLE_WITH_FOOT)
                .textAlign(textAlign)
                .maxLength(maxLength)
                .footHtml(footHtml)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value).build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, 
            String name, String value, CssAlignType textAlign, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textAlign(textAlign)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value).build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String name, String value, 
            CssAlignType textAlign, int maxLength, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textAlign(textAlign)
                .maxLength(maxLength)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value).build().html();
    }
    
    public String largeWithLabel(int labelWidth, String labelName, String name, String value, 
            CssAlignType textAlign, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.LARGE_WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textAlign(textAlign)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value).build().html();
    }
    
    public String withLabelWithTooltip(
            int labelWidth, String labelName, String name, String value, 
            String customizeTooltip, CssAlignType textAlign, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textAlign(textAlign)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .customizeTooltip(customizeTooltip)
                .build().html();
    }
    
    public String withLabelWithFoot(int labelWidth, String labelName, 
            String name, String value, String footHtml, 
            CssAlignType textAlign, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.WITH_LABEL_FOOT)
                .labelWidth(labelWidth).labelName(labelName)
                .textAlign(textAlign)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .footHtml(footHtml)
                .textValue(value).build().html();
    }
    
    public String withLabelWithFoot(int labelWidth, String labelName, 
            String name, String value, String footHtml, 
            CssAlignType textAlign, int maxLength, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.WITH_LABEL_FOOT)
                .labelWidth(labelWidth).labelName(labelName)
                .textAlign(textAlign)
                .maxLength(maxLength)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .footHtml(footHtml)
                .textValue(value).build().html();
    }
    
    public String withLabelWithFootWithTooltip(
            int labelWidth, String labelName, String name, String value, 
            String customizeTooltip, String footHtml, 
            CssAlignType textAlign, String placeholder, boolean notBlank) {
        return LabelTextSet.builder().outPutType(LabelTextSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textAlign(textAlign)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value)
                .customizeTooltip(customizeTooltip)
                .footHtml(footHtml)
                .build().html();
    }
}
