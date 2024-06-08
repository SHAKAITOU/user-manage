package cn.caam.gs.common.html;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.EnumImpl;
import cn.caam.gs.common.html.element.HtmlRadio;
import cn.caam.gs.common.html.element.LabelRadioSet;
import cn.caam.gs.common.html.element.LabelRadioSet.LabelRadioSetType;

@Component
public class HtmlRadioHelper extends HtmlBaseHelper {


    public String withLabel(int labelWidth, String labelName, String name, String selectedValue,
            EnumImpl[] enumTypes) {
        return getRadio(labelWidth, labelName, "",  name, selectedValue, true, false, enumTypes);
    }
    
    public String withLabel(int labelWidth, String labelName, 
            String id, String name, String selectedValue, EnumImpl[] enumTypes) {
        return getRadio(labelWidth, labelName, id, name, selectedValue, true, false, enumTypes);
    }

    public String withLabelIncludeAll(int labelWidth, String labelName, String name, String selectedValue,
            EnumImpl[] enumTypes) {
        return getRadio(labelWidth, labelName, "",  name, selectedValue, true, true, enumTypes);
    }

    public String get(String name, String selectedValue, EnumImpl[] enumTypes) {
        return getRadio(0, "", "", name, selectedValue, false, false, enumTypes);
    }

    public String includeAll(String name, String selectedValue, EnumImpl[] enumTypes) {
        return getRadio(0, "", "", name, selectedValue, false, true, enumTypes);
    }
    
    public String withLabel(int labelWidth, String labelName, 
            String id, String name, String selectedValue, List<HtmlRadio> radios) {
        return getRadio(labelWidth, labelName, id, name, selectedValue, true, false, radios);
    }
    
    public String get(String name, String selectedValue, List<HtmlRadio> radios) {
        return getRadio(0, "", "", name, selectedValue, false, false, radios);
    }

    
    private String getRadio(int labelWidth, String labelName, 
            String id, String name, String selectedValue,
            boolean withLabel, boolean includeAll, EnumImpl[] enumTypes) {

        List<HtmlRadio> radios = new ArrayList<>();
        for (EnumImpl type : enumTypes) {
            if (includeAll || type.getNormalShow()) {
                HtmlRadio radio = new HtmlRadio(String.valueOf(type.getId()), getContext(type.getMsg()),
                        type.getClassType());
                radios.add(radio);
            }
        }

        return getRadio(labelWidth, labelName, id, name, selectedValue, withLabel, includeAll, radios);
    }

    private String getRadio(int labelWidth, String labelName, 
            String id, String name, String selectedValue,
            boolean withLabel, boolean includeAll, List<HtmlRadio> radios) {

        if (withLabel) {
            return LabelRadioSet.builder().outPutType(LabelRadioSetType.WITH_LABEL).labelWidth(labelWidth)
                    .labelName(labelName).id(StringUtils.isEmpty(id) ? convertNameDotForId(name) : id)
                    .name(name).selectedValue(selectedValue)
                    .radios(radios).build().html();
        } else {
            return LabelRadioSet.builder().outPutType(LabelRadioSetType.SIMPLE)
                    .id(StringUtils.isEmpty(id) ? convertNameDotForId(name) : id).name(name)
                    .selectedValue(selectedValue).radios(radios).build().html();
        }
    }
}
