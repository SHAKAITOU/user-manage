package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.LabelNumberSet;
import cn.caam.gs.common.html.element.LabelNumberSet.LabelNumberSetType;

@Component
public class HtmlNumberHelper extends HtmlBaseHelper {
    
    public String get(String id, String name, String value, String step,
            String placeholder, boolean notBlank) {
        return LabelNumberSet.builder().outPutType(LabelNumberSetType.SIMPLE)
                .id(id).name(name).value(value)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .min(0).max(99999999).step(step).build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String name, 
            String value, String step, String placeholder, boolean notBlank) {
        return LabelNumberSet.builder().outPutType(LabelNumberSetType.WITH_LABEL).labelWidth(labelWidth)
                .labelName(labelName).id(convertNameDotForId(name)).name(name).value(value)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .min(0).max(99999999).step(step).build().html();
    }

    
    public String withFoot(String name, 
            String value, String step, String foot, String placeholder, boolean notBlank) {
        return LabelNumberSet.builder().outPutType(LabelNumberSetType.WITH_FOOT)
                .id(convertNameDotForId(name)).name(name).value(value)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .min(0).max(99999999).step(step).footHtml(foot).build().html();
    }
    
    public String withLabelWithFoot(int labelWidth, String labelName, String name, 
            String value, String step, String foot, String placeholder, boolean notBlank) {
        return LabelNumberSet.builder().outPutType(LabelNumberSetType.WITH_LABEL_FOOT).labelWidth(labelWidth)
                .labelName(labelName).id(convertNameDotForId(name)).name(name).value(value)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .min(0).max(99999999).step(step).footHtml(foot).build().html();
    }
    
    public String largeWithLabelWithFoot(int labelWidth, String labelName, String name, 
            String value, String step, String foot, String placeholder, boolean notBlank) {
        return LabelNumberSet.builder().outPutType(LabelNumberSetType.LARGE).labelWidth(labelWidth)
                .labelName(labelName).id(convertNameDotForId(name)).name(name).value(value)
                .placeholder(placeholder)
                .notBlank(notBlank)
                .min(0).max(99999999).step(step).footHtml(foot).build().html();
    }
    
}
