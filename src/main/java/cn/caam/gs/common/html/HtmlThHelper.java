package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.enums.CssGridsType;
import cn.caam.gs.common.html.element.ThSet;
import cn.caam.gs.common.html.element.ThSet.ThSetType;

@Component
public class HtmlThHelper extends HtmlBaseHelper {

    public ThSet index(CssGridsType grids) {
        return ThSet.builder().outPutType(ThSetType.INDEX).grids(grids.getKey()).build();
    }
    
    public ThSet index(int width) {
        return ThSet.builder().outPutType(ThSetType.INDEX).width(width).build();
    }
    
    public ThSet index(int width, int height) {
        return ThSet.builder().outPutType(ThSetType.INDEX)
                .height(height).width(width).build();
    }
    
    public ThSet index(int height, CssGridsType grids) {
        return ThSet.builder().outPutType(ThSetType.INDEX)
                .height(height).grids(grids.getKey()).build();
    }
    
    public ThSet get(int width, String context, CssAlignType alignType) {
        return ThSet.builder().outPutType(ThSetType.NORMAL).width(width).align(alignType).context(context).build();
    }
    
    public ThSet get(CssGridsType grids, String context, CssAlignType alignType) {
        return ThSet.builder().outPutType(ThSetType.NORMAL).grids(grids.getKey()).align(alignType).context(context).build();
    }
    
    public ThSet get(int height, CssGridsType grids, String context, CssAlignType alignType) {
        return ThSet.builder().outPutType(ThSetType.NORMAL)
                .height(height).grids(grids.getKey()).align(alignType).context(context).build();
    }
}