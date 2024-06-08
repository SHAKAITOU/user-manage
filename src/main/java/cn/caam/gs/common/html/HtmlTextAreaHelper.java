package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.LabelTextAreaSet;
import cn.caam.gs.common.html.element.LabelTextAreaSet.LabelTextAreaSetType;

@Component
public class HtmlTextAreaHelper extends HtmlBaseHelper {

    public String getWithLabel(int labelWidth, String labelName, String name, String value, int rows) {
        return LabelTextAreaSet.builder().outPutType(LabelTextAreaSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textId(convertNameDotForId(name)).textName(name)
                .rows(rows)
                .textValue(value).build().html();
    }
    
    public String get(String name, String value, int rows) {
        return LabelTextAreaSet.builder().outPutType(LabelTextAreaSetType.SIMPLE)
                .textId(convertNameDotForId(name)).textName(name)
                .rows(rows)
                .textValue(value).build().html();
    }
}
