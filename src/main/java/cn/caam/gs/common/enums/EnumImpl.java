package cn.caam.gs.common.enums;

public interface EnumImpl {

    int getId();
    
    String getKey();
    
    String getMsg();
    
    boolean getNormalShow();
    
    CssClassType getClassType();

    EnumImpl[] list();
}