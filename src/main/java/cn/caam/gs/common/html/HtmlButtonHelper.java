package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.IconSet;
import cn.caam.gs.common.html.element.IconSet.IconSetCss;
import cn.caam.gs.common.html.element.IconSet.IconSetType;
import cn.caam.gs.common.html.element.bs5.ButtonSet;
import cn.caam.gs.common.html.element.bs5.ButtonSet.ButtonSetType;
import cn.caam.gs.common.util.UtilConstants;

@Component
public class HtmlButtonHelper {
    
    public String get(CssClassType classType, String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .classType(classType).id(id).buttonName(buttonName).build().html();
    }
    
    public String getBorder(CssClassType classType, String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .isBorderOnly(true)
                .classType(classType).id(id).buttonName(buttonName).build().html();
    }
    
    public String getBorder(IconSetType icon, CssClassType classType, CssGridsType grids, String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .isBorderOnly(true).grids(grids)
                .classType(classType).id(id).buttonName(
                        IconSet.builder().type(icon).css(IconSetCss.NOMAL_10).build().html()
                        + UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + buttonName).build().html();
    }
    
    public String getBorder(IconSetType icon, CssClassType classType, String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .isBorderOnly(true)
                .classType(classType).id(id).buttonName(
                        IconSet.builder().type(icon).css(IconSetCss.NOMAL_10).build().html()
                        + UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + buttonName).build().html();
    }
    
    public String getBorder(IconSetType icon, CssClassType classType, String id) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .isBorderOnly(true)
                .classType(classType).id(id).buttonName(
                        IconSet.builder().type(icon).css(IconSetCss.NOMAL_10).build().html()).build().html();
    }
    
    public String get(IconSetType icon, CssClassType classType, String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .classType(classType).id(id).buttonName(
                        IconSet.builder().type(icon).css(IconSetCss.NOMAL_10).build().html()
                        + UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + buttonName).build().html();
    }
    
    public String customize(CssClassType classType, String id, String buttonName, String customizeClassName) {
        return ButtonSet.builder().outPutType(ButtonSetType.CUSTOMIZE)
                .classType(classType).id(id).buttonName(buttonName).customizeClassName(customizeClassName)
                .build().html();
    }
    
    public String getBorder(IconSetType icon, CssClassType classType, String id, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .isBorderOnly(true)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(IconSet.builder().type(icon).css(IconSetCss.NOMAL_10).build().html())
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String get(IconSetType icon, CssClassType classType, String id, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.NORMAL)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(IconSet.builder().type(icon).css(IconSetCss.NOMAL_10).build().html())
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String forTable(CssClassType classType, String id, String buttonName, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.TABLE)
                .classType(classType).id(id).buttonName(buttonName).classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String forTable(IconSetType icon, CssClassType classType, String id, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.TABLE)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(IconSet.builder().type(icon).css(IconSetCss.NOMAL_8).build().html())
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String forTableBorder(IconSetType icon, CssClassType classType, String id, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.TABLE)
                .isBorderOnly(true)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(IconSet.builder().type(icon).css(IconSetCss.NOMAL_8).build().html())
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String forTableNameRight(IconSetType icon, CssClassType classType, String id, String buttonName, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.TABLE)
                .isBorderOnly(false)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(IconSet.builder().type(icon).css(IconSetCss.NOMAL_8).build().html()
                        + UtilConstants.HTML_SPACE + buttonName)
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String forTableNameLeft(IconSetType icon, CssClassType classType, 
            String id, String buttonName, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.TABLE)
                .isBorderOnly(false)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(buttonName
                        + UtilConstants.HTML_SPACE + 
                        IconSet.builder().type(icon).css(IconSetCss.NOMAL_8).build().html())
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String forTableBorderNameRight(IconSetType icon, CssClassType classType, String id, String buttonName, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.TABLE)
                .isBorderOnly(true)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(IconSet.builder().type(icon).css(IconSetCss.NOMAL_8).build().html()
                        + UtilConstants.HTML_SPACE + buttonName)
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String forTableBorderNameLeft(IconSetType icon, CssClassType classType, 
            String id, String buttonName, String keyValue, String classDistinctName) {
        return ButtonSet.builder().outPutType(ButtonSetType.TABLE)
                .isBorderOnly(true)
                .classType(classType).id(id).keyValue(keyValue)
                .buttonName(buttonName
                        + UtilConstants.HTML_SPACE + 
                        IconSet.builder().type(icon).css(IconSetCss.NOMAL_8).build().html())
                .classDistinctName(classDistinctName)
                .build().html();
    }
    
    public String close(String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.CLOSE)
                .classType(CssClassType.SECONDARY).id(id).buttonName(
                        IconSet.builder().type(IconSetType.CLOSE).css(IconSetCss.NOMAL_10).build().html()
                        + UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + buttonName).build().html();
    }
    
    public String closeBorder(String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.CLOSE)
                .isBorderOnly(true)
                .classType(CssClassType.SECONDARY).id(id).buttonName(
                        IconSet.builder().type(IconSetType.CLOSE).css(IconSetCss.NOMAL_10).build().html()
                        + UtilConstants.HTML_SPACE + UtilConstants.HTML_SPACE + buttonName).build().html();
    }
    
    public String closeNotShow(String id, String buttonName) {
        return ButtonSet.builder().outPutType(ButtonSetType.CLOSE_NOT_SHOW)
                .classType(CssClassType.SECONDARY).id(id).buttonName(buttonName).build().html();
    }
}
