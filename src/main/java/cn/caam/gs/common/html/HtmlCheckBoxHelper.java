package cn.caam.gs.common.html;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.EnumImpl;
import cn.caam.gs.common.html.element.CheckBoxSet;
import cn.caam.gs.common.html.element.CheckBoxSet.CheckBoxSetType;
import cn.caam.gs.common.html.element.HtmlRadio;

@Component
public class HtmlCheckBoxHelper extends HtmlBaseHelper {

    public String forTable(String id, String name, boolean checkedFlg, String value) {
        return CheckBoxSet.builder().outPutType(CheckBoxSetType.SINGLE_FOR_TABLE)
                .id(id).name(name).checkedFlg(checkedFlg).value(value).build().html();
    }
    
    public String buttons(int labelWidth, String labelName, 
            String prefixId, String prefixName, List<String> values, EnumImpl[] enumTypes) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (EnumImpl type : enumTypes) {
            if (type.getNormalShow()) {
                HtmlRadio radio = new HtmlRadio(String.valueOf(type.getId()), getContext(type.getMsg()),
                        type.getClassType());
                radios.add(radio);
            }
        }
        return CheckBoxSet.builder().outPutType(CheckBoxSetType.MULTIPLE_BUTTON_WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .prefixId(prefixId).prefixName(prefixName)
                .selectedValues(values).radios(radios).build().html();
    }
    
    public String largeButtons(int labelWidth, String labelName, 
            String prefixId, String prefixName, List<String> values, EnumImpl[] enumTypes) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (EnumImpl type : enumTypes) {
            if (type.getNormalShow()) {
                HtmlRadio radio = new HtmlRadio(String.valueOf(type.getId()), getContext(type.getMsg()),
                        type.getClassType());
                radios.add(radio);
            }
        }
        return CheckBoxSet.builder().outPutType(CheckBoxSetType.LARGE_MULTIPLE_BUTTON_WITH_LABEL)
                .labelWidth(labelWidth).labelName(labelName)
                .prefixId(prefixId).prefixName(prefixName)
                .selectedValues(values).radios(radios).build().html();
    }
    
    public String buttons(String prefixId, String prefixName, List<String> values, EnumImpl[] enumTypes) {
        List<HtmlRadio> radios = new ArrayList<>();
        for (EnumImpl type : enumTypes) {
            if (type.getNormalShow()) {
                HtmlRadio radio = new HtmlRadio(String.valueOf(type.getId()), getContext(type.getMsg()),
                        type.getClassType());
                radios.add(radio);
            }
        }
        return CheckBoxSet.builder().outPutType(CheckBoxSetType.MULTIPLE_BUTTON)
                .prefixId(prefixId).prefixName(prefixName)
                .selectedValues(values).radios(radios).build().html();
    }
    
}
