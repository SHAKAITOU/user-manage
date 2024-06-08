package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.LabelImageSet;
import cn.caam.gs.common.html.element.LabelImageSet.LabelImageSetType;

@Component
public class HtmlImageHelper extends HtmlBaseHelper {
    
    public String get(String id, String src) {
        return LabelImageSet.builder().outPutType(LabelImageSetType.SIMPLE)
                .textId(convertNameDotForId(id))
                .src(src)
                .build().html();
    }
    
    public String get(String id, int imgWidth, int imgHeight, String src) {
        return LabelImageSet.builder().outPutType(LabelImageSetType.SIMPLE)
                .textId(convertNameDotForId(id))
                .imgWidth(imgWidth).imgHeight(imgHeight)
                .src(src)
                .build().html();
    }
    
    public String get(String id, String base64String, String extent) {
        return LabelImageSet.builder().outPutType(LabelImageSetType.SIMPLE)
                .textId(convertNameDotForId(id))
                .base64String(base64String).extent(extent)
                .build().html();
    }
    
    public String get(String id, int imgWidth, int imgHeight, String base64String, String extent) {
        return LabelImageSet.builder().outPutType(LabelImageSetType.SIMPLE)
                .textId(convertNameDotForId(id))
                .imgWidth(imgWidth).imgHeight(imgHeight)
                .base64String(base64String).extent(extent)
                .build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String id, int imgWidth, int imgHeight, String src) {
        return LabelImageSet.builder().outPutType(LabelImageSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textId(convertNameDotForId(id))
                .imgWidth(imgWidth).imgHeight(imgHeight)
                .src(src)
                .build().html();
    }
    
    public String withLabel(int labelWidth, String labelName, String id, int imgWidth, int imgHeight, String base64String, String extent) {
        return LabelImageSet.builder().outPutType(LabelImageSetType.WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .textId(convertNameDotForId(id))
                .imgWidth(imgWidth).imgHeight(imgHeight)
                .base64String(base64String).extent(extent)
                .build().html();
    }

    
}
