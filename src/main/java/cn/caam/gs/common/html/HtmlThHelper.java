package cn.caam.gs.common.html;


import org.springframework.stereotype.Component;

import cn.caam.gs.common.enums.CssAlignType;
import cn.caam.gs.common.html.element.ThSet;
import cn.caam.gs.common.html.element.ThSet.ThSetType;

@Component
public class HtmlThHelper extends HtmlBaseHelper {

    public ThSet index(int grids) {
        return ThSet.builder().outPutType(ThSetType.INDEX).grids(grids).build();
    }
    
    public ThSet index(int height, int grids) {
        return ThSet.builder().outPutType(ThSetType.INDEX)
                .height(height).grids(grids).build();
    }
    
    public ThSet get(int grids, String context, CssAlignType alignType) {
        return ThSet.builder().outPutType(ThSetType.NORMAL).grids(grids).align(alignType).context(context).build();
    }
    
    public ThSet get(int height, int grids, String context, CssAlignType alignType) {
        return ThSet.builder().outPutType(ThSetType.NORMAL)
                .height(height).grids(grids).align(alignType).context(context).build();
    }
}
