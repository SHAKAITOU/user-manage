package cn.caam.gs.common.html.element;

import lombok.Getter;
import cn.caam.gs.common.enums.CssClassType;

public class HtmlRadio implements HtmlSelect {

    @Getter
    private String value;
    @Getter
    private String name;
    @Getter
    private CssClassType classType;
    @Getter
    private boolean disabled;
    @Getter
    private String hiddenData;
    
    public HtmlRadio(String value, String name) {
        this(value, name, false);
    }
    
    public HtmlRadio(String value, String name, String hiddenData) {
        this(value, name, false, hiddenData);
    }
    
    public HtmlRadio(String value, String name, boolean disabled) {
        this.value = value;
        this.name = name;
        this.classType = CssClassType.INFO;
        this.disabled = disabled;
        this.hiddenData = "";
    }
    
    public HtmlRadio(String value, String name, boolean disabled, String hiddenData) {
        this.value = value;
        this.name = name;
        this.classType = CssClassType.INFO;
        this.disabled = disabled;
        this.hiddenData = hiddenData;
    }
    
    public HtmlRadio(String value, String name, CssClassType classType) {
        this.value = value;
        this.name = name;
        this.classType = classType;
        this.hiddenData = "";
    }

}
