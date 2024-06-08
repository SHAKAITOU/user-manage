package cn.caam.gs.common.html;


import java.util.Map;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.html.element.TrSet;
import cn.caam.gs.common.html.element.TrSet.TrSetType;

@Component
public class HtmlTrHelper extends HtmlBaseHelper {

    public TrSet head(CssClassType type) {
        return TrSet.builder().outPutType(TrSetType.HEAD_ROW).headType(type).build();
    }
    
    public TrSet row(Map<String, String> properties) {
        return TrSet.builder().outPutType(TrSetType.BODY_ROW).properties(properties).build();
    }
    
    public TrSet row(Map<String, String> properties, CssClassType bgType) {
        return TrSet.builder().outPutType(TrSetType.BODY_ROW)
                .headType(bgType).properties(properties).build();
    }
    
    public TrSet row(Map<String, String> properties, CssClassType bgType, CssClassType textType) {
        return TrSet.builder().outPutType(TrSetType.BODY_ROW)
                .headType(bgType).textType(textType).properties(properties).build();
    }
}
