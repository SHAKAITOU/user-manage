package cn.caam.gs.common.html.element.bs5;

import java.util.List;
import java.util.Objects;

import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.HtmlRadio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelRadioGroupSet {

    private String id;
    private String labelName; 
    private CssFontSizeType fontSize;
    private CssGridsType grids;
    private String name;
    private String selectedValue;
    private List<HtmlRadio> radios;
    public String html() {
        return get();
    }

    private String get() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div class='input-group mb-3'>");
        sb.append("<span class='input-group-text " 
                + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                + "'>" + labelName);
        sb.append("</span>");
        
        sb.append("<div class='input-group-append'>");
        for(int i=0; i<radios.size(); i++) {
            String subId = id + "_" + i;
            sb.append("<div class='radio-inline' style='left:5px'>");
            sb.append("<input class='form-check-input " + id + " ");
            sb.append((Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ));
            sb.append("' type='radio' ");
            sb.append(" id='" + subId + "' ");
            sb.append(" name='" + name + "' ");
            sb.append(" value='" + radios.get(i).getValue() + "' ");
            if (Objects.nonNull(selectedValue)) {
                if (selectedValue.equals(radios.get(i).getValue())) {
                    sb.append(" checked ");
                }
            } else {
                if (i == 0) {
                    sb.append(" checked ");
                }
            }
            sb.append(" >");
            sb.append(" <label class='form-check-label "
                    + (Objects.nonNull(fontSize) ? fontSize.getKey() : "label-14" ) 
                    + "' for='" + subId + "'>");
            sb.append(radios.get(i).getName());
            sb.append("</label>");
            sb.append("</div>");
        }
        sb.append("</div>");
        sb.append("</div>");
        return getContext(sb.toString());
    }
    
    private String getContext(String coreStr) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        if (Objects.nonNull(grids)) {
            sb.append("class='col-xl-" + grids.getKey() 
            + " col-xxl-" + grids.getKey() 
            + " col-lg-" + grids.getKey()
            + " col-md-12 col-sm-12"
            +" '>");
        } else {
            sb.append("class='col'>");
        }
        sb.append(coreStr);
        sb.append("</div>");
        return sb.toString();
    }
}
