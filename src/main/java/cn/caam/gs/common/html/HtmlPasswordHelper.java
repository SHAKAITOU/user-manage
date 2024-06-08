package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.LabelPasswordSet;
import cn.caam.gs.common.html.element.LabelPasswordSet.LabelPasswordSetType;

@Component
public class HtmlPasswordHelper extends HtmlBaseHelper {

    public String withLabel(int labelWidth, String labelName, String name, String value,
            int maxLength, String placeholder) {
        return LabelPasswordSet.builder().outPutType(LabelPasswordSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .maxLength(maxLength)
                .placeholder(placeholder)
                .textId(convertNameDotForId(name)).textName(name)
                .textValue(value).build().html();
    }
}
