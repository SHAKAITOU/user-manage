package cn.caam.gs.common.enums;

public interface EnumType {

    int getId();
    
    String getKey();
    
    String getMsg();
    
    boolean getNormalShow();

    EnumType[] list();
}
