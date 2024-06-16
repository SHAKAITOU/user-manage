package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet;
import cn.caam.gs.common.html.element.bs5.LabelTextAreaSet.LabelTextAreaSetType;

@Component
public class HtmlTextAreaHelper extends HtmlBaseHelper {

    public String getWithLabel(int labelWidth, String labelName, String name, String value, int rows) {
        return LabelTextAreaSet.builder().outPutType(LabelTextAreaSetType.WITH_LABEL)
                .labelName(labelName)
                .id(convertNameDotForId(name)).name(name)
                .rows(rows)
                .value(value).build().html();
    }
    
    public String get(String name, String value, int rows) {
        return LabelTextAreaSet.builder().outPutType(LabelTextAreaSetType.SIMPLE)
                .id(convertNameDotForId(name)).name(name)
                .rows(rows)
                .value(value).build().html();
    }
}
