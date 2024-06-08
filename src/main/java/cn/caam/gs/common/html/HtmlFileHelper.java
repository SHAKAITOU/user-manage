package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.LabelFileSet;
import cn.caam.gs.common.html.element.LabelFileSet.LabelFileSetType;

@Component
public class HtmlFileHelper extends HtmlBaseHelper {
    
    public String get(String name) {
        return LabelFileSet.builder().outPutType(LabelFileSetType.SIMPLE)
                .textId(convertNameDotForId(name)).textName(name)
                .build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String name) {
        return LabelFileSet.builder().outPutType(LabelFileSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textId(convertNameDotForId(name)).textName(name)
                .build().html();
    }
    
    public String withLabelWithFoot(int labelWidth, String labelName, String name, String footHtml) {
        return LabelFileSet.builder().outPutType(LabelFileSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textId(convertNameDotForId(name)).textName(name)
                .footHtml(footHtml)
                .build().html();
    }

    
}
