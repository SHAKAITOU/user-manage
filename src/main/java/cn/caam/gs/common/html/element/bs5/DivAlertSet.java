package cn.caam.gs.common.html.element.bs5;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;
import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssClassType;
import cn.caam.gs.common.enums.CssFontSizeType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.enums.GridFlexType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DivAlertSet {

    private String id;
    private String[] contexts;
    private CssClassType classType; 
    private CssAlignType align;
    private CssGridsType grids;
    private GridFlexType gridFlexType;
    @Default
    private CssFontSizeType fontSize = CssFontSizeType.LABEL_14;
    
    public String html() {
        return getSimple();
    }
    
    private String getSimple() {
        if (Objects.nonNull(grids)) {
            return getContext();
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("<div class='alert ");
            sb.append(" alert-"+ (Objects.nonNull(classType) ? classType.getKey() : CssClassType.CONTEXT.getKey()));
            sb.append(" text-" + (Objects.nonNull(align) ? align.getKey(): CssAlignType.LEFT.getKey()));
            sb.append(" " + fontSize.getKey());
            sb.append(" '");
            if(!StringUtils.isEmpty(id)) {
                sb.append(" id='"+id+"'");
            }
            sb.append(">");
            for(String context : contexts) {
                if (Objects.nonNull(context)) {
                    sb.append(context);
                }
            }
            sb.append("</div>");
            return sb.toString();
        }
    }
    
    private String getContext() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div ");
        sb.append("class='col-xl-" + grids.getKey() 
        + " col-xxl-" + grids.getKey() 
        + " col-lg-" + grids.getKey()
        + " col-md-12 col-sm-12"
        + " " + (Objects.nonNull(gridFlexType) ? gridFlexType.getKey() : GridFlexType.LEFT.getKey()) 
        + " alert alert-"+ (Objects.nonNull(classType) ? classType.getKey() : CssClassType.CONTEXT.getKey())
        + " " + fontSize.getKey()
        + " '>");
        for(String context : contexts) {
            if (Objects.nonNull(context)) {
                sb.append(context);
            }
        }
        sb.append("</div>");
        return sb.toString();
    }
}
