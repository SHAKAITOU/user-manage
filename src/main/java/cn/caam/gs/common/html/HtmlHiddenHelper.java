package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.html.element.HiddenSet;

@Component
public class HtmlHiddenHelper extends HtmlBaseHelper {

    public String get(String name, String value) {
        return HiddenSet.builder().id(convertNameDotForId(name)).name(name).value(value).build().html();
    }
    
    public String get(String id, String name, String value) {
        return HiddenSet.builder().id(id).name(name).value(value).build().html();
    }
    
    public String onlyId(String id, String value) {
        return HiddenSet.builder().id(id).value(value).build().html();
    }
}
