package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.LabelDateInputSet;
import cn.caam.gs.common.html.element.LabelDateInputSet.LabelDateInputSetType;

@Component
public class HtmlDateInputHelper extends HtmlBaseHelper {
    
    public String get(String name, String context) {
        return LabelDateInputSet.builder().outPutType(LabelDateInputSetType.WITH_FOOT)
                .id(convertNameDotForId(name)).name(name)
                .value(context).build().html();
    }
    
    public String get(String name, String context, String footHtml) {
        return LabelDateInputSet.builder().outPutType(LabelDateInputSetType.WITH_FOOT)
                .id(convertNameDotForId(name)).name(name)
                .value(context).footHtml(footHtml)
                .build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String name, String context) {
        return LabelDateInputSet.builder().outPutType(LabelDateInputSetType.WITH_LABEL_FOOT)
                .labelWidth(labelWidth).labelName(labelName)
                .id(convertNameDotForId(name)).name(name)
                .value(context).build().html();
    }
    
    public String withLabelNoFoot(int labelWidth, String labelName, String name, String context) {
        return LabelDateInputSet.builder().outPutType(LabelDateInputSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .id(convertNameDotForId(name)).name(name)
                .value(context).build().html();
    }


}
